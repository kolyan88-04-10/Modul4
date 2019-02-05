package com.alevel.prokopchuk.models;

public class Column extends Model {
    private String typeName;

    public Column(String name, String typeName) {
        super(name);
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


}
