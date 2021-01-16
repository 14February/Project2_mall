package com.cskaoyan.controller.backstage.marketManagementController;

import com.cskaoyan.bean.backstage.marketManagement.adminCategory.AdminCategory;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryCreate;

import com.cskaoyan.bean.marketManagement.adminCategory.CategoryL1;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.MarketManageService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/admin/category")
public class AdminCategoryController{

    @Autowired
    MarketManageService marketManageService;
    @Autowired
    OperationMap operationMap;

    @RequestMapping("/list")
    public BaseRespVo categoryList(){
        List<AdminCategory.DataDTO> category = marketManageService.categoryList();
        return BaseRespVo.ok(category);
    }
    @RequestMapping("/l1")
    public BaseRespVo categoryL1(){
      List<CategoryL1> categoryL1s =  marketManageService.categoryL1();
      return BaseRespVo.ok(categoryL1s);
    }

    @RequestMapping("/create")
    public BaseRespVo categoryCreate(@RequestBody Category category, HttpServletRequest request){
        CategoryCreate create = marketManageService.categoryCreat(category);
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(28), true);
        return BaseRespVo.ok(create);
    }
    @RequestMapping("/update")
    public BaseRespVo categoryUpdate(@RequestBody Category category,HttpServletRequest request){

        marketManageService.categoryUpdate(category);
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(29), true);
        return BaseRespVo.ok();
    }


    @RequestMapping("/delete")
    public BaseRespVo categoryDelete(@RequestBody Category category,HttpServletRequest request){
        marketManageService.categoryDelete(category);
        operationMap.insertOperations(request, SecurityUtils.getSubject(), OperationMap.operations.get(30), true);
        return BaseRespVo.ok();
    }
}
