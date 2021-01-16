package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.SearchIndexVo;
import com.cskaoyan.service.WxSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: SearchController
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 14:34
 */
@RestController
@RequestMapping("wx/search")
public class SearchController {
    @Autowired
    WxSearchService wxSearchService;
    @RequestMapping("index")
    public BaseRespVo index(HttpSession session){
        String keyword = (String) session.getAttribute("keyword");
        SearchIndexVo searchIndexVo= wxSearchService.index(keyword);
        return BaseRespVo.ok(searchIndexVo);
    }

    @RequestMapping("helper")
    public BaseRespVo helper(String keyword, HttpSession session){
        List<String> list = wxSearchService.helper(keyword);
        session.setAttribute("keyword",keyword);
        return BaseRespVo.ok(list);
    }
    @RequestMapping("clearhistory")
    public BaseRespVo clearhistory(){
        int code = wxSearchService.clearhistory();
        if (code!=0){
            return BaseRespVo.ok();
        }
        return BaseRespVo.fail();
    }
}
