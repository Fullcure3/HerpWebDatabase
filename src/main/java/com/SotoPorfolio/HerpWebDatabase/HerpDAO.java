package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletContext;

public class HerpDAO implements DAO{
    private ServletContext context;

    public HerpDAO(ServletContext context) {
        this.context = context;
    }

    @Override
    public MysqlDataSource connectToDatabase() {
        return getDataSource();
    }

    private MysqlDataSource getDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(getServerContext("HERP_DB_URL"));
        mysqlDataSource.setUser(getServerContext("HERP_DB_USERNAME"));
        mysqlDataSource.setPassword(getServerContext("HERP_DB_PASSWORD"));
        return mysqlDataSource;
    }

    private String getServerContext(String paramName) {
        return context.getInitParameter(paramName);
    }
}
