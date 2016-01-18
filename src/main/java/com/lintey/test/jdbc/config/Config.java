package com.lintey.test.jdbc.config;

public class Config {
  public static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
  public static String JDBC_URL = "jdbc:mysql://localhost:3306/test_jdbc?user=admin&password=admin";
  public static String LOGIN = "root";
  public static String PASS = "";

  static {
    JdbcUtils.initDriver(DRIVER_CLASS_NAME);
  }

}
