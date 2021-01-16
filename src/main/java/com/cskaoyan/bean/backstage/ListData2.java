package com.cskaoyan.bean.backstage;

import lombok.Data;

import java.util.List;

@Data
public class ListData2<T>{
    Integer count;
    List<T> data;

    public static <T> ListData2<T> data(Integer count, List<T> data)
    {
        ListData2<T> listData = new ListData2<>();
        listData.setData(data);
        listData.setCount(count);
        return listData;
    }
}
