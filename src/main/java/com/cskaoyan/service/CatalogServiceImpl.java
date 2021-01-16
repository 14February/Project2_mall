package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryExample;
import com.cskaoyan.bean.wx.CatalogIndex;
import com.cskaoyan.mapper.backstage.marketManagementMapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CatalogIndex catalogIndex() {

        CatalogIndex catalogIndex = new CatalogIndex();

        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        //父类目List条件
        criteria.andPidEqualTo(0);
        criteria.andDeletedEqualTo(false);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);


        List<Category> currentSubCategorises = new ArrayList<>();

        for (Category category : categories) {

            Category currentSubCategory= new Category(
                    category.getId(),
                    category.getName(),
                    category.getKeywords(),
                    category.getDesc(),
                    category.getPid(),
                    category.getIconUrl(),
                    category.getPicUrl(),
                    category.getLevel(),
                    category.getSortOrder(),
                    category.getAddTime(),
                    category.getUpdateTime(),
                    category.getDeleted()
            );
            currentSubCategorises.add(currentSubCategory);
        }

        Category currentCategory = categories.get(0);

        //父类目List
        catalogIndex.setCategoryList(categories);

        //当前单个父类目
        catalogIndex.setCurrentCategory(currentCategory);

        //子类名List

        catalogIndex.setCurrentSubCategory(currentSubCategorises);

        return catalogIndex;
    }

    @Override
    public CatalogIndex catalogCurrent(Integer id) {

        CatalogIndex catalog = new CatalogIndex();
        Category category = categoryMapper.selectByPrimaryKey(id);

        Integer categoryId = category.getId();
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andPidEqualTo(categoryId);
        List<Category> categories = categoryMapper.selectByExample(categoryExample);

        catalog.setCurrentCategory(category);
        catalog.setCurrentSubCategory(categories);
        return catalog;
    }
}
