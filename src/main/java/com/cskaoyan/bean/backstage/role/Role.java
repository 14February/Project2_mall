package com.cskaoyan.bean.backstage.role;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

@Data
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Role {
    private Integer id;

    private String name;

    private String desc;

    private Boolean enabled;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;
}