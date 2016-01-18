package com.lintey.test.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.lintey.test.jdbc.config.JdbcUtils;
import com.lintey.test.jdbc.model.UserModel;

public class UserDaoImp implements UserDao {

  private static final Logger logger = Logger.getLogger(JdbcUtils.getCurrentClassName());

  /*
   * (non-Javadoc)
   *
   * @see com.lintey.test.jdbc.UserDao#selectAll()
   */
  @Override
  public List<UserModel> selectAll() {


    final String sql = "SELECT * FROM User";
    Connection connection = null;
    List<UserModel> resList = null;
    try {
      connection = JdbcUtils.getConnection();
      connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
      connection.setAutoCommit(false);
      Statement st = connection.createStatement();
      ResultSet rs = st.executeQuery(sql);
      resList = new ArrayList<UserModel>();
      while (rs.next()) {
        resList.add(new UserModel(rs.getLong("id"), rs.getString("name")));
      }
    } catch (SQLException e) {
      logger.error("Could not execute query" + sql, e);
    } finally {
      try {
        connection.commit();
        connection.close();
      } catch (SQLException e) {
        logger.error("Could not close connection", e);
      }
    }

    return resList;

  }

  /*
   * (non-Javadoc)
   *
   * @see com.lintey.test.jdbc.UserDao#selectById()
   */
  @Override
  public UserModel selectById(long id) {
    final String SQL = "SELECT * FROM USER WHERE id = ?";
    try (Connection connection = JdbcUtils.getConnection()) {
      PreparedStatement ps = connection.prepareStatement(SQL);
      ps.setLong(1, id);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        return new UserModel(rs.getLong("id"), rs.getString("name"));
      }
    } catch (SQLException e) {
      logger.error("Could not execute query" + SQL, e);
    }
    return null;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.lintey.test.jdbc.UserDao#insert(com.lintey.test.jdbc.UserModel)
   */
  @Override
  public long insert(UserModel user) {
    final String sql = "INSERT INTO User (name) VALUES (?)";
    try (Connection connection = JdbcUtils.getConnection()) {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, user.getName());
      ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      if (rs.next()) {
        return rs.getLong(1);
      }
    } catch (SQLException e) {
      logger.error("Could not execute query" + sql, e);
    }
    return -1;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.lintey.test.jdbc.UserDao#deleteById(long)
   */
  @Override
  public boolean deleteById(long id) {
    final String sql = "DELETE FROM USER WHERE id = ?";
    try (Connection connection = JdbcUtils.getConnection()) {
      PreparedStatement ps = connection.prepareStatement(sql);
      ps.setLong(1, id);
      int rs = ps.executeUpdate();
      return rs == 1;
    } catch (SQLException e) {
      logger.error("Could not execute query" + sql, e);
    }
    return false;
  }


}
