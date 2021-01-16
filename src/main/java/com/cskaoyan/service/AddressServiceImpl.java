package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Address;

import com.cskaoyan.bean.backstage.marketManagement.adminRegion.Region;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.RegionExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.AddressDetail;
import com.cskaoyan.bean.wx.AddressList;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.AddressMapper;
import com.cskaoyan.mapper.backstage.marketManagementMapper.RegionMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<AddressList> addressList() {

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        List<AddressList> addressLists = addressMapper.selectAddressByUserId(user.getId());
        return addressLists;
    }

    @Override
    public AddressDetail addressDetail(Integer id) {

        Address address = addressMapper.selectByPrimaryKey(id);

        String areaName =  regionMapper.selectNameById(address.getAreaId());
        String cityName = regionMapper.selectNameById(address.getCityId());
        String provinceName = regionMapper.selectNameById(address.getProvinceId());

        AddressDetail addressDetail = new AddressDetail(
                address.getIsDefault(),
                address.getAreaId(),
                address.getAddress(),
                cityName,
                areaName,
                address.getName(),
                address.getMobile(),
                address.getId(),
                address.getCityId(),
                provinceName,
                address.getProvinceId()
        );
        return addressDetail;
    }

    @Override
    public List<Region> regionList(Integer pid) {

        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria();

        criteria.andPidEqualTo(pid);
        List<Region> regions = regionMapper.selectByExample(regionExample);
        return  regions;
    }


    @Override
    public Integer addressSave(Address address) {

        //如果id = 0 是新增,获取当前username 并在user表中查询userId
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
//
        Integer id = userMapper.selectUserByUsername(username).getId();

        if (address.getId().equals(0)){
            address.setUserId(id);
            address.setAddTime(new Date());
            address.setUpdateTime(new Date());
            Integer integer = addressMapper.insertAddress(address);
            return address.getId();
        }
        else {

            int i = addressMapper.updateByPrimaryKeySelective(address);
            return address.getId();
        }
    }

    @Override
    public void addressDelete(Integer id) {

        Address address = addressMapper.selectByPrimaryKey(id);
        address.setDeleted(true);
        addressMapper.updateByPrimaryKeySelective(address);
    }
}
