package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.Feedback;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-10 15:57
 */
@RestController
@RequestMapping("admin/feedback")
public class AdminFeedbackController{

    @Autowired
    UserService userService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, String username, Integer id){
        ListData<Feedback> feedbackListData = userService.queryFeedbackList(page,limit,sort,order,username,id);
        return BaseRespVo.ok(feedbackListData);
    }


}
