package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HerpQueryService implements Service{
    private String sql = "SELECT * FROM herps WHERE common_name REGEXP ? AND habitat REGEXP ?";
    private DAO dao;

    public HerpQueryService(DAO dao) {
        this.dao = dao;
    }

    @Override
    public ResultSet getMatchingResults(ServletRequest request) throws SQLException {
        MysqlDataSource mysqlDataSource = dao.connectToDatabase();
        Connection connection = mysqlDataSource.getConnection();
        return matchingHerps(request, connection);
    }

    private ResultSet matchingHerps(ServletRequest request, Connection connection) throws SQLException {
        String commonName = request.getParameter("commonName");
        String habitat = request.getParameter("habitat");

        return processedQuery(connection, commonName, habitat);
    }

    private ResultSet processedQuery(Connection connection, String commonName, String habitat) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, commonName);
        statement.setString(2, habitat);
        return statement.executeQuery();
    }

}
