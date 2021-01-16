package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.wx.CollectionList;

import java.util.Map;

public interface CollectionService {

    CollectionList collectList(Integer type, Integer page, Integer size);

    Map<String, String> collectionAddordelete(Map map);
}
