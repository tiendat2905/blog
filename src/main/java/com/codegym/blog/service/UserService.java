package com.codegym.blog.service;

import com.codegym.blog.dao.UserDao;
import com.codegym.blog.model.User;

import java.util.List;

public class UserService implements BaseService<User> {

    UserDao userDao = new UserDao();

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public User findById(int id) {
        return userDao.findById(id);
    }

    public User findByUsernamePassword(String username, String password) {
        return userDao.findByUsernamePassword(username, password);
    }

    @Override
    public void save(User object) {
        userDao.save(object);
    }

    @Override
    public void update(User object) {
        userDao.update(object);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
