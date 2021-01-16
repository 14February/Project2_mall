package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.Footprint;
import com.cskaoyan.bean.backstage.FootprintExample;
import com.cskaoyan.bean.wx.footprint.FootPrintVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FootprintMapper {
    long countByExample(FootprintExample example);

    int deleteByExample(FootprintExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Footprint record);

    int insertSelective(Footprint record);

    List<Footprint> selectByExample(FootprintExample example);

    Footprint selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByExample(@Param("record") Footprint record, @Param("example") FootprintExample example);

    int updateByPrimaryKeySelective(Footprint record);

    int updateByPrimaryKey(Footprint record);

    List<FootPrintVo> queryFootprintListByUserIdWithPage(@Param("userId") Integer userId,@Param("offset") Integer offset,@Param("size") Integer size);

    int queryFootprintTotalByUserId(@Param("userId") Integer userId);

    Integer selectFootprint(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    void updateFootprint(@Param("userId") Integer userId, @Param("goodsId") Integer goodsId);
}