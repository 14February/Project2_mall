package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.feedback.WxFeedbackBo;
import com.cskaoyan.service.UserService;
import com.cskaoyan.service.WxFeedbackService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 21:19
 */
@RestController
@RequestMapping("wx/feedback")
public class FeedbackController{

    @Autowired
    WxFeedbackService wxFeedbackService;
    @Autowired
    UserService userService;

    @RequestMapping("submit")
    public BaseRespVo submit(@RequestBody WxFeedbackBo feedbackBo){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        Integer userId = userService.selectUserByUsername(username);
        wxFeedbackService.sumbitFeedback(feedbackBo,userId,username);
        return BaseRespVo.ok();
    }
}
