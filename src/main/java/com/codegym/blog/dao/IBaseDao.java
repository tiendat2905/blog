package com.codegym.blog.dao;

import java.util.List;

public interface IBaseDao<T> {
    List<T> findAll();
    T findById(int id);
    void save(T object);
    void update(T object);
    void delete(int id);
}
