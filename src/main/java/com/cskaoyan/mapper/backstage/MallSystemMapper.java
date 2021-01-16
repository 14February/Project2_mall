package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.ConfigOrder;

import com.cskaoyan.bean.backstage.ConfigWx;
import com.cskaoyan.bean.backstage.MallSystem;
import com.cskaoyan.bean.backstage.MallSystemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MallSystemMapper {
    Integer selectGoodsTotal();
    Integer selectUserTotal();
    Integer selectProductTotal();
    Integer selectOrderTotal();
    long countByExample(MallSystemExample example);

    int deleteByExample(MallSystemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MallSystem record);

    int insertSelective(MallSystem record);

    List<MallSystem> selectByExample(MallSystemExample example);

    MallSystem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MallSystem record, @Param("example") MallSystemExample example);

    int updateByExample(@Param("record") MallSystem record, @Param("example") MallSystemExample example);

    int updateByPrimaryKeySelective(MallSystem record);

    int updateByPrimaryKey(MallSystem record);

    int updateCommentConfigByKeyValue(ConfigOrder configOrder);

    int updateUnconfirmConfigByKeyValue(ConfigOrder configOrder);

    int updateUnpaidConfigByKeyValue(ConfigOrder configOrder);

    int updateCatlogGoodsByKeyName(ConfigWx configWx);
    int updateCatlogListByKeyName(ConfigWx configWx);
    int updateIndexBrandByKeyName(ConfigWx configWx);
    int updateIndexHotByKeyName(ConfigWx configWx);
    int updateIndexNewByKeyName(ConfigWx configWx);
    int updateIndexTopicByKeyName(ConfigWx configWx);

}