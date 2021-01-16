package com.cskaoyan.controller.wx;

import com.cskaoyan.bean.backstage.Storage;
import com.cskaoyan.bean.both.BaseRespVo;
import com.cskaoyan.mapper.backstage.StorageMapper;
import com.cskaoyan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 */
@RequestMapping("wx/storage")
@RestController
public class StorageController{

    @Autowired
    StorageService storageService;

    @RequestMapping("upload")
    public BaseRespVo<Storage> upload(MultipartFile file)
    {
        Storage storage = storageService.upload(file,"wx");
        return BaseRespVo.ok(storage);
    }
}
