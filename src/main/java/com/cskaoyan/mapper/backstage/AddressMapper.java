package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.Address;
import com.cskaoyan.bean.backstage.AddressExample;
import com.cskaoyan.bean.wx.AddressList;
import com.cskaoyan.bean.wx.cart.CheckedAddressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AddressMapper {
    long countByExample(AddressExample example);

    int deleteByExample(AddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Address record);

    int insertSelective(Address record);

    List<Address> selectByExample(AddressExample example);

    Address selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByExample(@Param("record") Address record, @Param("example") AddressExample example);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

    String selectCityByCityId(@Param("cityId") Integer cityId);

    String selectProvinceByProvinceId(@Param("provinceId") Integer provinceId);

    String selectAreaByAreaId(@Param("areaId") Integer areaId);

    List<AddressList> selectAddressByUserId(@Param("userId") Integer userId);

    Integer insertAddress(@Param("address") Address address);

    CheckedAddressVo selectCheckedAddressByPrimaryKey(Integer addressId);
}