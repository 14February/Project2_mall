package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.catandbrand.CatAndBrandVo;
import com.cskaoyan.bean.backstage.goodsbean.create.CreateGoodsBo;
import com.cskaoyan.bean.backstage.goodsbean.detail.DetailVo;
import com.cskaoyan.map.OperationMap;
import com.cskaoyan.service.GoodsService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: project2
 * @ClassName: GoodsController
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-09 15:29
 */
@RestController
@RequestMapping("admin/goods")
public class AdminGoodsController{
    @Autowired
    OperationMap operationMap;
    @Autowired
    GoodsService goodsService;

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String goodsSn, String name, String sort, String order) {
        ListData<Goods> goodsList = goodsService.queryList(page, limit, goodsSn, name, sort, order);
        return BaseRespVo.ok(goodsList);
    }

    @RequestMapping("create")
    public BaseRespVo create(@RequestBody CreateGoodsBo createGoodsBo,HttpServletRequest request) {
        int code = goodsService.create(createGoodsBo);
        Subject subject = SecurityUtils.getSubject();
        if (code != 0) {
            operationMap.insertOperations(request,subject, OperationMap.operations.get(18), true);
            return BaseRespVo.ok();
        }
        operationMap.insertOperations(request,subject,OperationMap.operations.get(18), false);
        return BaseRespVo.fail();
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody CreateGoodsBo createGoodsBo,HttpServletRequest request) {
        int code = goodsService.update(createGoodsBo);
        Subject subject = SecurityUtils.getSubject();
        if (code != 0) {
            operationMap.insertOperations(request,subject,OperationMap.operations.get(20), true);
            return BaseRespVo.ok();
        }
        operationMap.insertOperations(request,subject,OperationMap.operations.get(20), false);
        return BaseRespVo.fail();
    }

    @RequestMapping("catAndBrand")
    public BaseRespVo catAndBrand() {
        CatAndBrandVo catAndBrandVo = goodsService.catAndBrand();
        return BaseRespVo.ok(catAndBrandVo);
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Goods goods,HttpServletRequest request) {
        Integer id = goods.getId();
        int code = goodsService.delete(id);
        Subject subject = SecurityUtils.getSubject();
        if (code != 0) {
            operationMap.insertOperations(request,subject,OperationMap.operations.get(19), true);
            return BaseRespVo.ok();
        }
        operationMap.insertOperations(request,subject,OperationMap.operations.get(19), false);
        return BaseRespVo.fail();
    }

    @RequestMapping("detail")
    public BaseRespVo detail(Integer id) {
        DetailVo detailVo = goodsService.detail(id);
        return BaseRespVo.ok(detailVo);
    }
}
