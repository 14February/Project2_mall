package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Storage;
import com.cskaoyan.mapper.backstage.StorageMapper;
import lombok.Data;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@ConfigurationProperties(prefix = "storage")
@Data
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    String path;

    String urlWx;

    String urlAdmin;

    @Override
    public Storage upload(MultipartFile file,String type) {
        String originalFilename = file.getOriginalFilename();
        int size = (int)file.getSize();
        String contentType = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        //对应数据表cskaoyanmall_storage列名为key的列，修改为varchar255
        String destFileName = uuid + "-" + originalFilename;

        File destFile = new File(path, destFileName);
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = null;
        if("admin".equals(type)){
            url = urlAdmin + destFileName; //192.168.5.44
        }else{
            url = urlWx + destFileName;
        }
        Storage storage = new Storage(null, destFileName, originalFilename, contentType, size, url, new Date(), new Date(), false);
        storageMapper.insert(storage);
        return storage;
    }
}
