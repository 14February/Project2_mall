package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.keyword.Keyword;
import com.cskaoyan.bean.backstage.keyword.KeywordExample;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistory;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistoryExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.SearchIndexVo;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.KeywordMapper;
import com.cskaoyan.mapper.backstage.SearchHistoryMapper;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: WxSearchServiceImpl
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 14:36
 */
@Service
public class WxSearchServiceImpl implements WxSearchService {
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public SearchIndexVo index(String keywordlike) {
        SearchIndexVo searchIndexVo = new SearchIndexVo();
        Keyword keyword = keywordMapper.selectByPrimaryKey(1);
        SearchIndexVo.DefaultKeywordBean defaultKeywordBean = new SearchIndexVo.DefaultKeywordBean(keyword.getId(), keyword.getKeyword(),
                keyword.getUrl(), keyword.getIsHot(), keyword.getIsDefault(),
                keyword.getSortOrder(), keyword.getAddTime(), keyword.getUpdateTime(),
                keyword.getDeleted());

        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        List<SearchHistory> searchHistories = searchHistoryMapper.selectByExample(searchHistoryExample);
        ArrayList<SearchIndexVo.HistoryKeywordListBean> historyKeywordList = new ArrayList<>();
        for (SearchHistory searchHistory : searchHistories) {
            SearchIndexVo.HistoryKeywordListBean historyKeywordListBean = new SearchIndexVo.HistoryKeywordListBean();
            historyKeywordListBean.setKeyword(searchHistory.getKeyword());
            historyKeywordList.add(historyKeywordListBean);
        }

        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        criteria.andIsHotEqualTo(true);
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        ArrayList<SearchIndexVo.HotKeywordListBean> hotKeywordList = new ArrayList<>();

        for (Keyword keyword1 : keywords) {
            SearchIndexVo.HotKeywordListBean hotKeywordListBean = new SearchIndexVo.HotKeywordListBean();
            hotKeywordListBean.setId(keyword1.getId());
            hotKeywordListBean.setHot(keyword1.getIsHot());
            hotKeywordListBean.setDefault(keyword1.getIsDefault());
            hotKeywordListBean.setKeyword(keyword1.getKeyword());
//            hotKeywordListBean.setSortOrder(keyword1.getSortOrder());
            hotKeywordListBean.setAddTime(keyword1.getAddTime());
            hotKeywordListBean.setUpdateTime(keyword1.getUpdateTime());
            hotKeywordListBean.setUrl(keyword1.getUrl());
            hotKeywordListBean.setDeleted(keyword1.getDeleted());
            hotKeywordList.add(hotKeywordListBean);
        }
        searchIndexVo.setDefaultKeyword(defaultKeywordBean);
        searchIndexVo.setHistoryKeywordList(historyKeywordList);
        searchIndexVo.setHotKeywordList(hotKeywordList);

        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        Integer id = user.getId();
        if (keywordlike!=null) {
            SearchHistory searchHistory = new SearchHistory();
            searchHistory.setKeyword(keywordlike);
            searchHistory.setUserId(id);
            searchHistory.setFrom("wx");
            searchHistory.setAddTime(new Date());
            searchHistory.setUpdateTime(new Date());
            searchHistory.setDeleted(true);
            searchHistoryMapper.insertSelective(searchHistory);
        }
        return searchIndexVo;
    }

    @Override
    public List<String> helper(String keyword) {
        KeywordExample keywordExample = new KeywordExample();
        KeywordExample.Criteria criteria = keywordExample.createCriteria();
        if(keyword!=null)criteria.andKeywordLike("%"+keyword+"%");
        List<Keyword> keywords = keywordMapper.selectByExample(keywordExample);
        ArrayList<String> list = new ArrayList<>();
        for (Keyword keywordBean : keywords) {
            String keyword1 = keywordBean.getKeyword();
            list.add(keyword1);
        }
        return list;
    }

    @Override
    public int clearhistory() {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        int i = searchHistoryMapper.deleteByExample(searchHistoryExample);
        return i;
    }
}
