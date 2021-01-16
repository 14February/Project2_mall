package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Topic;
import com.cskaoyan.bean.backstage.TopicExample;
import com.cskaoyan.mapper.backstage.TopicMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicMapper topicMapper;

    @Override
    public Topic create(Topic topic) {
        topic.setAddTime(new Date());
        topic.setUpdateTime(new Date());
        int insert = topicMapper.insertSelective(topic);
        Topic insertTopic = topicMapper.selectByPrimaryKey(topic.getId());
        return insertTopic;
    }

    @Override
    public void delete(Topic topic) {
        topic.setDeleted(true);
        int delete = topicMapper.updateByPrimaryKey(topic);
    }

    @Override
    public Topic update(Topic topic) {
        topic.setUpdateTime(new Date());
        int update = topicMapper.updateByPrimaryKey(topic);
        Topic updateTopic = topicMapper.selectByPrimaryKey(topic.getId());
        return updateTopic;
    }

    @Override
    public ListData<Topic> queryTopicList(Integer page, Integer limit, String title, String subtitle, String sort, String order) {
       PageHelper.startPage(page, limit);
        TopicExample example = new TopicExample();
        example.setOrderByClause(sort + " " + order);
        TopicExample.Criteria criteria = example.createCriteria();
        if(title != null) criteria.andTitleLike("%" + title + "%");
        if(subtitle != null) criteria.andSubtitleLike("%" + subtitle + "%");
        List<Topic> topics = topicMapper.selectByExample(example);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        int total = (int)topicPageInfo.getTotal();
        return ListData.data(total, topics);
    }
}
