package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/user")
public class AdminUserController{

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit,String sort,String order,String username,String mobile)
    {
        ListData<User> listData = userService.queryList(page,limit,sort,order,username,mobile);
        return BaseRespVo.ok(listData);
    }
}
