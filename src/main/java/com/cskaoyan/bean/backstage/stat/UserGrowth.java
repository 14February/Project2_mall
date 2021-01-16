package com.cskaoyan.bean.backstage.stat;

import java.util.ArrayList;
import java.util.List;

public class UserGrowth {

    private List<String> columns;
    private List<RowsBean> rows;

    {
        columns=new ArrayList<>();
        columns.add("day");
        columns.add("users");
    }
    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * users : 3
         */

        private int users;

        public int getUsers() {
            return users;
        }

        public void setUsers(int users) {
            this.users = users;
        }
    }
}
