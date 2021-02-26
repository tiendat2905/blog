package com.codegym.blog.service;

import com.codegym.blog.dao.ICategoryDao;
import com.codegym.blog.model.Category;

import java.util.List;

public class CategoryService implements BaseService<Category> {

    ICategoryDao iCategoryDao = new ICategoryDao();

    @Override
    public List<Category> findAll() {
        return iCategoryDao.findAll();
    }

    @Override
    public Category findById(int id) {
        return iCategoryDao.findById(id);
    }

    @Override
    public void save(Category object) {
        iCategoryDao.save(object);
    }

    @Override
    public void update(Category object) {
        iCategoryDao.update(object);
    }

    @Override
    public void delete(int id) {
        iCategoryDao.delete(id);
    }
}
