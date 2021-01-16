package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.IndexVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Log;
import com.cskaoyan.bean.backstage.admin.Admin;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.AdminService;
import com.cskaoyan.service.LogService;
import com.cskaoyan.service.MallSystemService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 */
@RequestMapping("admin")
@RestController
public class AdminController{

    @Autowired
    MallSystemService mallSystemService;

    @Autowired
    AdminService adminService;

    @Autowired
    LogService logService;

    @Autowired
    OperationMap operationMap;

    @RequestMapping("dashboard")
    public BaseRespVo<IndexVo> dashboard()
    {
        IndexVo indexVo = mallSystemService.selectTotals();
        return BaseRespVo.ok(indexVo);
    }

    @RequestMapping("admin/list")
    public BaseRespVo<ListData<Admin>> adminList(Integer page,Integer limit,String sort,String order,String username)
    {
        ListData<Admin> listData = adminService.queryAdminList(page,limit,sort,order,username);

        return BaseRespVo.ok(listData);
    }

    @RequestMapping("log/list")
    public BaseRespVo<ListData<Log>> logList(Integer page,Integer limit,String sort,String order,String name)
    {
        ListData<Log> listData = logService.queryLogList(page,limit,sort,order,name);

        return BaseRespVo.ok(listData);
    }


    @RequestMapping("admin/update")
    public BaseRespVo<Admin> update(HttpServletRequest request,@RequestBody Admin admin)
    {

        admin.setUpdateTime(new Date());
        adminService.update(admin);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(3), true);
        return BaseRespVo.ok(admin);
    }

    @RequestMapping("admin/create")
    public BaseRespVo<Admin> create(HttpServletRequest request,@RequestBody Admin admin)
    {
        String username = admin.getUsername();
        if(!username.matches("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+")) return BaseRespVo.fail(601,"管理员名称不符合规定");
        String password = admin.getPassword();
        if(password.length() < 6) return BaseRespVo.fail(602,"管理员密码长度不能小于6");
        Date date = new Date();
        admin.setUpdateTime(date);
        admin.setAddTime(date);
        adminService.create(admin);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(2), true);
        return BaseRespVo.ok(admin);
    }

    @RequestMapping("admin/delete")
    public BaseRespVo<Object> delete(HttpServletRequest request,@RequestBody Admin admin)
    {
        adminService.deleteAdmin(admin);
        operationMap.insertOperations(request,SecurityUtils.getSubject(), OperationMap.operations.get(4), true);
        return BaseRespVo.ok();
    }
}
