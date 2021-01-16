package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.groupon.GrouponRules;

public interface GrouponService {
    /**
     * 返回团购规则
     * @param page
     * @param limit
     * @param goodsId
     * @param sort
     * @param order
     * @return
     */
    ListData<GrouponRules> getGrouponRules(Integer page, Integer limit, Integer goodsId, String sort, String order);

    /**
     * 新建团购规则
     * @param grouponRules
     * @return
     */
    int create(GrouponRules grouponRules);

    /**
     * 修改团购规则
     * @param grouponRules
     * @return
     */
    int update(GrouponRules grouponRules);

    /**
     * 删除团购规则
     * @param grouponRules
     * @return
     */
    int delete(GrouponRules grouponRules);
}
