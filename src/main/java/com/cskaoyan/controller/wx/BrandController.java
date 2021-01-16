package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.BrandList;
import com.cskaoyan.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @RequestMapping("list")
    public BaseRespVo brandList(Integer page,Integer size){
        BrandList brandList = brandService.brandList(page,size);
        return BaseRespVo.ok(brandList);
    }
    @RequestMapping("/detail")
    public BaseRespVo brandDetail(Integer id){
        HashMap<Object, Object> map = new HashMap<>();
        Brand brand = brandService.brandDetail(id);
        map.put("brand",brand);
        return BaseRespVo.ok(map);
    }
}
