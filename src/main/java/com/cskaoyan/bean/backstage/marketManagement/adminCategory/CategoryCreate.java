package com.cskaoyan.bean.backstage.marketManagement.adminCategory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryCreate {

/**
 * id : 1036016
 * name : 电竞
 * keywords : 游戏
 * desc : 英雄联盟
 * pid : 0
 * iconUrl :
 * picUrl :
 * level : L1
 * addTime : 2021-01-10 16:04:31
 * updateTime : 2021-01-10 16:04:31
 */

@JsonProperty("id")
private Integer id;
@JsonProperty("name")
private String name;
@JsonProperty("keywords")
private String keywords;
@JsonProperty("desc")
private String desc;
@JsonProperty("pid")
private Integer pid;
@JsonProperty("iconUrl")
private String iconUrl;
@JsonProperty("picUrl")
private String picUrl;
@JsonProperty("level")
private String level;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@JsonProperty("addTime")
private Date addTime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@JsonProperty("updateTime")
private Date updateTime;

}
