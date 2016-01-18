package com.lintey.test.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.lintey.test.jdbc.dao.UserDao;
import com.lintey.test.jdbc.dao.UserDaoImp;
import com.lintey.test.jdbc.model.UserModel;

public class Start {
  UserDao dao;

  public void setDao(UserDao dao) {
    this.dao = dao;
  }

  public static void main(String[] args) throws SQLException {
    UserDao dao = new UserDaoImp();
    List<UserModel> userModel = null;
    dao.insert(new UserModel(null, "blabla"));
    userModel = dao.selectAll();
    for (Object user : userModel) {
      System.out.println(user);
    }
    System.out.println(dao.selectById(1));

  }
}
