package com.cskaoyan.service;


import com.alibaba.druid.util.StringUtils;

import com.cskaoyan.bean.marketManagement.adminCategory.*;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandCreat;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandExample;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.AdminCategory;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryCreate;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryExample;
import com.cskaoyan.bean.backstage.marketManagement.adminOrder.*;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.AdminRegion;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.Region;
import com.cskaoyan.bean.backstage.marketManagement.adminRegion.RegionExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.marketManagementMapper.*;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MarketManageServiceImpl implements MarketManageService{

    @Autowired
    BrandMapper brandMapper;
    @Override
    public ListData<Brand> queryBrandList(Integer page,Integer limit,String sort,String order,Integer id,String name) {
        PageHelper.startPage(page,limit);
        BrandExample brandExample = new BrandExample();
        brandExample.setOrderByClause(sort+ " " +order);


        BrandExample.Criteria criteria = brandExample.createCriteria();
        if (id != null){
            //添加查询条件
            criteria.andIdEqualTo(id);
        }
        if (!StringUtils.isEmpty(name)){
            criteria.andNameLike("%" + name + "%");
        }

        criteria.andDeletedEqualTo(false);
        //返回值封装类型
        List<Brand> brands = brandMapper.selectByExample(brandExample);
        PageInfo<Brand> brandPageInfo = new PageInfo<>(brands);
        int total = (int) brandPageInfo.getTotal();

        return ListData.data(total,brands);
    }

    @Override
    public Brand brandUpdate(Brand items) {
        Integer id = items.getId();

        int i = brandMapper.updateByPrimaryKey(items);

        Brand brand = brandMapper.selectByPrimaryKey(id);

        return brand;
    }

    @Override
    public BrandCreat brandCreat(BrandCreat brandCreatItem) {


        Brand brand = new Brand(null,brandCreatItem.getName(),brandCreatItem.getDesc(),brandCreatItem.getPicUrl(),null,brandCreatItem.getFloorPrice(),new Date(),new Date(),false);
        Integer i = brandMapper.insertSelective(brand);


        BrandExample brandExample = new BrandExample();
        brandExample.createCriteria().andNameEqualTo(brandCreatItem.getName());


        //TODO:此处不能添加相同 name 的商品
        List<Brand> brandList = brandMapper.selectByExample(brandExample);
        Integer id = 0;
        for (Brand list:brandList) {
             id = list.getId();
        }

        Brand brandCreat = brandMapper.selectByPrimaryKey(id);

        BrandCreat creat = new BrandCreat(
                id,
                brandCreat.getName(),
                brandCreat.getDesc(),
                brandCreat.getFloorPrice(),
                brandCreat.getPicUrl(),
                brandCreat.getAddTime(),
                brandCreat.getUpdateTime());

        return creat;
    }

    @Override
    public void brandDelete(Brand items) {
        items.setDeleted(true);
        brandMapper.updateByPrimaryKeySelective(items);
    }


    //--------------------------------------------Category---------------------------------------------------------------

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List<AdminCategory.DataDTO> categoryList() {

        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        //一级类目，且未删除
        criteria.andLevelEqualTo("L1");
        criteria.andDeletedEqualTo(false);

        //一级类目List，且未删除
        List<Category> categoriesL1 = categoryMapper.selectByExample(categoryExample);


        //最外层
        List<AdminCategory.DataDTO> dataDTOList = new ArrayList<>();

        for (Category list1 :categoriesL1) {
            Integer id = list1.getId();

            //二级类目
            CategoryExample categoryExampleL2 = new CategoryExample();
            CategoryExample.Criteria criteriaL2 = categoryExampleL2.createCriteria();

            //二级类目
            criteriaL2.andPidEqualTo(id);
            criteriaL2.andDeletedEqualTo(false);

            //children
            List<Category> categoriesL2 = categoryMapper.selectByExample(categoryExampleL2);

            List<AdminCategory.DataDTO.ChildrenDTO> childrenDTOList = new ArrayList<>();

            for (Category list2 :categoriesL2) {
                AdminCategory.DataDTO.ChildrenDTO childrenDTO = new AdminCategory.DataDTO.ChildrenDTO(
                    list2.getId(),
                    list2.getName(),
                    list2.getKeywords(),
                    list2.getDesc(),
                    list2.getIconUrl(),
                    list2.getPicUrl(),
                    list2.getLevel());
                childrenDTOList.add(childrenDTO);
            }
            System.out.println(1);
            //data
            AdminCategory.DataDTO dataDTO = new AdminCategory.DataDTO(
                    list1.getId(),
                    list1.getName(),
                    list1.getKeywords(),
                    list1.getDesc(),
                    list1.getIconUrl(),
                    list1.getPicUrl(),
                    list1.getLevel(),
                    childrenDTOList
            );
            dataDTOList.add(dataDTO);
        }
        return dataDTOList;
    }

    @Override
    public CategoryCreate categoryCreat(Category category) {

        CategoryExample categoryExample = new CategoryExample();

        CategoryExample.Criteria criteria = categoryExample.createCriteria();

        category.setAddTime(new Date());
        category.setUpdateTime(new Date());
        categoryMapper.insertSelective(category);

        criteria.andNameEqualTo(category.getName());

        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        CategoryCreate categoryCreate = null;
        for (Category list :categories) {
          categoryCreate = new CategoryCreate(
                list.getId(),list.getName(),list.getKeywords(),list.getDesc(),list.getPid(),
                    list.getIconUrl(),list.getPicUrl(),list.getLevel(),new Date(),new Date()
            );
        }
        return categoryCreate;
    }

    @Override
    public void categoryUpdate(Category category) {
        int i = categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public void categoryDelete(Category category) {
        category.setDeleted(true);
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    //-----------------------------------------------------Order---------------------------------------------------------------------
    @Autowired
    OrderMapper orderMapper;
    @Override
    public ListData<Order> orderList(Integer page, Integer limit, String sort, String order,
                                     Short[] orderStatusArray, String orderSn, Integer userId) {
        PageHelper.startPage(page,limit);

        OrderExample orderExample = new OrderExample();
        orderExample.setOrderByClause(sort + " " +order);

        OrderExample.Criteria criteria = orderExample.createCriteria();


        if (orderStatusArray != null ){
            criteria.andOrderStatusBetween(orderStatusArray[0],orderStatusArray[orderStatusArray.length -1]);
        }

        if (!StringUtils.isEmpty(orderSn)){
            criteria.andOrderSnEqualTo(orderSn);
        }
        if (userId != null){
            criteria.andUserIdEqualTo(userId);
        }
        List<Order> orders = orderMapper.selectByExample(orderExample);

        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        int total = (int) orderPageInfo.getTotal();
        return ListData.data(total,orders);
    }

    @Autowired
    OrderGoodsMapper orderGoodsMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public OrderDetail orderDetail(Integer id) {

        OrderDetail orderDetail = new OrderDetail();

        //order
        Order order = orderMapper.selectByPrimaryKey(id);
        Integer userId = order.getUserId();

        OrderDetail.OrderDTO orderDTO = new OrderDetail.OrderDTO(
                order.getId(),
                order.getUserId(),
                order.getOrderSn(),
                order.getOrderStatus(),
                order.getConsignee(),
                order.getMobile(),
                order.getAddress(),
                order.getMessage(),
                order.getGoodsPrice(),
                order.getFreightPrice(),
                order.getCouponPrice(),
                order.getIntegralPrice(),
                order.getGrouponPrice(),
                order.getOrderPrice(),
                order.getActualPrice(),
                order.getComments(),
                order.getAddTime(),
                order.getUpdateTime(),
                order.getDeleted(),
                order.getShipChannel(),
                order.getShipSn(),
                order.getPayTime(),
                order.getConfirmTime(),
                order.getShipTime()
        );


        //users
        User user = userMapper.selectByPrimaryKey(userId);
        OrderDetail.UserDTO userDTO = new OrderDetail.UserDTO(user.getNickname(), user.getAvatar());


        //orderGoods
        OrderGoodsExample orderGoodsExample = new OrderGoodsExample();
        OrderGoodsExample.Criteria criteria = orderGoodsExample.createCriteria();
        criteria.andOrderIdEqualTo(id);
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectByExample(orderGoodsExample);

        List<OrderDetail.OrderGoodsDTO> orderGoodsDTOList  = new ArrayList<>();

        for (OrderGoods list:orderGoodsList) {

            List<String> specificationList = new ArrayList<>();
            specificationList.add(list.getSpecifications());

            OrderDetail.OrderGoodsDTO orderGoodsDTO = new OrderDetail.OrderGoodsDTO(
                    list.getId(),
                    list.getOrderId(),
                    list.getGoodsId(),
                    list.getGoodsName(),
                    list.getGoodsSn(),
                    list.getProductId(),
                    list.getNumber(),
                    list.getPrice(),
                    list.getPicUrl(),
                    list.getComment(),
                    list.getAddTime(),
                    list.getUpdateTime(),
                    list.getDeleted(),
                    specificationList
            );
            orderGoodsDTOList.add(orderGoodsDTO);
        }

        orderDetail.setOrder(orderDTO);
        orderDetail.setUser(userDTO);
        orderDetail.setOrderGoods(orderGoodsDTOList);

        return orderDetail;
    }

    @Override
    public void orderShip(OrderShip orderShip) {

        Order order = new Order();
        order.setId(orderShip.getOrderId());
        order.setShipChannel(orderShip.getShipChannel());
        order.setShipSn(orderShip.getShipSn());
        order.setOrderStatus((short) 301);
        order.setShipTime(new Date());
        int i = orderMapper.updateByPrimaryKeySelective(order);
    }


    //-----------------------------------------------------------------------------------------------------------------------------

    @Autowired
    RegionMapper regionMapper;

    @Override
    public List<AdminRegion> regionList() {

        List<AdminRegion> adminRegionList = new ArrayList<>();

        //省份
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteria = regionExample.createCriteria();
        criteria.andTypeEqualTo((byte) 1);

        //查询出省份List
        List<Region> provinceList = regionMapper.selectByExample(regionExample);

        for (Region province : provinceList) {

            RegionExample cityExample = new RegionExample();
            RegionExample.Criteria cityExampleCriteria = cityExample.createCriteria();
            cityExampleCriteria.andPidEqualTo(province.getId());

            //城市List
            List<Region> cityList = regionMapper.selectByExample(cityExample);

            List<AdminRegion.Children1> cities = new ArrayList<>();

            for (Region region : cityList) {

                RegionExample countyExample = new RegionExample();
                RegionExample.Criteria countyExampleCriteria = countyExample.createCriteria();
                countyExampleCriteria.andPidEqualTo(region.getId());

                //县的List
                List<Region> countyList = regionMapper.selectByExample(countyExample);

                List<AdminRegion.Children1.Children2> counties = new ArrayList<>();
                for (Region county : countyList) {

                    //每一个县
                    AdminRegion.Children1.Children2 oneCounty = new AdminRegion.Children1.Children2(
                            county.getId(),
                            county.getName(),
                            county.getType(),
                            county.getCode()
                    );
                    counties.add(oneCounty);
                }

                //一个省份所有的城市
                AdminRegion.Children1 city = new AdminRegion.Children1(
                        region.getId(),
                        region.getName(),
                        region.getType(),
                        region.getCode(),
                        counties
                );
                cities.add(city);
            }

            //每一个省份
            AdminRegion adminRegion = new AdminRegion(
                    province.getId(),
                    province.getName(),
                    province.getType(),
                    province.getCode(),
                    cities
            );
            adminRegionList.add(adminRegion);
        }
        return adminRegionList;
    }

    @Override
    public BigDecimal orderReturn(Integer id) {


        //202
        OrderExample orderExample = new OrderExample();
        OrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andIdEqualTo(id);
        Order order = orderMapper.selectByPrimaryKey(id);

        order.setOrderStatus((short) 203);
        order.setEndTime(new Date());
        int i = orderMapper.updateByPrimaryKeySelective(order);
        //203
        return order.getGoodsPrice();
    }

    @Override
    public List<CategoryL1> categoryL1() {

        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andLevelEqualTo("L1");
        criteria.andDeletedEqualTo(false);

        List<Category> categories = categoryMapper.selectByExample(categoryExample);
        ArrayList<CategoryL1> categoryL1s = new ArrayList<>();
        for (Category category : categories) {
            CategoryL1 categoryL1 = new CategoryL1(
                    category.getId(),category.getName()
            );
            categoryL1s.add(categoryL1);
        }
        return categoryL1s;
    }
}
