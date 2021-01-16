package com.cskaoyan.service;
import com.cskaoyan.bean.marketManagement.adminCategory.CategoryL1;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandCreat;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.AdminCategory;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryCreate;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.Order;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.OrderDetail;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.OrderShip;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.AdminRegion;


import java.math.BigDecimal;
import java.util.List;


public interface MarketManageService {

    ListData<Brand> queryBrandList(Integer page,Integer limit,String sort,String order,Integer id,String name);

    Brand brandUpdate(Brand items);

    BrandCreat brandCreat(BrandCreat brandCreat);

    void brandDelete(Brand items);


    List<AdminCategory.DataDTO> categoryList();

    CategoryCreate categoryCreat(Category categoryCreate);

    void categoryUpdate(Category category);

    void categoryDelete(Category category);

    ListData<Order> orderList(Integer page, Integer limit, String sort, String order, Short[] orderStatusArray, String orderSn, Integer userId);

    OrderDetail orderDetail(Integer id);

    void orderShip(OrderShip orderShip);

    List<AdminRegion> regionList();

    BigDecimal orderReturn(Integer id);

    List<CategoryL1> categoryL1();

}
