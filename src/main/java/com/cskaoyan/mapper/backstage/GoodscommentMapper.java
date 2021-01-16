package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.bean.backstage.goodsbean.GoodsCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodscommentMapper {
    long countByExample(GoodsCommentExample example);

    int deleteByExample(GoodsCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsComment record);

    int insertSelective(GoodsComment record);

    List<GoodsComment> selectByExample(GoodsCommentExample example);

    GoodsComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsComment record,@Param("example") GoodsCommentExample example);

    int updateByExample(@Param("record") GoodsComment record,@Param("example") GoodsCommentExample example);

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);


}