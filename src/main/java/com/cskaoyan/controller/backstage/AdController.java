package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.backstage.Ad;
import com.cskaoyan.bean.backstage.LogExample;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.AdService;
import com.cskaoyan.service.LogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin/ad")
public class AdController{
    @Autowired
    AdService adService;
    @Autowired
    LogService logService;
    @Autowired
    OperationMap operationMap;
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, String name, String content) {
        ListData<Ad> listData = adService.queryList(page, limit, sort, order, name, content);
        LogExample logExample = new LogExample();
        return BaseRespVo.ok(listData);
    }

    @RequestMapping("update")
    public BaseRespVo update(HttpServletRequest request, @RequestBody Ad ad) {
        Ad updateAd = adService.update(ad);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(10), true);
        return BaseRespVo.ok(updateAd);
    }
    @PostMapping("delete")
    public BaseRespVo delete(HttpServletRequest request, @RequestBody Ad ad) {
        adService.delete(ad);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(11), true);
        return BaseRespVo.ok();
    }
    @PostMapping("create")
    public BaseRespVo create(HttpServletRequest request, @RequestBody Ad ad) {
        Ad insertAd = adService.create(ad);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(9), true);
        return BaseRespVo.ok(insertAd);
    }
}

