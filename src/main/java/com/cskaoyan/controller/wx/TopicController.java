package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.ListData2;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.TopicDetailVo;
import com.cskaoyan.service.WxTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: TopicController
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 19:29
 */
@RestController
@RequestMapping("wx/topic")
public class TopicController {
    @Autowired
    WxTopicService wxTopicService;
    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer size){
        ListData2<Topic> topicList = wxTopicService.list(page,size);
        return BaseRespVo.ok(topicList);
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id){
        TopicDetailVo topicDetailVo =wxTopicService.detail(id);
        return BaseRespVo.ok(topicDetailVo);
    }
    @RequestMapping("related")
    public BaseRespVo related(Integer id){
        List<Topic> topicList = wxTopicService.related(id);
        return BaseRespVo.ok(topicList);
    }

}
