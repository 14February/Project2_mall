package com.cskaoyan.service;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.keyword.Keyword;
import com.cskaoyan.bean.backstage.keyword.KeywordExample;
import com.cskaoyan.mapper.backstage.KeywordMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: KeyWordServiceImpl
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 17:02
 */
@Service
public class KeyWordServiceImpl implements KeyWordService {
    @Autowired
    KeywordMapper keywordMapper;
    @Override
    public ListData<Keyword> list(Integer page, Integer limit, String keyword, String url, String sort, String order) {
        PageHelper.startPage(page,limit);
        KeywordExample example = new KeywordExample();
        KeywordExample.Criteria criteria = example.createCriteria();
        if (keyword!=null)criteria.andKeywordLike("%"+keyword+"%");
        if (url!=null)criteria.andUrlEqualTo(url);
        List<Keyword> keywords = keywordMapper.selectByExample(example);
        PageInfo<Keyword> keywordPageInfo = new PageInfo<>();
        int total = (int) keywordPageInfo.getTotal();
        return ListData.data(total,keywords);
    }

    @Override
    public Keyword create(Keyword keyword) {
        keyword.setSortOrder(100);
        keyword.setAddTime(new Date());
        keyword.setUpdateTime(new Date());
        keyword.setDeleted(false);
        keywordMapper.insert(keyword);
        Integer id = keyword.getId();
        Keyword keyword1 = keywordMapper.selectByPrimaryKey(id);
        return keyword1;
    }

    @Override
    public Keyword update(Keyword keyword) {
        keyword.setUpdateTime(new Date());
        keywordMapper.updateByPrimaryKeySelective(keyword);
        Integer id = keyword.getId();
        Keyword keyword1 = keywordMapper.selectByPrimaryKey(id);
        return keyword1;
    }

    @Override
    public int delete(Keyword keyword) {
        Integer id = keyword.getId();
        int i = keywordMapper.deleteByPrimaryKey(id);
        return i;
    }
}
