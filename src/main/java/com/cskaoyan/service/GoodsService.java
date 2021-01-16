package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.Storage;
import com.cskaoyan.bean.backstage.goodsbean.GoodsComment;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.catandbrand.CatAndBrandVo;
import com.cskaoyan.bean.backstage.goodsbean.create.CreateGoodsBo;
import com.cskaoyan.bean.backstage.goodsbean.detail.DetailVo;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.wx.goods.*;

import java.util.List;


/**
 * @ProjectName: project2
 * @ClassName: GoodsService
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-09 16:17
 */
public interface GoodsService {

    ListData<Goods> queryList(Integer page, Integer limit, String goodsSn, String name, String sort, String order);



    int delete(Integer id);

    DetailVo detail(Integer id);



    int deleteComment(Integer id);

    int create(CreateGoodsBo createGoodsBo);

    int update(CreateGoodsBo createGoodsBo);

    int count();
    CategoryVO queryCategory(Integer id);

    GoodsListVO list(String keyword, String order, String sort, Integer categoryId, Integer page, Integer size);

    GoodsDetailVO wxDetail(Integer id);


    CatAndBrandVo catAndBrand();


    ListData<GoodsComment> listComment(Integer page, Integer limit, Integer userId, Integer valueId, String sort, String order);

    RelatedGoodsVO related(Integer id);
}
