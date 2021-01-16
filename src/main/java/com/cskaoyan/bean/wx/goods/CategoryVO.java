package com.cskaoyan.bean.wx.goods;

import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryVO {
    private Category currentCategory;
    private List<Category> brotherCategory;
    private Category parentCategory;
}
