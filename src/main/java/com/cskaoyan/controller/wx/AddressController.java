package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.Address;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.Region;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.AddressDetail;
import com.cskaoyan.bean.wx.AddressList;
import com.cskaoyan.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class AddressController {

    @Autowired
    AddressService addressService;

    @RequestMapping("/address/list")
    public BaseRespVo addressList(){

        List<AddressList> addressListList = addressService.addressList();

        return BaseRespVo.ok(addressListList);
    }

    @RequestMapping("/address/detail")
    public BaseRespVo addressDetail(Integer id){
        AddressDetail addressDetail = addressService.addressDetail(id);
        return BaseRespVo.ok(addressDetail);
    }


    @RequestMapping("/region/list")
    public BaseRespVo regionList(Integer pid){
       List<Region> regionList = addressService.regionList(pid);
       return BaseRespVo.ok(regionList);
    }

    @RequestMapping("/address/save")
    public BaseRespVo addressSave(@RequestBody Address address){
        Integer id = addressService.addressSave(address);
        return BaseRespVo.ok(id);
    }
    @RequestMapping("/address/delete")
    public BaseRespVo addressDelete(@RequestBody Map map){
        Integer id = (Integer) map.get("id");
        addressService.addressDelete(id);
        return BaseRespVo.ok();
    }

}
