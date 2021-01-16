package com.cskaoyan.service;

import com.cskaoyan.bean.wx.footprint.FootPrintList;

/**
 *
 */
public interface FootPrintService{
    FootPrintList queryFootprintList(Integer page,Integer size,String userId);

    void deleteFootPrint(Integer id);
}
