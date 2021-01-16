package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.keyword.Keyword;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.KeyWordService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: project2
 * @ClassName: KeyWordContorller
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 17:00
 */
@RestController
@RequestMapping("admin/keyword")
public class AdminKeyWordContorller{
    @Autowired
    OperationMap operationMap;
    @Autowired
    KeyWordService keyWordService;
    @RequestMapping("list")
    public BaseRespVo list(Integer page,Integer limit,String keyword,String url,String sort,String order){
        ListData<Keyword> keywordListData = keyWordService.list(page,limit,keyword,url,sort,order);
        return BaseRespVo.ok(keywordListData);
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody Keyword keyword, HttpServletRequest request){
        Keyword keyword1 = keyWordService.create(keyword);
        Subject subject = SecurityUtils.getSubject();
        operationMap.insertOperations(request,subject,OperationMap.operations.get(22), true);
        return  BaseRespVo.ok(keyword1);
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Keyword keyword,HttpServletRequest request){
        Keyword keyword1 = keyWordService.update(keyword);
        Subject subject = SecurityUtils.getSubject();
        operationMap.insertOperations(request,subject,OperationMap.operations.get(23), true);
        return BaseRespVo.ok(keyword1);
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Keyword keyword,HttpServletRequest request){
        int code = keyWordService.delete(keyword);
        Subject subject = SecurityUtils.getSubject();
        if (code!=0){

            operationMap.insertOperations(request,subject,OperationMap.operations.get(24), true);
            return BaseRespVo.ok();
        }
        operationMap.insertOperations(request,subject,OperationMap.operations.get(24), false);
        return BaseRespVo.fail();
    }
}
