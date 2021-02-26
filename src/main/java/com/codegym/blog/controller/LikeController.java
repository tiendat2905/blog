package com.codegym.blog.controller;

import com.codegym.blog.model.About;
import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.AboutService;
import com.codegym.blog.service.BlogService;
import com.codegym.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LikeController",urlPatterns = "/like")
public class LikeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        BlogService blogService = new BlogService();
        blogService.incLikes(id);

        String url = "blog-single?id="+id;
        response.sendRedirect(url);
    }
}
