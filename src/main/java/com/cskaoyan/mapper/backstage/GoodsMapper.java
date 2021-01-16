package com.cskaoyan.mapper.backstage;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import com.cskaoyan.bean.backstage.goodsbean.GoodsExample;
import java.util.List;

import com.cskaoyan.bean.backstage.goodsbean.catandbrand.BrandListBean;
import com.cskaoyan.bean.backstage.goodsbean.catandbrand.CategoryListBean;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.wx.goods.GoodsVO;
import com.cskaoyan.bean.wx.goods.GrouponListVO;
import org.apache.ibatis.annotations.Param;

public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);



    List<BrandListBean> selectBrandList();

    List<CategoryListBean> selectcategoryList();

    List<Integer> selectCatdgoryIds(Integer id);

    List<GoodsVO> selectByCategoryId(@Param("categoryId") Integer categoryId);

    List<Category> selectFilterCategory(Integer categoryId);
    int selectAll();

    List<GoodsVO> selectRelatedGoods(Integer id);

    List<GrouponListVO> selectGrouponList();

    List<Goods> selectHotGoods();

    List<Goods> selectNewGoods();

}