package com.cskaoyan.bean.backstage;

import lombok.Data;

import java.util.List;

@Data
public class ListData<T>{
    Integer total;
    List<T> items;

    public static <T> ListData<T> data(Integer total,List<T> items)
    {
        ListData<T> listData = new ListData<>();
        listData.setItems(items);
        listData.setTotal(total);
        return listData;
    }
}
