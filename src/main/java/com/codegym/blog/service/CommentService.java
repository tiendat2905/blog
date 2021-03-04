package com.codegym.blog.service;

import com.codegym.blog.dao.CommentDao;
import com.codegym.blog.model.Comment;

import java.util.List;

public class CommentService {

    CommentDao commentDao = new CommentDao();

    public List<Comment> findAll(int idBlog) {
        return commentDao.findAll(idBlog);
    }

    public void save(Comment object) {
        commentDao.save(object);
    }
}
