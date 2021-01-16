package com.cskaoyan.mapper;

import com.cskaoyan.bean.wx.feedback.WxFeedback;
import com.cskaoyan.bean.wx.feedback.WxFeedbackExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxFeedbackMapper {
    long countByExample(WxFeedbackExample example);

    int deleteByExample(WxFeedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WxFeedback record);

    int insertSelective(WxFeedback record);

    List<WxFeedback> selectByExample(WxFeedbackExample example);

    WxFeedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WxFeedback record, @Param("example") WxFeedbackExample example);

    int updateByExample(@Param("record") WxFeedback record, @Param("example") WxFeedbackExample example);

    int updateByPrimaryKeySelective(WxFeedback record);

    int updateByPrimaryKey(WxFeedback record);
}