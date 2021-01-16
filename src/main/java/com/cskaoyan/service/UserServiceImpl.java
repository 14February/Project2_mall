package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.*;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistory;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistoryExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.backstage.user.UserExample;
import com.cskaoyan.bean.wx.goods.OrderVO;
import com.cskaoyan.bean.wx.goods.StatusAndGoodsNum;
import com.cskaoyan.bean.wx.goods.UserOrderVO;
import com.cskaoyan.bean.wx.register.Register;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.*;
import com.cskaoyan.token.MallToken;
import com.cskaoyan.utils.Md5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public ListData<User> queryList(Integer page,Integer limit,String sort,String order,String username,String mobile)
    {
        PageHelper.startPage(page, limit);
        UserExample example = new UserExample();
        example.setOrderByClause(sort + " " + order);

        UserExample.Criteria criteria = example.createCriteria();
        if(username != null) criteria.andUsernameLike("%" + username + "%");
        if(mobile != null) criteria.andMobileLike("%" + username + "%");
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        int total = (int)userPageInfo.getTotal();   //符合条件的总记录数
        return ListData.data(total, users);
    }

    @Override
    public ListData<AddressItem> queryAddressList(Integer page,Integer limit,String sort,String order,String name,Integer userId) {
        PageHelper.startPage(page, limit);
        AddressExample example = new AddressExample();
        example.setOrderByClause(sort + " " + order);
        AddressExample.Criteria criteria = example.createCriteria();
        if(name != null) criteria.andNameLike("%" + name + "%");
        if(userId != null) criteria.andUserIdEqualTo(userId);
        List<Address> addresses = addressMapper.selectByExample(example);
        List<AddressItem> addressItems = new ArrayList<>(addresses.size());
        for (int i = 0; i < addresses.size(); i++) {
            Address address = addresses.get(i);
            String city =  addressMapper.selectCityByCityId(address.getCityId());
            String province = addressMapper.selectProvinceByProvinceId(address.getProvinceId());
            String area = addressMapper.selectAreaByAreaId(address.getAreaId());
            AddressItem addressItem = new AddressItem(area,address.getIsDefault(),address.getAreaId(),
                    address.getAddress(),province,city,address.getName(),address.getMobile(),address.getId(),
                    address.getCityId(),address.getUserId(),address.getProvinceId());
            addressItems.add(addressItem);
        }
        PageInfo<AddressItem> addressItemsPageInfo = new PageInfo<>(addressItems);
        int total = (int)addressItemsPageInfo.getTotal();   //符合条件的总记录数

        return ListData.data(total, addressItems);
    }

    @Override
    public ListData<Collect> queryCollectList(Integer page,Integer limit,String sort,String order,Integer userId,Integer valueId) {
        PageHelper.startPage(page, limit);
        CollectExample example = new CollectExample();
        example.setOrderByClause(sort + " " + order);
        CollectExample.Criteria criteria = example.createCriteria();
        if(userId != null) criteria.andUserIdEqualTo(userId);
        if(valueId != null) criteria.andValueIdEqualTo(valueId);
        List<Collect> collects = collectMapper.selectByExample(example);
        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);
        int total = (int)collectPageInfo.getTotal();
        return ListData.data(total, collects);

    }

    @Override
    public ListData<Footprint> queryFootprintList(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId) {
        PageHelper.startPage(page, limit);
        FootprintExample example = new FootprintExample();
        example.setOrderByClause(sort + " " + order);
        FootprintExample.Criteria criteria = example.createCriteria();
        if(userId != null) criteria.andUserIdEqualTo(userId);
        if(goodsId != null) criteria.andGoodsIdEqualTo(goodsId);
        List<Footprint> footprints = footprintMapper.selectByExample(example);
        PageInfo<Footprint> collectPageInfo = new PageInfo<>(footprints);
        int total = (int)collectPageInfo.getTotal();
        return ListData.data(total, footprints);

    }

    @Override
    public ListData<SearchHistory> querySearchHistoryList(Integer page,Integer limit,String sort,String order,Integer userId,String keyword) {
        PageHelper.startPage(page, limit);
        SearchHistoryExample example = new SearchHistoryExample();
        example.setOrderByClause(sort + " " + order);
        SearchHistoryExample.Criteria criteria = example.createCriteria();
        if(userId != null) criteria.andUserIdEqualTo(userId);
        if(keyword != null) criteria.andKeywordLike("%" + keyword + "%");
        List<SearchHistory> searchHistories = searchHistoryMapper.selectByExample(example);
        PageInfo<SearchHistory> searchHistoryPageInfo = new PageInfo<>(searchHistories);
        int total = (int)searchHistoryPageInfo.getTotal();
        return ListData.data(total, searchHistories);

    }

    @Override
    public boolean resetPassword(String mobile,String password)
    {
        return userMapper.updatePasswordByMobile(mobile,password) > 0;
    }

    @Override
    public int register(Register register) throws NoSuchAlgorithmException
    {
        String username = register.getUsername();
        String wxCode = register.getWxCode();
        String mobile = register.getMobile();
        List<User> users = userMapper.selectUserByUsernameOrWxCode(username,wxCode,mobile);
        for(User user : users)
        {
            if(mobile.equals(user.getMobile())) return 3;
            if(wxCode.equals(user.getWeixinOpenid())) return 2;
            if(username.equals(user.getUsername())) return 1;
        }
        User user = new User();
        user.setUsername(username);
        String password = Md5Utils.getMd5WithSalt(register.getPassword());
        user.setPassword(password);
        user.setMobile(mobile);
        user.setWeixinOpenid(wxCode);
        userMapper.insertSelective(user);
        SecurityUtils.getSubject().login(new MallToken(username,password,"wx"));
        return 0;
    }

    @Override
    public ListData<Feedback> queryFeedbackList(Integer page, Integer limit, String sort, String order, String username, Integer id) {
        PageHelper.startPage(page, limit);
        FeedbackExample example = new FeedbackExample();
        example.setOrderByClause(sort + " " + order);
        FeedbackExample.Criteria criteria = example.createCriteria();
        if(username != null) criteria.andUsernameEqualTo(username);
        if(id != null) criteria.andIdEqualTo(id);
        List<Feedback> feedbacks = feedbackMapper.selectByExample(example);
        PageInfo<Feedback> feedbacksPageInfo = new PageInfo<>(feedbacks);
        int total = (int)feedbacksPageInfo.getTotal();
        return ListData.data(total, feedbacks);
    }

    @Override
    public UserOrderVO queryOrderStatusByUsername() {
        Subject subject = SecurityUtils.getSubject();
//        Object principal = subject.getPrincipal();
        String username = (String)subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);

        OrderVO orderVO = new OrderVO(0, 0, 0, 0);
        UserOrderVO userOrderVO = new UserOrderVO();
        Integer id = user.getId();
