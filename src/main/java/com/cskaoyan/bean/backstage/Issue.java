package com.cskaoyan.bean.backstage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue {
    private Integer id;

    private String question;

    private String answer;

    private Date addTime;

    private Date updateTime;

    private Boolean deleted;


}