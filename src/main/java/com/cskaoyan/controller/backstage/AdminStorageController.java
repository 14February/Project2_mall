package com.cskaoyan.controller.backstage;

import com.cskaoyan.bean.both.BaseRespVo;

import com.cskaoyan.bean.backstage.Storage;
import com.cskaoyan.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;

import com.cskaoyan.bean.backstage.ListData;

import com.cskaoyan.service.MallSystemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("admin/storage")
public class AdminStorageController{

    @Autowired
    MallSystemService mallSystemService;

    @Autowired
    StorageService storageService;

    @PostMapping("create")
    public BaseRespVo create(MultipartFile file) throws IOException {

        Storage storage = storageService.upload(file,"admin");
        return BaseRespVo.ok(storage);
    }

    @RequestMapping("list")
    public BaseRespVo list(Integer page, Integer limit, String sort, String order, String key, String name){
        ListData<Storage> storageListData = mallSystemService.queryStorageList(page,limit,sort,order,key,name);
        return  BaseRespVo.ok(storageListData);
    }

    @RequestMapping("update")
    public BaseRespVo update(@RequestBody Storage updateStorageBo){
        Storage updateStorageVo = mallSystemService.updateStorage(updateStorageBo);
        return BaseRespVo.ok(updateStorageVo);
    }

    @RequestMapping("delete")
    public BaseRespVo delete(@RequestBody Storage deleteStorageBo){
       int i =  mallSystemService.deleteStorage(deleteStorageBo);
       return BaseRespVo.ok();
    }
//    @RequestMapping("create")
//    public BaseRespVo create(@RequestBody MultipartFile file) {
//
//
//        String type = file.getContentType();//正文类型（文件类型）
//        int size = (int) file.getSize();//文件大小
//        String name = file.getOriginalFilename(); //原始的文件名
//        String suffixName = name.substring(name.lastIndexOf("."));
//        String name1 = UUID.randomUUID() + suffixName;
//        //需要提供一个file对象来接收multipartFile → 文件存储到什么目录，在该目录下的文件名是什么
//        File file1 = new File("D:\\360downloads\\file", name1);
//        String url = "http://localhost:8083/file/" + name1;
//        try {
//            file.transferTo(file1);//将MultipartFile写入到file → 文件保存
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Storage storage = new Storage();
//        storage.setKey(name1 + type);
//        storage.setName(name);
//        storage.setType(type);
//        storage.setSize(size);
//        storage.setUrl(url);
//        storage.setAddTime(new Date());
//        storage.setUpdateTime(new Date());
//        storage.setDeleted(false);
//        int code = mallSystemService.createStorage(storage);
//        if (code != 0) {
//            return BaseRespVo.ok(url);
//        }
//        return BaseRespVo.fail();
//    }
}