//        List<Integer> statusList = userMapper.queryOrderStatusByUserId(id);

        List<StatusAndGoodsNum> statusAndGoodsNums = userMapper.queryStatusAndGoodsNum(id);
        for (StatusAndGoodsNum statusAndGoodsNum : statusAndGoodsNums) {
            Integer status = statusAndGoodsNum.getStatus();
            Integer goodsNum = statusAndGoodsNum.getGoodsNum(); //该订单的商品数量
            if (status.equals(101)) {  //未付款
                Integer unpaid = orderVO.getUnpaid();
                unpaid += goodsNum;
                orderVO.setUnpaid(unpaid);
            }
            if (status.equals(201)) { //已付款，未发货
                Integer unship = orderVO.getUnship();
                unship += goodsNum;
                orderVO.setUnship(unship);
            }
            if (status.equals(301)) { //已发货，未收货
                Integer unrecv = orderVO.getUnrecv();
                unrecv += goodsNum;
                orderVO.setUnrecv(unrecv);
            }
        }

        List<Integer> uncommentGoodsNum = userMapper.queryUncommentGoods(id);
        Integer uncomment = orderVO.getUncomment();
        for (Integer uncommentNum : uncommentGoodsNum) {
            uncomment += uncommentNum;
        }
        orderVO.setUncomment(uncomment);
        userOrderVO.setOrder(orderVO);
        return userOrderVO;
    }

    @Override
    public Integer selectUserByUsername(String username) {
        User user = userMapper.selectUserByUsername(username);
        return user.getId();
    }
}
