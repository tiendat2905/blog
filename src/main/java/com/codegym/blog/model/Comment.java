package com.codegym.blog.model;

import java.util.Date;

public class Comment {
    private int id;
    private int idBlog;

    private String blogName;

    private String username;
    private String email;
    private Date pushDate;
    private String comment;

    public Comment() {
    }

    public Comment(int id, int idBlog, String blogName, String username, String email, Date pushDate, String comment) {
        this.id = id;
        this.idBlog = idBlog;
        this.blogName = blogName;
        this.username = username;
        this.email = email;
        this.pushDate = pushDate;
        this.comment = comment;
    }

    public Comment(int id, int idBlog, String username, String email, String comment) {
        this.id = id;
        this.idBlog = idBlog;
        this.username = username;
        this.email = email;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdBlog() {
        return idBlog;
    }

    public void setIdBlog(int idBlog) {
        this.idBlog = idBlog;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
