package com.cskaoyan.service;


import com.cskaoyan.bean.backstage.*;
import com.cskaoyan.mapper.backstage.MallSystemMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.cskaoyan.mapper.backstage.StorageMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.util.*;

@Service
public class MallSystemServiceImpl implements MallSystemService {

    @Autowired
    MallSystemMapper mallSystemMapper;
    @Autowired
    StorageMapper storageMapper;

    SqlSession sqlSession;
    static SqlSessionFactory sqlSessionFactory;
    MallSystemMapper mapper;


    public void init() throws IOException {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        Reader reader = Resources.getResourceAsReader("mybatis.xml");
        sqlSessionFactory=builder.build(reader);
        sqlSession=sqlSessionFactory.openSession();
        mapper=sqlSession.getMapper(MallSystemMapper.class);
    }

    public void commit(){
        if(sqlSession!=null){
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Override
    public MallSystemConfig queryConfig() {
        MallSystemConfig mallSystemConfig = new MallSystemConfig();
        List<MallSystem> systems = mallSystemMapper.selectByExample(null);
        for (MallSystem system : systems) {
            if("cskaoyan_mall_mall_address".equals(system.getKeyName())){
                mallSystemConfig.setCskaoyanmall_mall_address(system.getKeyValue());
            }
            if("cskaoyan_mall_mall_name".equals(system.getKeyName())){
                mallSystemConfig.setCskaoyanmall_mall_name(system.getKeyValue());
            }
            if("cskaoyan_mall_mall_phone".equals(system.getKeyName())){
                mallSystemConfig.setCskaoyanmall_mall_phone(system.getKeyValue());
            }
            if("cskaoyan_mall_mall_qq".equals(system.getKeyName())){
                mallSystemConfig.setCskaoyanmall_mall_qq(system.getKeyValue());
            }
        }
        return mallSystemConfig;
    }

    @Override
    public int updateConfig(MallSystemConfig config) {
        //修改地址
        MallSystemExample example = new MallSystemExample();
        MallSystemExample.Criteria criteria = example.createCriteria();
        criteria.andKeyNameEqualTo("cskaoyan_mall_mall_address");
        MallSystem mallAddress = new MallSystem(null,"cskaoyan_mall_mall_address", config.getCskaoyanmall_mall_address(),
                null, new Date(), null);
        int code = mallSystemMapper.updateByExampleSelective(mallAddress, example);
        //修改商城名字
        MallSystemExample example1 = new MallSystemExample();
        MallSystemExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andKeyNameEqualTo("cskaoyan_mall_mall_name");
        MallSystem mallAddress1 = new MallSystem(null, null, config.getCskaoyanmall_mall_name(),
                null, new Date(), null);
        int code1 = mallSystemMapper.updateByExampleSelective(mallAddress1, example1);
        //修改qq
        MallSystemExample example2 = new MallSystemExample();
        MallSystemExample.Criteria criteria2 = example2.createCriteria();
        criteria2.andKeyNameEqualTo("cskaoyan_mall_mall_qq");
        MallSystem mallAddress2 = new MallSystem(null, null, config.getCskaoyanmall_mall_qq(),
                null, new Date(), null);
        int code2 = mallSystemMapper.updateByExampleSelective(mallAddress2, example2);
        //修改phone
        MallSystemExample example3 = new MallSystemExample();
        MallSystemExample.Criteria criteria3 = example3.createCriteria();
        criteria3.andKeyNameEqualTo("cskaoyan_mall_mall_phone");
        MallSystem mallAddress3 = new MallSystem(null, null, config.getCskaoyanmall_mall_phone(),
                null, new Date(), null);
        int code3 = mallSystemMapper.updateByExampleSelective(mallAddress3, example3);
        return code+code1+code2+code3;
    }

    @Override

    public ConfigExpress queryExpressConfig() {
        ConfigExpress express = new ConfigExpress();
        List<MallSystem> exps = mallSystemMapper.selectByExample(null);
        for (MallSystem exp : exps) {
            if("cskaoyan_mall_express_freight_min".equals(exp.getKeyName())){
                express.setCskaoyanmall_express_freight_min(exp.getKeyValue());
            }
            if("cskaoyan_mall_express_freight_value".equals(exp.getKeyName())){
                express.setCskaoyanmall_express_freight_value(exp.getKeyValue());
            }
        }
        return express;
    }

    @Override
    public int updateExpress(String expressFreightValue, String expressFreightMin) {
        //修改运费满减所需最低消费
        MallSystemExample example = new MallSystemExample();
        MallSystemExample.Criteria criteria = example.createCriteria();
        criteria.andKeyNameEqualTo("cskaoyan_mall_express_freight_min");
        MallSystem mallSystem = new MallSystem(null, null, expressFreightMin, null, new Date(), null);
        int code = mallSystemMapper.updateByExampleSelective(mallSystem, example);

        //修改运费满减不足所需运费
        MallSystemExample example1 = new MallSystemExample();
        MallSystemExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andKeyNameEqualTo("cskaoyan_mall_express_freight_value");
        MallSystem mallSystem1 = new MallSystem(null, null, expressFreightValue, null, new Date(), null);
        int code1 = mallSystemMapper.updateByExampleSelective(mallSystem1, example1);
        return code+code1;
    }

    @Override
    public ConfigOrder queryConfigOrder() {
        ConfigOrder configOrder = new ConfigOrder();
        List<MallSystem> mallSystems = mallSystemMapper.selectByExample(null);
        for (MallSystem mallSystem : mallSystems) {
            if("cskaoyan_mall_order_comment".equals(mallSystem.getKeyName()))
                configOrder.setCskaoyanmall_order_comment(mallSystem.getKeyValue());
            if("cskaoyan_mall_order_unconfirm".equals(mallSystem.getKeyName()))
                configOrder.setCskaoyanmall_order_unconfirm(mallSystem.getKeyValue());
            if("cskaoyan_mall_order_unpaid".equals(mallSystem.getKeyName()))
                configOrder.setCskaoyanmall_order_unpaid(mallSystem.getKeyValue());
        }
        return configOrder;
    }

    @Override
    public int updateOrderConfig(ConfigOrder configOrder) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int code1 = mapper.updateCommentConfigByKeyValue(configOrder);
        int code2 = mapper.updateUnconfirmConfigByKeyValue(configOrder);
        int code3 = mapper.updateUnpaidConfigByKeyValue(configOrder);
        commit();
        return code1+code2+code3;
    }

    @Override
    public ConfigWx queryWxConfig() {
        ConfigWx configWx = new ConfigWx();
        List<MallSystem> mallSystems = mallSystemMapper.selectByExample(null);
        for (MallSystem mallSystem : mallSystems) {
            if("cskaoyan_mall_wx_share".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_share(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_index_brand".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_index_brand(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_index_topic".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_index_topic(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_index_hot".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_index_hot(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_catlog_goods".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_catlog_goods(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_catlog_list".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_catlog_list(mallSystem.getKeyValue());
            if("cskaoyan_mall_wx_index_new".equals(mallSystem.getKeyName()))
                configWx.setCskaoyanmall_wx_index_new(mallSystem.getKeyValue());
        }
        return configWx;
    }

    @Override
    public int updateWxConfig(ConfigWx configWx) {
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int i1 = mapper.updateCatlogGoodsByKeyName(configWx);
        int i2 = mapper.updateCatlogListByKeyName(configWx);
        int i3 = mapper.updateIndexBrandByKeyName(configWx);
        int i4 = mapper.updateIndexHotByKeyName(configWx);
        int i5 = mapper.updateIndexNewByKeyName(configWx);
        int i6 = mapper.updateIndexTopicByKeyName(configWx);
        commit();
        return i1+i2+i3+i4+i5+i6;
    }

    public IndexVo selectTotals()
    {
        IndexVo indexVo = new IndexVo();
        indexVo.setGoodsTotal(mallSystemMapper.selectGoodsTotal());
        indexVo.setUserTotal(mallSystemMapper.selectUserTotal());
        indexVo.setProductTotal(mallSystemMapper.selectProductTotal());
        indexVo.setOrderTotal(mallSystemMapper.selectOrderTotal());
        return indexVo;
    }

    @Override
    public ListData<Storage> queryStorageList(Integer page,Integer limit,String sort,String order,String key,String name) {
        PageHelper.startPage(page, limit);
        StorageExample example = new StorageExample();
        example.setOrderByClause(sort + " " + order);
        StorageExample.Criteria criteria = example.createCriteria();
        if(key != null && key.length() != 0) criteria.andKeyEqualTo(key);
        if(name != null && name.length() != 0) criteria.andNameLike(name);
        List<Storage> storages = storageMapper.selectByExample(example);
        PageInfo<Storage> storagesPageInfo = new PageInfo<>(storages);
        int total = (int)storagesPageInfo.getTotal();
        return ListData.data(total, storages);
    }



    @Override
    public Storage updateStorage(Storage updateStorageBo) {
        storageMapper.updateByPrimaryKeySelective(updateStorageBo);
        Storage updateStorageVo = storageMapper.selectByPrimaryKey(updateStorageBo.getId());
        return  updateStorageVo;
    }

    @Override
    public int deleteStorage(Storage deleteStorageBo) {
        int i = storageMapper.deleteByPrimaryKey(deleteStorageBo.getId());
        return i;
    }

    @Override
    public int createStorage(Storage storage) {
        int i = storageMapper.insertSelective(storage);
        return i;
    }

}
