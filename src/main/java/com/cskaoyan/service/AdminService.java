package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.admin.Admin;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.user.UserInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *
 */
public interface AdminService{
    ListData<Admin> queryAdminList(Integer page,Integer limit,String sort,String order,String username);

    void update(Admin admin);

    void create(Admin admin);

    void deleteAdmin(Admin admin);

    UserInfo queryAdminByUsername(PrincipalCollection principals);
}
