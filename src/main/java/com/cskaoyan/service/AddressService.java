package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Address;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.Region;
import com.cskaoyan.bean.wx.AddressDetail;
import com.cskaoyan.bean.wx.AddressList;


import java.util.List;

public interface AddressService {

    List<AddressList> addressList();

    AddressDetail addressDetail(Integer id);

    List<Region> regionList(Integer pid);

    Integer addressSave(Address address);

    void addressDelete(Integer id);

}
