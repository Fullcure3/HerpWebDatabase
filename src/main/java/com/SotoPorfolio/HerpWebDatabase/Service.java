package com.SotoPorfolio.HerpWebDatabase;

import javax.servlet.ServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Service {
    ResultSet getMatchingResults(ServletRequest request) throws SQLException;
}
