package com.cskaoyan.controller.backstage.marketManagementController;

import com.cskaoyan.bean.backstage.marketManagement.adminRegion.AdminRegion;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.service.MarketManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminRegionController{

    @Autowired
    MarketManageService marketManageService;

    @RequestMapping("/admin/region/list")

    public BaseRespVo regionList(){
        List<AdminRegion> adminRegions = marketManageService.regionList();
        return BaseRespVo.ok(adminRegions);
    }

}
