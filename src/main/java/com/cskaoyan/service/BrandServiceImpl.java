package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.marketManagement.adminBrand.Brand;
import com.cskaoyan.bean.backstage.marketManagement.adminBrand.BrandExample;
import com.cskaoyan.bean.wx.BrandList;
import com.cskaoyan.mapper.backstage.marketManagementMapper.BrandMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService{

    @Autowired
    BrandMapper brandMapper;

    @Override
    public BrandList brandList(Integer page, Integer size) {

        BrandList brands = new BrandList();

        PageHelper.startPage(page,size);

        BrandExample brandExample = new BrandExample();
        BrandExample.Criteria criteria = brandExample.createCriteria();
        criteria.andDeletedEqualTo(false);

        //所有的
        List<Brand> brandList = brandMapper.selectByExample(brandExample);

        ArrayList<BrandList.BrandListDTO> brandListDTOS = new ArrayList<>();

        for (Brand brand : brandList) {
            BrandList.BrandListDTO brandListDTO = new BrandList.BrandListDTO(
                    brand.getId(),
                    brand.getName(),
                    brand.getDesc(),
                    brand.getPicUrl(),
                    brand.getFloorPrice()
            );
            brandListDTOS.add(brandListDTO);
        }
        brands.setBrandList(brandListDTOS);

        PageInfo<Brand> brandPageInfo = new PageInfo<>(brandList);
        Integer total = (int) brandPageInfo.getTotal();
        brands.setTotalPages(total);
        return brands;
    }

    @Override
    public Brand brandDetail(Integer id) {
        Brand brand = brandMapper.selectByPrimaryKey(id);
        return brand;
    }
}
