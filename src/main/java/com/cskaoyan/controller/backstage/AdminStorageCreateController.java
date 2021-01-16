package com.cskaoyan.controller.backstage;

import com.cskaoyan.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: project2
 * @ClassName: StoragecreateController
 * @TODO: TODO
 * @Author caifanglin
 * @Create 2021-01-10 12:15
 */
@RestController
@RequestMapping("admin/storage")
public class AdminStorageCreateController{
    @Autowired
    GoodsService goodsService;
//    @RequestMapping("create")
//    public BaseRespVo create(@RequestBody MultipartFile file) {
//        String type = file.getContentType();//正文类型（文件类型）
//        int size = (int) file.getSize();//文件大小
//        String name = file.getOriginalFilename(); //原始的文件名
//        //需要提供一个file对象来接收multipartFile → 文件存储到什么目录，在该目录下的文件名是什么
//        String uuid = UUID.randomUUID().toString();
//        String fileName = uuid + "."
//                + file.getContentType().substring(
//                file.getContentType().lastIndexOf("/") + 1);
//        File file1 = new File("D:\\spring", fileName);
//        String url = "http://localhost:8083/file/"+fileName;
//        try {
//            file.transferTo(file1);//将MultipartFile写入到file → 文件保存
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Storage storage = new Storage();
//        storage.setKey(fileName);
//        storage.setName(name);
//        storage.setType(type);
//        storage.setSize(size);
//        storage.setUrl(url);
//        storage.setAddTime(new Date());
//        storage.setUpdateTime(new Date());
//        storage.setDeleted(false);
//        Storage storagenew = goodsService.createStorage(storage);
//        return BaseRespVo.ok(storagenew);
//    }
}
