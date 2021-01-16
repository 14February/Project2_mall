package com.cskaoyan.mapper.backstage.marketManagementMapper;

import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryExample;
import java.util.List;

import com.cskaoyan.bean.wx.goods.CategoryVO;
import com.cskaoyan.bean.wx.goods.FloorGoodsVO;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);


    Category queryCurrentCategory(Integer id);
    Category queryParentCategory(Integer pid);

    List<Category> queryBrotherCategory(Integer pid);

    List<FloorGoodsVO> selectIdAndName();

    List<Category> selectCategory();

}