package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.groupon.GrouponGoods;
import com.cskaoyan.bean.backstage.groupon.GrouponRules;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GrouponRulesMapper {
    int deleteByPrimaryKey(Integer id);

    GrouponRules selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GrouponRules record);

    int updateByPrimaryKey(GrouponRules record);

    List<GrouponRules> selectByPrimaryKeySelective(@Param("goodsId") Integer goodsId);

    int insertRule(@Param("grouponRules") GrouponRules grouponRules);

    GrouponGoods selectGoodsById(Integer id);

    int updateDeletedById(Integer id);


}