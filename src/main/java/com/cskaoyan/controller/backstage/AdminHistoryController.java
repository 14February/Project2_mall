package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.searchHistory.SearchHistory;
import com.cskaoyan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-10 15:26
 */
@RestController
@RequestMapping("admin/history")
public class AdminHistoryController{
    @Autowired
    UserService userService;
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, Integer userId, String keyword){
        ListData<SearchHistory> searchHistoryListData = userService.querySearchHistoryList(page,limit,sort,order,userId,keyword);
        return BaseRespVo.ok(searchHistoryListData);
     }
}
