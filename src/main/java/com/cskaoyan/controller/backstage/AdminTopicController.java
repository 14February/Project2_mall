package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.TopicService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("admin/topic")
public class AdminTopicController{
    @Autowired
    TopicService topicService;
    @Autowired
    OperationMap operationMap;
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String title, String subtitle, String sort, String order){
        ListData<Topic> topics = topicService.queryTopicList(page, limit, title, subtitle, sort, order);
        return BaseRespVo.ok(topics);
    }
    @PostMapping("update")
    public BaseRespVo update(HttpServletRequest request, @RequestBody Topic topic) {
        Topic updateTopic = topicService.update(topic);
        operationMap.insertOperations(request, SecurityUtils.getSubject(),OperationMap.operations.get(16), true);
        return BaseRespVo.ok(updateTopic);
    }
    @PostMapping("delete")
    public BaseRespVo delete(HttpServletRequest request, @RequestBody Topic topic) {
        topicService.delete(topic);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(17), true);
        return BaseRespVo.ok();
    }
    @PostMapping("create")
    public BaseRespVo create(HttpServletRequest request, @RequestBody Topic topic) {
        Topic insertTopic = topicService.create(topic);
        operationMap.insertOperations(request,SecurityUtils.getSubject(),OperationMap.operations.get(15), true);
        return BaseRespVo.ok(insertTopic);
    }
}
