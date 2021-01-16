package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.groupon.Groupon;

import java.util.List;


public interface GrouponMapper {
    int deleteByPrimaryKey(Integer id);

    Groupon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Groupon record);

    int updateByPrimaryKey(Groupon record);

    List<Groupon> selectGroupon(Integer id);
}