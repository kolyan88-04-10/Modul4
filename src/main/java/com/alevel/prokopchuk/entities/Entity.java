package com.alevel.prokopchuk.entities;

import java.io.Serializable;

public class Entity implements Serializable, Cloneable {
    private int id;

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
