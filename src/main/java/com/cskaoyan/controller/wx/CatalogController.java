package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.CatalogIndex;
import com.cskaoyan.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wx/catalog")
public class CatalogController {

    @Autowired
    CatalogService catalogService;

    @RequestMapping("/index")
    public BaseRespVo catalogIndex(){
        CatalogIndex catalogIndex = catalogService.catalogIndex();
        return BaseRespVo.ok(catalogIndex);
    }
    @RequestMapping("/current")
    public BaseRespVo catalogCurrent(Integer id){
        CatalogIndex catalogCurrent =  catalogService.catalogCurrent(id);
        return BaseRespVo.ok(catalogCurrent);
    }
}
