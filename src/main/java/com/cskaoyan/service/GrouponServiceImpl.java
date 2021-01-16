package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.groupon.GrouponGoods;
import com.cskaoyan.bean.backstage.groupon.GrouponRules;
import com.cskaoyan.mapper.backstage.GrouponRulesMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrouponServiceImpl  implements GrouponService{

    @Autowired
    GrouponRulesMapper grouponRulesMapper;

    @Override
    public ListData<GrouponRules> getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order) {
        ListData<GrouponRules> rulesListData = new ListData<>();
        //分页
        PageHelper.startPage(page,limit);
        List<GrouponRules> grouponRules = grouponRulesMapper.selectByPrimaryKeySelective(goodsId);
        PageInfo<GrouponRules> pageInfo = new PageInfo<>(grouponRules);
        int total = (int) pageInfo.getTotal();
        rulesListData.setItems(grouponRules);
        rulesListData.setTotal(total);
        return rulesListData;
    }

    @Override
    public int create(GrouponRules grouponRules) {
        GrouponGoods grouponGoods = grouponRulesMapper.selectGoodsById(grouponRules.getGoodsId());
        if(grouponGoods==null){
            return 500;
        }
        grouponRules.setGoodsId(grouponGoods.getId());
        grouponRules.setGoodsName(grouponGoods.getName());
        grouponRules.setPicUrl(grouponGoods.getPic_url());
        return grouponRulesMapper.insertRule(grouponRules);
    }

    @Override
    public int update(GrouponRules grouponRules) {
        return grouponRulesMapper.updateByPrimaryKeySelective(grouponRules);
    }

    @Override
    public int delete(GrouponRules grouponRules) {
        return grouponRulesMapper.updateDeletedById(grouponRules.getId());
    }
}
