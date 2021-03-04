package com.codegym.blog.controller;

import com.codegym.blog.model.Blog;
import com.codegym.blog.model.Category;
import com.codegym.blog.service.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminCategory", urlPatterns = "/admin-category")
public class AdminCategory extends HttpServlet {

    private CategoryService categoryService = new CategoryService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //fix unicode
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");


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
            case "find":
                try {
                    findByName(request, response);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                break;
            default:
                showListView(request, response);
                break;
        }
    }

    private void showAddView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Category category = new Category();
            request.setAttribute("cate", category);

            RequestDispatcher rd = request.getRequestDispatcher("admin-category-add.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            response.sendRedirect("error404.jsp");
        }
    }

    private void showEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Category category = categoryService.findById(id);
        if (category != null) {
            request.setAttribute("cate", category);
            RequestDispatcher rd = request.getRequestDispatcher("admin-category-edit.jsp");
            rd.forward(request, response);
        } else {
            response.sendRedirect("error404.jsp");
        }

    }

    private void doDeleteView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.delete(id);

        request.setAttribute("message", "Xoa thanh cong");
        showListView(request, response);
    }

    private void showListView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categoryList = categoryService.findAll();

        request.setAttribute("list", categoryList);
        RequestDispatcher rd = request.getRequestDispatcher("admin-category.jsp");
        rd.forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Category category = new Category(0, name, description);
        System.out.println("test1: " + category);
        categoryService.save(category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-category-add.jsp");
        request.setAttribute("cate", category);
        request.setAttribute("message", "Thêm mới thành công");
        List<Category> categoryList = null;
        dispatcher.forward(request, response);

    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        int id = Integer.parseInt(request.getParameter("id"));

        Category category = new Category(id, name, description);
        categoryService.update(category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-category-edit.jsp");
        request.setAttribute("cate", category);
        request.setAttribute("message", "Cập nhật thành công");
        dispatcher.forward(request, response);
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        List<Category> categoryList = categoryService.findByName(name);

        if (!categoryList.isEmpty()) {
            request.setAttribute("list", categoryList);
        } else {
            request.setAttribute("message", " File Not Found...");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin-category.jsp");
        dispatcher.forward(request, response);
    }
}
