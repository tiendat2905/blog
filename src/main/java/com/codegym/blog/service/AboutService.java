package com.codegym.blog.service;

import com.codegym.blog.dao.AboutDao;
import com.codegym.blog.model.About;

public class AboutService {

    AboutDao aboutDao = new AboutDao();

    public About getAbout() {
        return aboutDao.getAbout();
    }

}
