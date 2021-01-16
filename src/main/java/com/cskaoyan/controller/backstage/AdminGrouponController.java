package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.groupon.GrouponRules;
import com.cskaoyan.service.GrouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/groupon")
public class AdminGrouponController{

    @Autowired
    GrouponService grouponService;

    @RequestMapping("list")
    public BaseRespVo getGrouponRules(Integer page,Integer limit,Integer goodsId, String sort,String order){
        ListData<GrouponRules> grouponRules=grouponService.getGrouponRules(page,limit,goodsId,sort,order);
        return BaseRespVo.ok(grouponRules);
    }
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody GrouponRules grouponRules){
        int code=grouponService.create(grouponRules);
        if(code==1){
            return BaseRespVo.ok();
        }else if (code==500){
            return BaseRespVo.fail("商品不存在");
        }
        return BaseRespVo.fail();
    }
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody GrouponRules grouponRules){

        int code=grouponService.update(grouponRules);
        if(code==1){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody GrouponRules grouponRules){
        int code=grouponService.delete(grouponRules);
        if(code==1){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }
    @RequestMapping("listRecord")
    public BaseRespVo getRecord(Integer page,Integer limit, String sort,String order){
        return BaseRespVo.ok();
    }
}
