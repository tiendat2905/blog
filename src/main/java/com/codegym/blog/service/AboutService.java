package com.codegym.blog.service;

import com.codegym.blog.dao.IAboutDao;
import com.codegym.blog.dao.IBaseDao;
import com.codegym.blog.model.About;

import java.util.List;

public class AboutService implements BaseService<About> {

    IAboutDao iAboutDao=new IAboutDao();


    @Override
    public List<About> findAll() {
        return null;
    }

    @Override
    public About findById(int id) {
        return null;
    }

    @Override
    public void save(About object) {

    }

    @Override
    public void update(About object) {

    }

    @Override
    public void delete(int id) {

    }

    public About getAbout() {
       return iAboutDao.getAbout();
    }

}
