package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Issue;
import com.cskaoyan.bean.backstage.ListData;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-10 17:00
 */
public interface MarketService {
    ListData<Issue> queryIssueList(Integer page, Integer limit, String sort, String order, String question);

    Issue insertIssue(String question, String answer);

    Issue updateIssue(Issue issueBo);

    int deleteIssue(Issue deleteIssueBo);
}
