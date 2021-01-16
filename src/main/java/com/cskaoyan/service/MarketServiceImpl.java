package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Issue;
import com.cskaoyan.bean.backstage.IssueExample;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.mapper.backstage.IssueMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-10 17:00
 */
@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    IssueMapper issueMapper;


    @Override
    public ListData<Issue> queryIssueList(Integer page, Integer limit, String sort, String order, String question) {
        PageHelper.startPage(page, limit);
        IssueExample example = new IssueExample();
        example.setOrderByClause(sort + " " + order);
        IssueExample.Criteria criteria = example.createCriteria();
        if(question != null) criteria.andQuestionLike("%" + question + "%");
        List<Issue> issues = issueMapper.selectByExample(example);
        PageInfo<Issue> issuesPageInfo = new PageInfo<>(issues);
        int total = (int)issuesPageInfo.getTotal();
        return ListData.data(total, issues);
    }

    @Override
    public Issue insertIssue(String question, String answer) {
        Issue issue = new Issue();
        issue.setQuestion(question);
        issue.setAnswer(answer);
        issue.setAddTime(new Date());
        issue.setUpdateTime(new Date());
        issueMapper.insertSelective(issue);
        return issue;
    }

    @Override
    public Issue updateIssue(Issue issueBo) {
        issueMapper.updateByPrimaryKeySelective(issueBo);
        Issue issue = issueMapper.selectByPrimaryKey(issueBo.getId());
        return issue;
    }

    @Override
    public int deleteIssue(Issue deleteIssueBo) {
        int i = issueMapper.deleteByPrimaryKey(deleteIssueBo.getId());
        return i;
    }
}
