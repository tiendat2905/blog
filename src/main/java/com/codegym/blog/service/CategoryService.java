package com.codegym.blog.service;

import com.codegym.blog.dao.CategoryDao;
import com.codegym.blog.model.Category;

import java.util.List;

public class CategoryService implements BaseService<Category> {

    CategoryDao categoryDao = new CategoryDao();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return categoryDao.findById(id);
    }

    @Override
    public void save(Category object) {
        categoryDao.save(object);
    }

    @Override
    public void update(Category object) {
        categoryDao.update(object);
    }

    @Override
    public void delete(int id) {
        categoryDao.delete(id);
    }

    public List<Category> findByName(String keyword){
        return categoryDao.findByName(keyword);
    }
}
