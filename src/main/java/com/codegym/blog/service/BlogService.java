package com.codegym.blog.service;

import com.codegym.blog.dao.IBlogDao;
import com.codegym.blog.model.Blog;
import java.util.List;


public class BlogService implements BaseService<Blog> {

    IBlogDao iBlogDao = new IBlogDao();

    @Override
    public List<Blog> findAll() {
        return iBlogDao.findAll();
    }

    @Override
    public Blog findById(int id) {
        return iBlogDao.findById(id);
    }

    public List<Blog> findByCate(int idCategory) {
        return iBlogDao.findByCate(idCategory);
    }

    public List<Blog> findTop3() {
        return iBlogDao.findTop3();
    }


    public List<Blog> findTop5() {
        return iBlogDao.findTop5();
    }

    public List<Blog> findTop3Related(int categoryId) {
        return iBlogDao.findTop3Related(categoryId);
    }


    public List<Blog> findTop12() {
        return iBlogDao.findTop12();
    }


    public List<Blog> findTop5Popular() {
        return iBlogDao.findTop5Popular();
    }

    @Override
    public void save(Blog object) {
       iBlogDao.save(object);

    }

    @Override
    public void update(Blog object) {
        iBlogDao.update(object);

    }

    @Override
    public void delete(int id) {
        iBlogDao.delete(id);

    }

    public void incViews(int id) {
        iBlogDao.incViews(id);
    }

    public void incLikes(int id) {
        iBlogDao.incLikes(id);
    }
}