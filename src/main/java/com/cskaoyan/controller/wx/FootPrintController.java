package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.footprint.FootPrintList;
import com.cskaoyan.service.FootPrintService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 */
@RestController
@RequestMapping("wx/footprint")
public class FootPrintController{

    @Autowired
    FootPrintService footPrintService;

    @RequestMapping("list")
    public BaseRespVo<FootPrintList> list(Integer page,Integer size)
    {
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection principals = subject.getPrincipals();
        FootPrintList footprintList = footPrintService.queryFootprintList(page,size,principals.toString());
        return BaseRespVo.ok(footprintList);
    }

    @RequestMapping("delete")
    public BaseRespVo<Object> list(@RequestBody Map<String,Integer> map)
    {
        Integer id = map.get("id");
        footPrintService.deleteFootPrint(id);
        return BaseRespVo.ok();
    }
}
