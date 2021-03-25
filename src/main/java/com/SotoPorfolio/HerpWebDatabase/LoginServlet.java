package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    private String mainPage = "herps.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        createUserSession(request);
        directToMainPage(request, response);

    }

    private void directToMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(mainPage);
    }

    private void createUserSession(HttpServletRequest request) {
        String username = request.getParameter("username");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
    }
}
