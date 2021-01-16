package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Log;

/**
 *
 */
public interface LogService{
    ListData<Log> queryLogList(Integer page,Integer limit,String sort,String order,String name);
}
