package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Ad;
import com.cskaoyan.bean.backstage.AdExample;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Log;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.mapper.backstage.AdMapper;
import com.cskaoyan.mapper.backstage.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdServiceImpl implements AdService{
    @Autowired
    AdMapper adMapper;

    @Override
    public ListData<Ad> queryList(Integer page, Integer limit, String sort, String order, String name, String content) {
        PageHelper.startPage(page, limit);
        AdExample example = new AdExample();
        example.setOrderByClause(sort + " " + order);
        AdExample.Criteria criteria = example.createCriteria();
        if(name != null && !name.equals("")) criteria.andNameLike("%" + name + "%");
        if(content != null && !content.equals("")) criteria.andContentLike("%" + content + "%");
        List<Ad> ads = adMapper.selectByExample(example);
        PageInfo<Ad> adPageInfo = new PageInfo<>(ads);
        int total = (int)adPageInfo.getTotal();
        return ListData.data(total, ads);
    }

    @Override
    public Ad update(Ad ad) {
        AdExample example = new AdExample();
        AdExample.Criteria criteria = example.createCriteria();
        ad.setUpdateTime(new Date());
        if(ad.getId() != null) criteria.andIdEqualTo(ad.getId());
        int i = adMapper.updateByExample(ad, example);
        Ad updateAd = adMapper.selectByPrimaryKey(ad.getId());
        return updateAd;
    }

    @Override
    public void delete(Ad ad) {
        ad.setDeleted(true);
        int delete = adMapper.updateByPrimaryKey(ad);
    }

    @Override
    public Ad create(Ad ad) {
        ad.setUpdateTime(new Date());
        ad.setAddTime(new Date());
        int insert = adMapper.insertSelective(ad);
        Ad insertAd = adMapper.selectByPrimaryKey(ad.getId());
        return insertAd;
    }
}
