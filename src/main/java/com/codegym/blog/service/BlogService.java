package com.codegym.blog.service;

import com.codegym.blog.dao.BlogDao;
import com.codegym.blog.model.Blog;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


public class BlogService implements BaseService<Blog> {

    BlogDao blogDao = new BlogDao();

    @Override
    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    @Override
    public Blog findById(int id) {
        return blogDao.findById(id);
    }

    public List<Blog> findByCate(int page,int idCategory) {
        return blogDao.findByCate(page,idCategory);
    }

    public int getNumberPageCat(int idCategory) {
        try {
            return blogDao.getNumberPageCat(idCategory);
        } catch (SQLException throwables) {
            return 0;
        }
    }

    public List<Blog> findTop3() {
        return blogDao.findTop3();
    }

    public List<Blog> findTop3Random() {
        return blogDao.findTop3Random();
    }

    public List<Blog> findTop5() {
        return blogDao.findTop5();
    }

    public List<Blog> findTop3Related(int categoryId) {
        return blogDao.findTop3Related(categoryId);
    }


    public List<Blog> findTop8() {
        return blogDao.findTop8();
    }


    public List<Blog> findTop5Popular() {
        return blogDao.findTop5Popular();
    }

    @Override
    public void save(Blog object) {
        blogDao.save(object);

    }

    @Override
    public void update(Blog object) {
        blogDao.update(object);

    }

    @Override
    public void delete(int id) {
        blogDao.delete(id);

    }

    public void incViews(int id) {
        blogDao.incViews(id);
    }

    public void incLikes(int id) {
        blogDao.incLikes(id);
    }

    public List<Blog> findByName(String keyword) {
        return blogDao.findByName(keyword);
    }

    public List<Blog> listPage(int page)  {
        try {
            return blogDao.listPage(page);
        } catch (SQLException e) {
            return Collections.emptyList();
        }
    }

    public int getNumberPage() {
        try {
            return blogDao.getNumberPage();
        } catch (SQLException throwables) {
            return 0;
        }
    }
}