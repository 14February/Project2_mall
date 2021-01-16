package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.ListData;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.bean.wx.CollectionList;
import com.cskaoyan.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("wx/collect")
public class CollectionController {

    @Autowired
    CollectionService collectionService;

    @RequestMapping("/list")
    public BaseRespVo collectList(Integer type,Integer page,Integer size){

        CollectionList collectionList = collectionService.collectList(type,page,size);

        return BaseRespVo.ok(collectionList);
    }


    @RequestMapping("/addordelete")
    public BaseRespVo collectionAddordelete(@RequestBody Map map){
        Map<String, String> typeMap = collectionService.collectionAddordelete(map);
        return BaseRespVo.ok(typeMap);
    }

}
