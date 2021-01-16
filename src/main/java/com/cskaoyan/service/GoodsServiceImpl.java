package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Footprint;
import com.cskaoyan.bean.backstage.FootprintExample;
import com.cskaoyan.bean.backstage.Issue;
import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.backstage.goodsbean.*;
import com.cskaoyan.bean.backstage.goodsbean.catandbrand.*;
import com.cskaoyan.bean.backstage.goodsbean.create.CreateGoodsBo;
import com.cskaoyan.bean.backstage.goodsbean.detail.DetailVo;

import com.cskaoyan.bean.backstage.groupon.Groupon;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandExample;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.Category;
import com.cskaoyan.bean.backstage.marketManagement.adminCategory.CategoryExample;
import com.cskaoyan.bean.backstage.user.User;
import com.cskaoyan.bean.wx.goods.*;
import com.cskaoyan.mapper.UserMapper;
import com.cskaoyan.mapper.backstage.*;

import com.cskaoyan.mapper.backstage.marketManagementMapper.BrandMapper;
import com.cskaoyan.mapper.backstage.marketManagementMapper.CategoryMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: GoodsServiceImpl
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-09 16:18
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsattributeMapper goodsattributeMapper;
    @Autowired
    GoodsproductMapper goodsproductMapper;
    @Autowired
    GoodsspecificationMapper goodsspecificationMapper;
    @Autowired
    GoodscommentMapper goodscommentMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    GrouponMapper grouponMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Override
    public ListData<Goods> queryList(Integer page,Integer limit,String goodsSn,String name,String sort,String order) {
        PageHelper.startPage(page, limit);
        GoodsExample example = new GoodsExample();
        example.setOrderByClause(sort + " " + order);
        GoodsExample.Criteria criteria = example.createCriteria();
        if (goodsSn != null && !goodsSn.equals("")) criteria.andGoodsSnEqualTo(goodsSn);
        if (name != null) criteria.andNameLike("%" + name + "%");
        List<Goods> goods = goodsMapper.selectByExample(example);
        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goods);
        int total = (int) goodsPageInfo.getTotal();   //符合条件的总记录数
        return ListData.data(total, goods);
    }

    @Override
    public int delete(Integer id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public DetailVo detail(Integer id) {
        DetailVo detailVo = new DetailVo();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        ArrayList<Integer> categoryId = new ArrayList<>();
        categoryId.add(goods.getCategoryId());
        GoodsAttributeExample goodsattributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria = goodsattributeExample.createCriteria();
        if (id != null) criteria.andGoodsIdEqualTo(id);
        List<GoodsAttribute> goodsattribute = goodsattributeMapper.selectByExample(goodsattributeExample);

        GoodsSpecificationExample goodsspecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria1 = goodsspecificationExample.createCriteria();

        if (id != null) criteria1.andGoodsIdEqualTo(id);
        List<GoodsSpecification> goodsspecification = goodsspecificationMapper.selectByExample(goodsspecificationExample);
        GoodsProductExample goodsproductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria2 = goodsproductExample.createCriteria();

        if (id != null) criteria2.andGoodsIdEqualTo(id);
        List<GoodsProduct> goodsProducts = goodsproductMapper.selectByExample(goodsproductExample);
        detailVo.setGoods(goods);
        detailVo.setCategoryIds(categoryId);
        detailVo.setAttributes(goodsattribute);
        detailVo.setProducts(goodsProducts);
        detailVo.setSpecifications(goodsspecification);
        return detailVo;
    }


    @Override
    public ListData<GoodsComment> listComment(Integer page,Integer limit,Integer userId,Integer valueId,String sort,String order) {
        PageHelper.startPage(page, limit);
        GoodsCommentExample example = new GoodsCommentExample();
        example.setOrderByClause(sort + " " + order);
        GoodsCommentExample.Criteria criteria = example.createCriteria();
        if (userId != null) criteria.andUserIdEqualTo(userId);
        if (valueId != null) criteria.andValueIdEqualTo(valueId);
        criteria.andTypeEqualTo((byte) 0);
        List<GoodsComment> goodsComments = goodscommentMapper.selectByExample(example);
        PageInfo<GoodsComment> pageInfo = new PageInfo<>(goodsComments);
        int total = (int) pageInfo.getTotal();
        return ListData.data(total,goodsComments);
    }

    @Override
    public int deleteComment(Integer id) {
        return goodscommentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int create(CreateGoodsBo createGoodsBo) {
        List<GoodsAttribute> attributes = createGoodsBo.getAttributes();
        Goods goods = createGoodsBo.getGoods();
        List<GoodsProduct> products = createGoodsBo.getProducts();
        List<GoodsSpecification> specifications = createGoodsBo.getSpecifications();
        goods.setAddTime(new Date());
        goods.setUpdateTime(new Date());
        goods.setDeleted(false);
        int insert = goodsMapper.insert(goods);
        Integer goodsId = goods.getId();

        for (GoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goodsId);
            attribute.setAddTime(new Date());
            attribute.setUpdateTime(new Date());
            attribute.setDeleted(false);
            goodsattributeMapper.insert(attribute);
        }

        for (GoodsProduct product : products) {
            product.setGoodsId(goodsId);
            product.setAddTime(new Date());
            product.setUpdateTime(new Date());
            product.setDeleted(false);
            goodsproductMapper.insert(product);
        }
        for (GoodsSpecification specification : specifications) {
            specification.setGoodsId(goodsId);
            specification.setAddTime(new Date());
            specification.setUpdateTime(new Date());
            specification.setDeleted(false);
            goodsspecificationMapper.insert(specification);
        }
        return insert;
    }

    @Override
    public int update(CreateGoodsBo createGoodsBo) {
        List<GoodsAttribute> attributes = createGoodsBo.getAttributes();
        Goods goods = createGoodsBo.getGoods();
        goods.setUpdateTime(new Date());
        List<GoodsProduct> products = createGoodsBo.getProducts();
        List<GoodsSpecification> specifications = createGoodsBo.getSpecifications();
        int i = goodsMapper.updateByPrimaryKeySelective(goods);
        for (GoodsAttribute attribute : attributes) {
            attribute.setUpdateTime(new Date());
            goodsattributeMapper.updateByPrimaryKeySelective(attribute);
        }
        for (GoodsProduct product : products) {
            product.setUpdateTime(new Date());
            goodsproductMapper.updateByPrimaryKeySelective(product);
        }
        for (GoodsSpecification specification : specifications) {
            specification.setUpdateTime(new Date());
            goodsspecificationMapper.updateByPrimaryKeySelective(specification);
        }
        return i;
    }

    @Override
    public CatAndBrandVo catAndBrand() {
        CatAndBrandVo catAndBrandVo = new CatAndBrandVo();
        List<CategoryListBean> categoryList = goodsMapper.selectcategoryList();
        List<BrandListBean> brandList = goodsMapper.selectBrandList();
        catAndBrandVo.setCategoryList(categoryList);
        catAndBrandVo.setBrandList(brandList);
        return catAndBrandVo;
    }

    @Override
    public int count() {
        int goodsCount = goodsMapper.selectAll();
        return goodsCount;
    }

    @Override
    public CategoryVO queryCategory(Integer id) {
        Category currentCategory = categoryMapper.queryCurrentCategory(id);
        Integer pid = currentCategory.getPid();
        Category parentCategory = categoryMapper.queryParentCategory(pid);
        List<Category> brotherCategorys = categoryMapper.queryBrotherCategory(pid);

        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setCurrentCategory(currentCategory);
        categoryVO.setBrotherCategory(brotherCategorys);
        categoryVO.setParentCategory(parentCategory);
        return categoryVO;
    }

    @Override
    public GoodsDetailVO wxDetail(Integer id) {
        String username = (String)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        User user = userMapper.selectUserByUsername(username);

        Goods info = goodsMapper.selectByPrimaryKey(id);
        if(info == null || user == null) return new GoodsDetailVO();
        //添加浏览足迹
        //判断该商品是否已经浏览过，浏览过则更新updateTime
        Integer userId = user.getId();
        Integer goodsId = info.getId();
        Integer num = footprintMapper.selectFootprint(userId, goodsId);
        if(num > 0) {
            footprintMapper.updateFootprint(userId, goodsId);
        } else {
            Footprint footprint = new Footprint(null, user.getId(), id, new Date(), new Date(), false);
            footprintMapper.insert(footprint);
        }
        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria1 = goodsProductExample.createCriteria();
        if(id != null) criteria1.andGoodsIdEqualTo(id);
        criteria1.andDeletedEqualTo(false);
        List<GoodsProduct> productList = goodsproductMapper.selectByExample(goodsProductExample);

        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria2 = goodsSpecificationExample.createCriteria();
        if(id != null)  criteria2.andGoodsIdEqualTo(id);
        criteria2.andDeletedEqualTo(false);
        List<GoodsSpecification> goodsSpecifications = goodsspecificationMapper.selectByExample(goodsSpecificationExample);
        GoodsSpecificationVO goodsSpecificationVO = new GoodsSpecificationVO();
        ArrayList<GoodsSpecificationVO> specificationVOList = new ArrayList<>();
        for (GoodsSpecification goodsSpecification : goodsSpecifications) {
            goodsSpecificationVO.setName(goodsSpecification.getSpecification());
            ArrayList<GoodsSpecification> goodsSpecificationList = new ArrayList<>();
            goodsSpecificationList.add(goodsSpecification);
            goodsSpecificationVO.setValueList(goodsSpecificationList);
            specificationVOList.add(goodsSpecificationVO);
        }
        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria3 = goodsAttributeExample.createCriteria();
        if(id != null) criteria3.andGoodsIdEqualTo(id);
        criteria3.andDeletedEqualTo(false);
        List<GoodsAttribute> attributes = goodsattributeMapper.selectByExample(goodsAttributeExample);

        Integer brandId = info.getBrandId();
        Brand  brand = null;
        if(brandId != null) {
            brandMapper.selectBrandById(brandId);
        }

        //查询comment表里type=0的content
        GoodsCommentExample goodsCommentExample = new GoodsCommentExample();
        GoodsCommentExample.Criteria criteria5 = goodsCommentExample.createCriteria();
        if(id != null) criteria5.andValueIdEqualTo(id);
        criteria5.andDeletedEqualTo(false);
        criteria5.andTypeEqualTo((byte)0); //商品评论
        List<GoodsComment> goodsComments = goodscommentMapper.selectByExample(goodsCommentExample);
        int commentCount = (int)goodscommentMapper.countByExample(goodsCommentExample);
        GoodsDetailCommentVO commentVO = new GoodsDetailCommentVO();
        commentVO.setData(goodsComments);
        commentVO.setCount(commentCount);

        List<Issue> issues = issueMapper.selectAllIssues();

        Integer userHasCollect = collectMapper.selectCollectByUserIdAndGoodsIdAndtype(user.getId(), info.getId());
        if(userHasCollect > 0) {
            userHasCollect = 1;
        }
        List<Groupon> groupons = grouponMapper.selectGroupon(user.getId());

        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        goodsDetailVO.setInfo(info);
        String shareUrl = info.getShareUrl();
        goodsDetailVO.setShareImage(shareUrl);
        goodsDetailVO.setAttribute(attributes);
        goodsDetailVO.setBrand(brand);
        goodsDetailVO.setComment(commentVO);
        goodsDetailVO.setGroupon(groupons);
        goodsDetailVO.setProductList(productList);
        goodsDetailVO.setIssue(issues);
        goodsDetailVO.setSpecificationList(specificationVOList);
        goodsDetailVO.setUserHasCollect(userHasCollect);
        return goodsDetailVO;
    }

    @Override
    public GoodsListVO list(String keyword, String order, String sort, Integer categoryId, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        //根据categoryId查询goods表的
        GoodsExample goodsExample = new GoodsExample();
        if(order != null && order.equals("")) {
            if(sort != null && sort.equals("")) {
                goodsExample.setOrderByClause(sort + " " + order);
            }
        }
        GoodsExample.Criteria criteria = goodsExample.createCriteria();

        if(categoryId != null && !categoryId.equals(0)) criteria.andCategoryIdEqualTo(categoryId);
        if(keyword != null) criteria.andNameLike("%" + keyword + "%");
        criteria.andIsOnSaleEqualTo(true);
        criteria.andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
//        List<GoodsVO> goodsVOS = goodsMapper.selectByCategoryId(categoryId);
        int count = goodsList.size();
        //查询category表
        List<Category> categoryVOS = goodsMapper.selectFilterCategory(categoryId);
        GoodsListVO goodsListVO = new GoodsListVO();
        goodsListVO.setCount(count);
        goodsListVO.setFilterCategoryList(categoryVOS);
        goodsListVO.setGoodsList(goodsList);
        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        long total = pageInfo.getTotal();
        return goodsListVO;
    }

    @Override
    public RelatedGoodsVO related(Integer id) {
        //根据商品id去goods表查询category_id，再查询出非该商品id的其他同category_id商品
        List<GoodsVO> goodsList = goodsMapper.selectRelatedGoods(id);
        RelatedGoodsVO relatedGoodsVO = new RelatedGoodsVO();
        relatedGoodsVO.setGoodsList(goodsList);
        return relatedGoodsVO;
    }
//    @Override
//    public Storage createStorage(Storage storage) {
//        storageMapper.insert(storage);
//        Integer id = storage.getId();
//        Storage storagenew = storageMapper.selectByPrimaryKey(id);
//        return storagenew;
//    }


}

