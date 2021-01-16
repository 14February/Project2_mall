package com.cskaoyan.mapper;


import com.cskaoyan.bean.wx.order.WxComment;
import com.cskaoyan.bean.wx.order.WxCommentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxCommentMapper {
    long countByExample(WxCommentExample example);

    int deleteByExample(WxCommentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxComment record);

    int insertSelective(WxComment record);

    List<WxComment> selectByExample(WxCommentExample example);

    WxComment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxComment record, @Param("example") WxCommentExample example);

    int updateByExample(@Param("record") WxComment record, @Param("example") WxCommentExample example);

    int updateByPrimaryKeySelective(WxComment record);

    int updateByPrimaryKey(WxComment record);
}