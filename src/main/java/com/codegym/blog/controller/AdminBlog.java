package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.BlogService;
import com.codegym.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminBlog",urlPatterns = "/admin-blog")
public class AdminBlog extends HttpServlet {

    BlogService blogService = new BlogService();

    CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //fix unicode
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("authentication");
            return;
        }

        String action = request.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "add":
                doAdd(request, response);
                break;
            case "edit":
                doEdit(request, response);
                break;
            case "delete":
                break;
            default:
                // this.showListView(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //fix unicode
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "add":
                showAddView(request, response);
                break;
            case "edit":
                showEditView(request, response);
                break;
            case "delete":
                doDeleteView(request, response);
                break;
            default:
                showListView(request, response);
                break;
        }
    }

    private void showAddView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("ok");
//        Blog blog = new Blog();
//        request.setAttribute("blog", blog);
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList",categoryList);

        RequestDispatcher rd = request.getRequestDispatcher("admin-blog-add.jsp");
        rd.forward(request, response);
    }

    private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("categoryList",categoryList);

        Blog blog = blogService.findById(id);
        if (blog != null) {
            request.setAttribute("blog", blog);
            RequestDispatcher rd = request.getRequestDispatcher("admin-blog-edit.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("404");
        }

    }

    private void doDeleteView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        blogService.delete(id);

        request.setAttribute("message", "Xoa thanh cong");
        showListView(request, response);
    }

    private void showListView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Blog> blogList = blogService.findAll();

        request.setAttribute("list", blogList);
        RequestDispatcher rd = request.getRequestDispatcher("admin-blog.jsp");
        rd.forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String fullContent = request.getParameter("fullContent");
        String imageUrl = request.getParameter("imageUrl");
        int idCategory = Integer.parseInt(request.getParameter("category"));

        Blog blog = new Blog(0, title, shortContent,fullContent,imageUrl,idCategory);
        System.out.println("test1: " + blog);
        blogService.save(blog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-blog-add.jsp");
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("blog", blog);
        request.setAttribute("message", "Thêm mới thành công");
        request.setAttribute("categoryList",categoryList);
        dispatcher.forward(request, response);

    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String shortContent = request.getParameter("shortContent");
        String fullContent = request.getParameter("fullContent");
        String imageUrl = request.getParameter("imageUrl");
        int idCategory = Integer.parseInt(request.getParameter("category"));

        Blog blog = new Blog(id, title, shortContent,fullContent,imageUrl,idCategory);
        blogService.update(blog);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-blog-edit.jsp");
        List<Category> categoryList = categoryService.findAll();
        request.setAttribute("blog", blog);
        request.setAttribute("message", "Cập nhật thành công");
        request.setAttribute("categoryList",categoryList);
        dispatcher.forward(request, response);
    }
}
