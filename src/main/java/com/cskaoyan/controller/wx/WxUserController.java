package com.cskaoyan.controller.wx;


import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.goods.UserOrderVO;
import com.cskaoyan.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/user")
public class WxUserController {
    @Autowired
    UserService userService;
    @GetMapping("index")
    public BaseRespVo index() {
        Subject subject = SecurityUtils.getSubject();
        UserOrderVO userOrder = userService.queryOrderStatusByUsername();
        return BaseRespVo.ok(userOrder);
    }
}
