package com.cskaoyan.bean.backstage.goodsbean.catandbrand;

        import java.util.List;

/**
 * @ProjectName: project2
 * @ClassName: CatAndBrandVo
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-09 20:41
 */
public class CatAndBrandVo {
    private List<CategoryListBean> categoryList;
    private List<BrandListBean> brandList;

    public List<CategoryListBean> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<CategoryListBean> categoryList) {
        this.categoryList = categoryList;
    }

    public List<BrandListBean> getBrandList() {
        return brandList;
    }

    public void setBrandList(List<BrandListBean> brandList) {
        this.brandList = brandList;
    }

}

