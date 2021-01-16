package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Log;
import com.cskaoyan.bean.backstage.LogExample;
import com.cskaoyan.mapper.backstage.LogMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class LogServiceImpl implements LogService{

    @Autowired
    LogMapper logMapper;

    @Override
    public ListData<Log> queryLogList(Integer page,Integer limit,String sort,String order,String name)
    {
        PageHelper.startPage(page, limit);
        LogExample example = new LogExample();
        example.setOrderByClause(sort + " " + order);

        LogExample.Criteria criteria = example.createCriteria();
        if(name != null) criteria.andAdminLike("%" + name + "%");

        List<Log> logs = logMapper.selectByExample(example);
        PageInfo<Log> userPageInfo = new PageInfo<>(logs);
        int total = (int)userPageInfo.getTotal();   //符合条件的总记录数
        return ListData.data(total, logs);
    }
}
