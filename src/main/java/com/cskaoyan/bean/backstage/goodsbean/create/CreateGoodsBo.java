package com.cskaoyan.bean.backstage.goodsbean.create;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsAttribute;
import com.cskaoyan.bean.backstage.goodsbean.GoodsProduct;
import com.cskaoyan.bean.backstage.goodsbean.GoodsSpecification;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: CreateGoodsBo
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 14:11
 */
@Data
public class CreateGoodsBo {
    private List<GoodsAttribute> attributes;
    private Goods goods;
    private List<GoodsProduct> products;
    private List<GoodsSpecification> specifications;
}
