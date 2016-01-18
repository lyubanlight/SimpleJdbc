package com.lintey.test.jdbc.config;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class JdbcUtils {
  private static boolean initialized = false;
  private static final Logger logger = Logger.getLogger(getCurrentClassName());

/*Not need in JDBC 4.0*/  
//  public static synchronized void initDriver(String name) {
//    if (!initialized) {
//      try {
//        Class.forName(name);
//      } catch (ClassNotFoundException e) {
//        // TODO Auto-generated catch block
//        e.printStackTrace();
//      }
//    }
//  }

  public static Connection getConnection() throws SQLException {
    return Config.getConnection();
    // return DriverManager.getConnection(Config.JDBC_URL_FULL);
  }

  public static String getCurrentClassName() {
    try {
      throw new RuntimeException();
    } catch (RuntimeException e) {
      return e.getStackTrace()[1].getClassName();
    }
  }
}
