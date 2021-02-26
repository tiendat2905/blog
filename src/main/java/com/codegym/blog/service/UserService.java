package com.codegym.blog.service;

import com.codegym.blog.dao.IUserDao;
import com.codegym.blog.model.User;

import java.util.List;

public class UserService implements BaseService<User> {

    IUserDao iUserDao = new IUserDao();

    @Override
    public List<User> findAll() {
        return iUserDao.findAll();
    }

    @Override
    public User findById(int id) {
        return iUserDao.findById(id);
    }

    public User findByUsernamePassword(String username, String password) {
        return iUserDao.findByUsernamePassword(username, password);
    }

    @Override
    public void save(User object) {
        iUserDao.save(object);
    }

    @Override
    public void update(User object) {
        iUserDao.update(object);
    }

    @Override
    public void delete(int id) {
        iUserDao.delete(id);
    }
}
