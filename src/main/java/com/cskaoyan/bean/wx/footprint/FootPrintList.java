package com.cskaoyan.bean.wx.footprint;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class FootPrintList{
    private Integer totalPages;
    private List<FootPrintVo> footprintList;
}
