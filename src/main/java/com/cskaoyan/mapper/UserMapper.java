package com.cskaoyan.mapper;

import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.backstage.user.UserExample;
import java.util.List;

import com.cskaoyan.bean.wx.goods.StatusAndGoodsNum;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUsername(@Param("username") String username);

    Integer selectUserIdByMobile(@Param("mobile") String mobile);

    String selectUsernameByMobile(@Param("mobile") String mobile);

    List<User> selectUserByUsernameOrWxCode(@Param("username") String username,@Param("wxCode") String wxCode,@Param("mobile") String mobile);

    int updatePasswordByMobile(@Param("mobile") String mobile,@Param("password") String password);

    List<StatusAndGoodsNum> queryStatusAndGoodsNum(Integer id);

    List<Integer> queryUncommentGoods(Integer id);

//    List<Integer> queryOrderStatusByUserId(Integer id);
//
//    List<Integer> queryUncommentGoods(String username);

}