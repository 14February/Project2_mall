package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.permission.Perm;
import com.cskaoyan.bean.backstage.permission.PermExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermMapper{
    long countByExample(PermExample example);

    int deleteByExample(PermExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Perm record);

    int insertSelective(Perm record);

    List<Perm> selectByExample(PermExample example);

    Perm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Perm record, @Param("example") PermExample example);

    int updateByExample(@Param("record") Perm record, @Param("example") PermExample example);

    int updateByPrimaryKeySelective(Perm record);

    int updateByPrimaryKey(Perm record);
}