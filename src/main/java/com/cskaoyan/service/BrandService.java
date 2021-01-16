package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.wx.BrandList;

public interface BrandService {
    BrandList brandList(Integer page, Integer size);

    Brand brandDetail(Integer id);

}
