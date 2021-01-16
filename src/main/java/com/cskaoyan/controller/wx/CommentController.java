package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.CommentCountVo;
import com.cskaoyan.bean.wx.CommentVo;
import com.cskaoyan.service.WxCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: project2
 * @ClassName: CommentContorller
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 21:06
 */
@RestController
@RequestMapping("wx/comment")
public class CommentController{
    @Autowired
    WxCommentService wxCommentService;
    @RequestMapping("list")
    public BaseRespVo list(Integer valueId,Byte type,Integer size,Integer page,Integer showType){
        CommentVo commentVo = wxCommentService.list(valueId,type,size,page,showType);
        return BaseRespVo.ok(commentVo);
    }

    @RequestMapping("count")
    public BaseRespVo count(Integer valueId,Byte type){
        CommentCountVo commentCountVo = wxCommentService.count(valueId,type);
        return BaseRespVo.ok(commentCountVo);
    }

    @RequestMapping("post")
    public BaseRespVo post(@RequestBody GoodsComment comment){
        GoodsComment commentNew = wxCommentService.post(comment);
        return BaseRespVo.ok(commentNew);
    }
}
