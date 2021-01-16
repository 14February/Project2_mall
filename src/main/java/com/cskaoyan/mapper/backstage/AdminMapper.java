package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.admin.Admin;
import com.cskaoyan.bean.backstage.admin.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper{
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectListByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    String selectPasswordByUsername(@Param("username") String username);

    List<Integer> selectRoleIdsByUsername(@Param("username") String username);
}