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

@WebServlet(name = "BlogAboutController",urlPatterns = "/blog-about")
public class BlogAboutController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        CategoryService categoryService = new CategoryService();

        List<Category> listCat = categoryService.findAll();

        BlogService blogService = new BlogService();
        Blog blog = blogService.findById(id);

        List<Blog> listTop5Popular = blogService.findTop5Popular();
        List<Blog> listTop3Related = blogService.findTop3Related(blog.getIdCategory());

        AboutService aboutService = new AboutService();
        About about = aboutService.getAbout();

        request.setAttribute("blog",blog);
        request.setAttribute("about",about);
        request.setAttribute("listCat",listCat);
        request.setAttribute("listTop3Related",listTop3Related);
        request.setAttribute("listTop5Popular",listTop5Popular);

        RequestDispatcher rd = request.getRequestDispatcher("ui-about.jsp");
        rd.forward(request,response);
    }
}
