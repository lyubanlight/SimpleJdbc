package com.lintey.test.jdbc.dao;

import java.sql.SQLException;
import java.util.List;

import com.lintey.test.jdbc.model.UserModel;


public interface UserDao {

  List<UserModel> selectAll() throws SQLException;

  UserModel selectById(long id) throws SQLException;

  long insert(UserModel user) throws SQLException;

  boolean deleteById(long id) throws SQLException;

}
