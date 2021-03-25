package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/login"})
public class LoginFilter implements Filter {

    private String loginPage = "login.html";
    private Service service;

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            directToCorrectPage(request, response, chain);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void directToCorrectPage(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, SQLException {
        setLoginService(request);
        if(isValidUser(request))
            allowAccess(request, response, chain);
        else
            redirectToLoginPage(request, response);
    }

    private void allowAccess(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

    private void redirectToLoginPage(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(loginPage).forward(request, response);
    }

    private boolean isValidUser(ServletRequest request) throws SQLException {
        return service.getMatchingResults(request).next();
    }

    private void setLoginService(ServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        service = new LoginService(new LoginDAO(servletContext));
    }

}
