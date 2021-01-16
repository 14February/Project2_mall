package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.backstage.AddressItem;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-09 15:41
 */
@RestController
@RequestMapping("admin/address")
public class AdminAddressController{
    @Autowired
    UserService userService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, String name, Integer userId){
        ListData<AddressItem> data =  userService.queryAddressList(page,limit,sort,order,name,userId);
        return BaseRespVo.ok(data);
    }


}
