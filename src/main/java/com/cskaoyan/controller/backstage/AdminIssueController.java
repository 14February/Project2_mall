package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.CreateBo;
import com.cskaoyan.bean.backstage.Issue;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-10 16:55
 */
@RestController
@RequestMapping("admin/issue")
public class AdminIssueController{
    @Autowired
    MarketService marketService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, String question){
        ListData<Issue> issueListData = marketService.queryIssueList(page,limit,sort,order,question);
        return BaseRespVo.ok(issueListData);
    }
    @RequestMapping("create")
    public BaseRespVo create(@RequestBody CreateBo createBo){
        String question = createBo.getQuestion();
        String answer = createBo.getAnswer();
        Issue issue = marketService.insertIssue(question,answer);
        return BaseRespVo.ok(issue);
    }
    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Issue updateIssueBo){
        Issue issueVo =marketService.updateIssue(updateIssueBo);
        return BaseRespVo.ok(issueVo);
    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Issue deleteIssueBo){
       int i =  marketService.deleteIssue(deleteIssueBo);
       return BaseRespVo.ok();
    }
}
