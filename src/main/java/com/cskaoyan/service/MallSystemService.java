package com.cskaoyan.service;


import com.cskaoyan.bean.backstage.ConfigExpress;
import com.cskaoyan.bean.backstage.ConfigOrder;
import com.cskaoyan.bean.backstage.ConfigWx;

import com.cskaoyan.bean.backstage.IndexVo;
import com.cskaoyan.bean.backstage.ListData;

import com.cskaoyan.bean.backstage.MallSystemConfig;
import com.cskaoyan.bean.backstage.Storage;


public interface MallSystemService {
    /**
     * 查询商城的地址，名字，电话，qq
     * @return
     */
    MallSystemConfig queryConfig();

    /**
     * 修改商城的地址，名字，电话，qq
     * @param config
     * @return
     */
    int updateConfig(MallSystemConfig config);


    /**
     * 查询商城运费配置信息
     * @return
     */
    ConfigExpress queryExpressConfig();

    /**
     * 修改运费配置
     * @param expressFreightValue
     * @param expressFreightMin
     * @return
     */
    int updateExpress(String expressFreightValue, String expressFreightMin);

    /**
     * 查询订单配置
     * @return
     */
    ConfigOrder queryConfigOrder();

    /**
     * 修改订单配置
     * @param configOrder
     * @return
     */
    int updateOrderConfig(ConfigOrder configOrder);

    /**
     * 查询微信小程序配置信息
     * @return
     */
    ConfigWx queryWxConfig();

    /**
     * 修改微信小程序配置信息
     * @param configWx
     * @return
     */
    int updateWxConfig(ConfigWx configWx);

    /*
     *  首页信息
     */
    IndexVo selectTotals();

    ListData<Storage> queryStorageList(Integer page, Integer limit, String sort, String order, String key, String name);

    Storage updateStorage(Storage updateStorageBo);

    int deleteStorage(Storage deleteStorageBo);

    int createStorage(Storage storage);

}
