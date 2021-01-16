package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Topic;

public interface TopicService {
    ListData<Topic> queryTopicList(Integer page, Integer limit, String title, String subtitle, String sort, String order);

    Topic update(Topic topic);

    void delete(Topic topic);

    Topic create(Topic topic);
}
