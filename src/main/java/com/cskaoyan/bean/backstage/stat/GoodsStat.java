package com.cskaoyan.bean.backstage.stat;

import java.util.ArrayList;
import java.util.List;

public class GoodsStat {

    private List<String> columns;
    private List<GoodsBean> rows;
    {
        columns=new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("products");
        columns.add("amount");
    }
    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<GoodsBean> getRows() {
        return rows;
    }

    public void setRows(List<GoodsBean> rows) {
        this.rows = rows;
    }

}
