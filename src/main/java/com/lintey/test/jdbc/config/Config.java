package com.lintey.test.jdbc.config;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Config {
  public static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
  public static String LOGIN = "admin";
  public static String PASS = "admin";
  public static String JDBC_URL = "jdbc:mysql://localhost:3306/test_jdbc";
  public static String JDBC_URL_FULL = JDBC_URL + "?user=" + LOGIN + "&password=" + PASS;

  private static final Logger logger = Logger.getLogger(JdbcUtils.getCurrentClassName());
  private static ComboPooledDataSource cpds = null;

  static {
    cpds = new ComboPooledDataSource();
    try {
      cpds.setDriverClass(DRIVER_CLASS_NAME);
    } catch (PropertyVetoException e) {
      logger.error("Can't set driver", e);
    }
    cpds.setJdbcUrl(JDBC_URL);
    cpds.setUser(LOGIN);
    cpds.setPassword(PASS);

    // the settings below are optional -- c3p0 can work with defaults
    cpds.setMinPoolSize(5);
    cpds.setAcquireIncrement(5);
    cpds.setMaxPoolSize(20);
  }

  public static DataSource getDataSource() {
    return cpds;
  }

  public static Connection getConnection() throws SQLException {
    return cpds.getConnection();
  }



}
