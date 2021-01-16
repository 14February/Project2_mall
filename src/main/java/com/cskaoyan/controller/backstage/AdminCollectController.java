package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.Collect;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description 会员收藏
 * @create 2021-01-10 14:40
 */
@RestController
@RequestMapping("admin/collect")
public class AdminCollectController{

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, Integer userId, Integer valueId){
        ListData<Collect> collects = userService.queryCollectList(page,limit,sort,order,userId,valueId);
        return  BaseRespVo.ok(collects);
    }


}
