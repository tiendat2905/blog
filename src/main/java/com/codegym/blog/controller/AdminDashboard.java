package com.codegym.blog.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminDashboard", urlPatterns = "/dashboard")
public class AdminDashboard extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //fix unicode
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //fix unicode
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        if (username == null || username.isEmpty()) {
            response.sendRedirect("authentication");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("admin-dashboard.jsp");
            rd.forward(request, response);
        }

    }
}
