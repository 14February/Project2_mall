package com.cskaoyan.service;

import com.cskaoyan.bean.wx.feedback.WxFeedback;
import com.cskaoyan.bean.wx.feedback.WxFeedbackBo;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.WxFeedbackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 21:21
 */
@Service
public class WxFeedbackServiceImpl implements WxFeedbackService {
    @Autowired
    WxFeedbackMapper wxFeedbackMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public void sumbitFeedback(WxFeedbackBo feedbackBo,Integer userId,String username) {
       WxFeedback wxFeedback = new WxFeedback();
      // Integer userId = userMapper.selectUserIdByMobile(feedbackBo.getMobile());
       //String username = userMapper.selectUsernameByMobile(feedbackBo.getMobile());
       wxFeedback.setAddTime(new Date());
       wxFeedback.setUpdateTime(new Date());
       wxFeedback.setContent(feedbackBo.getContent());
       wxFeedback.setHasPicture(feedbackBo.isHasPicture());
       wxFeedback.setMobile(feedbackBo.getMobile());
       wxFeedback.setUserId(userId);
       wxFeedback.setUsername(username);
       wxFeedback.setPicUrls(Arrays.toString(feedbackBo.getPicUrls()));
       wxFeedback.setStatus(1);
       wxFeedback.setFeedType(feedbackBo.getFeedType());
       wxFeedbackMapper.insertSelective(wxFeedback);
    }
}
