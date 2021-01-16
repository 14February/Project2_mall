package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.ListData2;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.backstage.TopicExample;
import com.cskaoyan.bean.wx.TopicDetailVo;
import com.cskaoyan.mapper.backstage.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: WxTopicServiceImpl
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 19:32
 */
@Service
public class WxTopicServiceImpl implements WxTopicService {
    @Autowired
    TopicMapper topicMapper;
    @Override
    public ListData2<Topic> list(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        TopicExample topicExample = new TopicExample();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        int count = (int) topicPageInfo.getTotal();
        return ListData2.data(count,topics);
    }

    @Override
    public TopicDetailVo detail(Integer id) {
        Topic topic = topicMapper.selectByPrimaryKey(id);
        String[] goods = topic.getGoods();
        TopicDetailVo topicDetailVo = new TopicDetailVo();
        topicDetailVo.setGoods(goods);
        topicDetailVo.setTopic(topic);
        return topicDetailVo;
    }

    @Override
    public List<Topic> related(Integer id) {
        List<Topic> topicList=topicMapper.selectTopicByRelatde(id);
        return topicList;
    }
}
