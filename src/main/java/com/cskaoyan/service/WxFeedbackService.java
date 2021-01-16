package com.cskaoyan.service;
import com.cskaoyan.bean.wx.feedback.WxFeedbackBo;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 21:21
 */
public interface WxFeedbackService {
    void sumbitFeedback(WxFeedbackBo feedbackBo,Integer userId,String username);
}
