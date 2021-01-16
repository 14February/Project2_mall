package com.cskaoyan.service;

import com.cskaoyan.bean.backstage.Storage;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    Storage upload(MultipartFile file,String type);
}
