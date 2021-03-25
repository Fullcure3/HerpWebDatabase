package com.SotoPorfolio.HerpWebDatabase;

import com.mysql.cj.jdbc.MysqlDataSource;

public interface DAO {
    MysqlDataSource connectToDatabase();
}
