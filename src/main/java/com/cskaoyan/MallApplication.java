package com.cskaoyan;

import com.cskaoyan.bean.backstage.goodsbean.Goods;
import org.apache.ibatis.io.Resources;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
@MapperScan(basePackages = "com.cskaoyan.mapper")
public class MallApplication{

    public static void main(String[] args)
    {
        SpringApplication.run(MallApplication.class,args);
    }
}
