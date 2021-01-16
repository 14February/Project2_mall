package com.cskaoyan.controller.backstage.marketManagementController;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandCreat;
import com.cskaoyan.bean.backstage.ListData;

import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.MarketManageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/admin/brand")
public class AdminBrandController{

    @Autowired
    MarketManageService marketManageService;
    @Autowired
    OperationMap operationMap;

    @RequestMapping("/list")
    public BaseRespVo brandList(Integer page,Integer limit,String sort,String order,Integer id,String name){
        ListData<Brand> brandList= marketManageService.queryBrandList(page,limit,sort,order,id,name);
        return BaseRespVo.ok(brandList);
    }


    @RequestMapping("/create")
    public BaseRespVo brandCreat(@RequestBody BrandCreat brandCreat, HttpServletRequest request){
        BrandCreat creat = marketManageService.brandCreat(brandCreat);
        operationMap.insertOperations(request, SecurityUtils.getSubject(),OperationMap.operations.get(25), true);
        return BaseRespVo.ok(creat);
    }

    @RequestMapping("/update")
    public BaseRespVo brandUpdate(@RequestBody Brand items,HttpServletRequest request){
        operationMap.insertOperations(request, SecurityUtils.getSubject(),OperationMap.operations.get(26), true);
        Brand item = marketManageService.brandUpdate(items);
        return BaseRespVo.ok(item);
    }

    @RequestMapping("/delete")
    public BaseRespVo brandDelete(@RequestBody Brand items,HttpServletRequest request){
        operationMap.insertOperations(request, SecurityUtils.getSubject(),OperationMap.operations.get(27), true);
        marketManageService.brandDelete(items);
        return BaseRespVo.ok();
    }

}
