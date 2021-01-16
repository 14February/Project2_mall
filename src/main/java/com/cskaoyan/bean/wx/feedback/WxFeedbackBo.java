package com.cskaoyan.bean.wx.feedback;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zyhstart
 * @description
 * @create 2021-01-12 21:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxFeedbackBo {
    private String content;
    private String feedType;
    private boolean hasPicture;
    private String mobile;
    private String[] picUrls;

}
