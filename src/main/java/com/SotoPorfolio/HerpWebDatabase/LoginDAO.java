package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.servlet.ServletContext;

class LoginDAO implements DAO {

    private ServletContext context;

    public LoginDAO(ServletContext context) {
        this.context = context;
    }

    @Override
    public MysqlDataSource connectToDatabase() {
        return getDataSource();
    }

    private MysqlDataSource getDataSource() {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setURL(getServerContext("MYSQL_DB_URL"));
        mysqlDataSource.setUser(getServerContext("MYSQL_DB_USERNAME"));
        mysqlDataSource.setPassword(getServerContext("MYSQL_DB_PASSWORD"));
        return mysqlDataSource;
    }

    private String getServerContext(String paramName) {
        return context.getInitParameter(paramName);
    }


}
