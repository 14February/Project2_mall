package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.keyword.Keyword;

/**
 * @ProjectName: project2
 * @ClassName: KeyWordService
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 17:02
 */
public interface KeyWordService {
    ListData<Keyword> list(Integer page, Integer limit, String keyword, String url, String sort, String order);

    Keyword create(Keyword keyword);

    Keyword update(Keyword keyword);

    int delete(Keyword keyword);
}
