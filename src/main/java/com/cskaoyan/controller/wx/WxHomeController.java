package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.goods.HomeIndexVO;
import com.cskaoyan.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/home")
public class WxHomeController {
    @Autowired
    HomeService homeService;
    @RequestMapping("index")
    public BaseRespVo index(){
        HomeIndexVO homeIndexVO = homeService.index();
        return BaseRespVo.ok(homeIndexVO);
    }
}
