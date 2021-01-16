package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.*;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistory;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.goods.UserOrderVO;
import com.cskaoyan.bean.wx.register.Register;
import org.apache.ibatis.annotations.Param;

import java.security.NoSuchAlgorithmException;

/**
 *
 */

public interface UserService {

    ListData<User> queryList(Integer page, Integer limit, String sort, String order, String username, String mobile);

    ListData<AddressItem> queryAddressList(Integer page, Integer limit, String sort, String order, String name, Integer userId);

    ListData<Collect> queryCollectList(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId);

    ListData<Footprint> queryFootprintList(Integer page, Integer limit, String sort, String order, Integer userId, Integer goodsId);

    ListData<SearchHistory> querySearchHistoryList(Integer page, Integer limit, String sort, String order, Integer userId, String keyword);

    ListData<Feedback> queryFeedbackList(Integer page, Integer limit, String sort, String order, String username, Integer id);

    int register(Register register) throws NoSuchAlgorithmException;

    boolean resetPassword(String mobile, String password);

    UserOrderVO queryOrderStatusByUsername();

    Integer selectUserByUsername(@Param("username") String username);
}
