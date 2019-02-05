package com.alevel.prokopchuk.entities;

import java.util.List;
import java.util.Map;

public class Table extends Entity {
    Map<String, String> columns;

    public Table(List<String> columnNames) {
        this.columns = columns;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columnNames) {
        this.columns = columns;
    }
}
