package com.codegym.blog.controller;

import com.codegym.blog.model.About;
import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.model.Comment;
import com.codegym.blog.service.AboutService;
import com.codegym.blog.service.BlogService;
import com.codegym.blog.service.CategoryService;
import com.codegym.blog.service.CommentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BlogSingleController",urlPatterns = "/blog-single")
public class BlogSingleController extends HttpServlet {

    BlogService blogService = new BlogService();
    CategoryService categoryService = new CategoryService();
    CommentService commentService = new CommentService();
    AboutService aboutService = new AboutService();


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 10 cmt

        // luu 1 cmt moi 1 - a - a@g.c - abc
        int idBlog = Integer.parseInt(request.getParameter("idBlog"));
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String comment = request.getParameter("comment");

        // tao Comment(..)
        Comment comment1 = new Comment(0, idBlog,username,email,comment);

        // luu vo db
        commentService.save(comment1);

        // redirect qua GET cua single blog
        response.sendRedirect(request.getContextPath() + "/blog-single?id=" + idBlog);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("ui-details.jsp");
//        request.setAttribute("comment", comment1);
//        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<Category> listCat = categoryService.findAll();

        Blog blog = blogService.findById(id);
        //Tang view
        blogService.incViews(id);

        List<Blog> listTop5Popular = blogService.findTop5Popular();
        List<Blog> listTop3Random = blogService.findTop3Random();
        List<Blog> listTop3Related = blogService.findTop3Related(blog.getIdCategory());


        List<Comment> commentList = commentService.findAll(id);


        About about = aboutService.getAbout();

        request.setAttribute("blog",blog);
        request.setAttribute("about",about);
        request.setAttribute("listCat",listCat);
        request.setAttribute("listTop3Related",listTop3Related);
        request.setAttribute("listTop5Popular",listTop5Popular);
        request.setAttribute("listTop3Random",listTop3Random);
        request.setAttribute("commentList",commentList);

        RequestDispatcher rd = request.getRequestDispatcher("ui-details.jsp");
        rd.forward(request,response);
    }
}
