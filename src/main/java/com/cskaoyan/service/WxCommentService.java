package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.bean.wx.CommentCountVo;
import com.cskaoyan.bean.wx.CommentVo;

/**
 * @ProjectName: project2
 * @ClassName: WxCommentService
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 21:07
 */
public interface WxCommentService {
    CommentVo list(Integer valueId, Byte type, Integer size, Integer page, Integer showType);

    CommentCountVo count(Integer valueId, Byte type);

    GoodsComment post(GoodsComment comment);
}
