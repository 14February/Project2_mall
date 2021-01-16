package com.cskaoyan.mapper;

import com.cskaoyan.bean.wx.cart.Cart;
import com.cskaoyan.bean.wx.cart.CartExample;
import com.cskaoyan.bean.wx.cart.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartsMapper {
    long countByExample(CartExample example);

    Integer countGoodsByUserId(Integer userId);

    int deleteByExample(CartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    List<Cart> selectByExample(CartExample example);

    Cart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByExample(@Param("record") Cart record, @Param("example") CartExample example);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<CartVo> selectCartByUserId(Integer userId);

    int insertCartVoSelective(CartVo record);

    int deleteByProductId(Integer productId, Integer userId);

    int updateCheckedByProductIdAndUserId(Integer userId, Integer productId, Integer isChecked);

    int selectCartId();

    List<CartVo> selectCheckedCartByUserId(Integer userId);

    CartVo selectByProductIdAndUserId(int productId, Integer userId);


    void updateByProductIdAndUserId(int productId, Integer userId,int number);
}