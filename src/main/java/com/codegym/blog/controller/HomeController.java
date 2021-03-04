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
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "HomeController",urlPatterns = "/home")
public class HomeController extends HttpServlet {

    private BlogService blogService = new BlogService();
    private CategoryService catService = new CategoryService();
    private AboutService aboutService = new AboutService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        int pageNum = page == null ? 1 : Integer.parseInt(page);

        int maxPage = blogService.getNumberPage();
        if (maxPage == 0)
            throw new ServletException("Number of pages invalid");

        request.setAttribute("maxPage", maxPage);

        List<Blog> pageBlogs = blogService.listPage(pageNum);
        request.setAttribute("pageBlogs", pageBlogs);

        //Dua du lieu list category ra ui
        List<Category> listCategory = catService.findAll();
        request.setAttribute("listCate", listCategory);

        //Dua du lieu blog ra ui
        List<Blog> list3Blogs = blogService.findTop3();

        List<Blog> list12Blogs = blogService.findTop8();

        List<Blog> list5Popular  = blogService.findTop5Popular();

        List<Blog> list3Random  = blogService.findTop3Random();

        request.setAttribute("list3Blogs",list3Blogs);
        request.setAttribute("list12Blogs",list12Blogs);
        request.setAttribute("list5Popular",list5Popular);
        request.setAttribute("list3Random",list3Random);

        //Dua du lieu about ra ui
        About about = aboutService.getAbout();
        request.setAttribute("about", about);

        RequestDispatcher rd = request.getRequestDispatcher("ui-home.jsp");
        rd.forward(request, response);
    }
}
