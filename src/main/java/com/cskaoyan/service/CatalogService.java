package com.cskaoyan.service;

import com.cskaoyan.bean.wx.CatalogIndex;

public interface CatalogService {

    CatalogIndex catalogIndex();

    CatalogIndex catalogCurrent(Integer id);
}
