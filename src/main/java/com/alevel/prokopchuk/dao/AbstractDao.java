package com.alevel.prokopchuk.dao;


import com.alevel.prokopchuk.models.Model;

public abstract class AbstractDao<T extends Model> {
    public abstract boolean create(T entity);
    public abstract boolean delete(T entity);
}
