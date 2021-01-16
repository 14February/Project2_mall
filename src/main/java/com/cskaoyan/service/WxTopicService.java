package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData2;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.wx.TopicDetailVo;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: WxTopicService
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 19:30
 */
public interface WxTopicService {

    ListData2<Topic> list(Integer page, Integer size);

    TopicDetailVo detail(Integer id);

    List<Topic> related(Integer id);
}
