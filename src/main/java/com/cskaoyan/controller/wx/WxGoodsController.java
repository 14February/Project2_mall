package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.goods.*;
import com.cskaoyan.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/goods")
public class WxGoodsController {
    @Autowired
    GoodsService goodsService;

    @GetMapping("count")
    public BaseRespVo count() {
        int goodsCount = goodsService.count();
        HashMap<String, Integer> map = new HashMap<>();
        map.put("goodsCount", goodsCount);
        return BaseRespVo.ok(map);
    }
    @GetMapping("category")
    public BaseRespVo category(Integer id) {
        CategoryVO categoryVO = goodsService.queryCategory(id);
        return BaseRespVo.ok(categoryVO);
    }
    @GetMapping("list")
    public BaseRespVo list(String keyword, String order, String sort, Integer categoryId, Integer page, Integer size){
        GoodsListVO goodsListVO = goodsService.list(keyword, order, sort, categoryId, page, size);
        return BaseRespVo.ok(goodsListVO);
    }
    @GetMapping("detail")
    public BaseRespVo detail(Integer id) {
        GoodsDetailVO goodsDetailVO = goodsService.wxDetail(id);
        return BaseRespVo.ok(goodsDetailVO);
    }
    @GetMapping("related")
    public BaseRespVo related(Integer id) {
        RelatedGoodsVO goodsList = goodsService.related(id);
        return BaseRespVo.ok(goodsList);
    }
}
