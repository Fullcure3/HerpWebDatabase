package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService implements Service {
    private String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
    private DAO dao;

    public LoginService(DAO dao) {
        this.dao = dao;
    }

    @Override
    public ResultSet getMatchingResults(ServletRequest request) throws SQLException {
        MysqlDataSource mysqlDataSource = dao.connectToDatabase();
        Connection connection = mysqlDataSource.getConnection();
        return matchingUser(connection, request);
    }

    private ResultSet matchingUser(Connection connection, ServletRequest request) throws SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        return processedRequest(connection, username, password);
    }

    private ResultSet processedRequest(Connection connection, String username, String password) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, username);
        statement.setString(2, password);

        return statement.executeQuery();
    }
}


