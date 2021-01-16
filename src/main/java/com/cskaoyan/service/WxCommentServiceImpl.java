package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.bean.backstage.goodsbean.GoodsCommentExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.CommentCountVo;
import com.cskaoyan.bean.wx.CommentVo;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.GoodscommentMapper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: WxCommentServiceImpl
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-12 21:08
 */
@Service
public class WxCommentServiceImpl implements WxCommentService {
    @Autowired
    GoodscommentMapper goodscommentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public CommentVo list(Integer valueId, Byte type, Integer size, Integer page, Integer showType) {
//        Subject subject = SecurityUtils.getSubject();
//        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
//        User user = userMapper.selectUserByUsername(username);
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        GoodsCommentExample.Criteria criteria = goodsCommentExample.createCriteria();
        if (valueId!=null)criteria.andValueIdEqualTo(valueId);
        List<GoodsComment> goodsComments=null;
        if (type == 1) {
            criteria.andTypeEqualTo(type);
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        if (type==0){
            criteria.andTypeEqualTo(type);
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        if (showType==1){
            criteria.andHasPictureEqualTo(true);
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        if (showType==0){
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        PageInfo<GoodsComment> goodsCommentPageInfo = new PageInfo<>(goodsComments);
        int count = (int) goodsCommentPageInfo.getTotal();
        CommentVo commentVo = new CommentVo();
        ArrayList<CommentVo.DataBean> dataBeans = new ArrayList<>();
        for (GoodsComment goodsComment : goodsComments) {
            CommentVo.DataBean dataBean = new CommentVo.DataBean();
            dataBean.setAddTime(goodsComment.getAddTime());
            dataBean.setPicList(goodsComment.getPicUrls());
            dataBean.setContent(goodsComment.getContent());
            User user = userMapper.selectByPrimaryKey(goodsComment.getUserId());
            CommentVo.DataBean.UserInfoBean userInfoBean = new CommentVo.DataBean.UserInfoBean();
            userInfoBean.setAvatarUrl(user.getAvatar());
            userInfoBean.setNickName(user.getNickname());
            dataBean.setUserInfo(userInfoBean);
            dataBeans.add(dataBean);
        }
        commentVo.setData(dataBeans);
        commentVo.setCount(count);
        commentVo.setCurrentPage(page);
        return commentVo;
    }

    @Override
    public CommentCountVo count(Integer valueId, Byte type) {
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        GoodsCommentExample.Criteria criteria = goodsCommentExample.createCriteria();
        if (valueId!=null)criteria.andValueIdEqualTo(valueId);
        List<GoodsComment> goodsComments=null;
        if (type == 1) {
            criteria.andTypeEqualTo(type);
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        if (type==0){
            criteria.andTypeEqualTo(type);
            goodsComments= goodscommentMapper.selectByExample(goodsCommentExample);
        }
        PageInfo<GoodsComment> goodsCommentPageInfo = new PageInfo<>(goodsComments);
        int count = (int) goodsCommentPageInfo.getTotal();
        int hasPicCount = 0;
        for (GoodsComment goodsComment : goodsComments) {
            Boolean hasPicture = goodsComment.getHasPicture();
            if (hasPicture.equals(true)) {
                hasPicCount = hasPicCount + 1;
            }
        }
        CommentCountVo commentCountVo = new CommentCountVo();
        commentCountVo.setAllCount(count);
        commentCountVo.setHasPicCount(hasPicCount);
        return commentCountVo;
    }

    @Override
    public GoodsComment post(GoodsComment comment) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);
        comment.setUserId(user.getId());
        comment.setAddTime(new Date());
        comment.setUpdateTime(new Date());
        comment.setDeleted(true);
        goodscommentMapper.insert(comment);
        Integer id = comment.getId();
        GoodsComment goodsComment = goodscommentMapper.selectByPrimaryKey(id);
        return goodsComment;
    }
}
