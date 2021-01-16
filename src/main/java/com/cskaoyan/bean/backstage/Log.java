package com.cskaoyan.bean.backstage;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private Integer id;

    private String admin;

    private String ip;

    private Integer type;

    private String action;

    private Boolean status;

    private String result;

    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date addTime;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date updateTime;

    private Boolean deleted;
}