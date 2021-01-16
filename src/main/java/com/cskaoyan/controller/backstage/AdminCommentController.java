package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.GoodsService;
import com.cskaoyan.token.MallToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: project2
 * @ClassName: CommentController
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 13:13
 */
@RestController
@RequestMapping("admin/comment")
public class AdminCommentController{
    @Autowired
    GoodsService goodsService;
    @Autowired
    OperationMap operationMap;
    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit,Integer userId,Integer valueId,String sort, String order){
        ListData<GoodsComment> goodscomments=goodsService.listComment(page,limit,userId,valueId,sort,order);
        return BaseRespVo.ok(goodscomments);
    }
    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody GoodsComment goodscomment,HttpServletRequest request) {
        Integer id = goodscomment.getId();
        int code = goodsService.deleteComment(id);
        Subject subject = SecurityUtils.getSubject();
        if (code != 0) {
            operationMap.insertOperations(request,subject,OperationMap.operations.get(21), true);
            return BaseRespVo.ok();
        }
        operationMap.insertOperations(request,subject,OperationMap.operations.get(21), false);
        return BaseRespVo.fail();

    }
}
