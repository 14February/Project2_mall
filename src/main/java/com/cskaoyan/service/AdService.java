package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Ad;
import com.cskaoyan.bean.backstage.ListData;

public interface AdService {
    ListData<Ad> queryList(Integer page, Integer limit, String sort, String order, String name, String content);

    Ad update(Ad ad);

    void delete(Ad ad);

    Ad create(Ad ad);
}
