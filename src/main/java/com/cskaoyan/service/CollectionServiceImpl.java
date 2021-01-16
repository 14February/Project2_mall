package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Collect;
import com.cskaoyan.bean.backstage.CollectExample;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.CollectionList;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.CollectMapper;
import com.cskaoyan.mapper.backstage.GoodsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CollectionServiceImpl implements CollectionService{

    @Autowired
    CollectMapper collectMapper;
    @Autowired
    GoodsMapper goodsMapper;


    //TODO:分页数据不够，潜在totalPages = 0 问题
    @Override
    public CollectionList collectList(Integer type, Integer page, Integer size) {

        PageHelper.startPage(1,size);
        CollectExample collectExample = new CollectExample();
        collectExample.setOrderByClause("add_time desc");
        CollectExample.Criteria collectExampleCriteria= collectExample.createCriteria();
        collectExampleCriteria.andDeletedEqualTo(false);

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria goodsExampleCriteria = goodsExample.createCriteria();

        //所有的收藏
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        PageInfo<Collect> collectPageInfo = new PageInfo<>(collects);

        List<Integer> integers = new ArrayList<>();
        for (Collect collect : collects) {
            integers.add(collect.getValueId());
        }

        //防止查空
        if (integers.size()!=0) {
            goodsExampleCriteria.andIdIn(integers);
        }

        List<Goods> goods = goodsMapper.selectByExample(goodsExample);

        ArrayList<CollectionList.CollectListDTO> list = null;
        for (Collect collect : collects) {
            list = new ArrayList<>();
            for (Goods good : goods) {
                CollectionList.CollectListDTO collectListDTO =
                        new CollectionList.CollectListDTO(
                                good.getBrief(),
                                good.getPicUrl(),
                                collect.getValueId(),
                                good.getName(),
                                collect.getId(),
                                collect.getType(),
                                good.getRetailPrice()
                );
                list.add(collectListDTO);
            }
        }
        int total = (int) collectPageInfo.getTotal();
        return CollectionList.data(total,list);
    }

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, String> collectionAddordelete(Map map) {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);

        Integer type = (Integer) map.get("type");
        Integer valueId = (Integer) map.get("valueId");

        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andValueIdEqualTo(valueId);
        criteria.andDeletedEqualTo(false);

        //查询数据库中该商品收藏数
        List<Collect> collects = collectMapper.selectByExample(collectExample);

        Collect collect = null;
        if (type.equals(0)) {
            Integer id = user.getId();
            collect = new Collect(
                    null,
                    id,
                    valueId,
                    type,
                    new Date(),
                    new Date(),
                    false
            );
        }

        Map<String, String> resMap = new HashMap<>();

        //收藏若小于1,,添加
        if (collects.size()<1){
        collectMapper.insert(collect);
        resMap.put("type","add");
        }
        else {
            Collect deCollect = new Collect();
            deCollect.setDeleted(true);
            collectMapper.updateByExampleSelective(deCollect,collectExample);
            resMap.put("type","delete");
        }
        return resMap ;
    }
}
