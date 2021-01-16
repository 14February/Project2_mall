package com.cskaoyan.bean.backstage.stat;

import java.util.ArrayList;
import java.util.List;

public class OrderStat {

    private List<String> columns;
    private List<OrderBean> rows;

    {
        columns=new ArrayList<>();
        columns.add("day");
        columns.add("orders");
        columns.add("customers");
        columns.add("amount");
        columns.add("pcr");
    }
    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<OrderBean> getRows() {
        return rows;
    }

    public void setRows(List<OrderBean> rows) {
        this.rows = rows;
    }
}
