package com.cskaoyan.service;

import com.cskaoyan.bean.wx.SearchIndexVo;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: WxSearchService
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 14:35
 */
public interface WxSearchService {


    List<String> helper(String keyword);

    int clearhistory();

    SearchIndexVo index(String keyword);
}
