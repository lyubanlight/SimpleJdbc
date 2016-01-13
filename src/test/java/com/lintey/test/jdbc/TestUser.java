package com.lintey.test.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lintey.test.jdbc.dao.UserDao;
import com.lintey.test.jdbc.dao.UserDaoImp;
import com.lintey.test.jdbc.model.UserModel;

public class TestUser {
  @Test
  public void TestInsert() throws SQLException {
    UserDao dao = new UserDaoImp();
    List<UserModel> userModel = dao.selectAll();
    int count = userModel.size();
    dao.insert(new UserModel(null, "blabla"));
    dao.insert(new UserModel(null, "blabla2"));
    dao.insert(new UserModel(null, "blabla3"));
    userModel = dao.selectAll();
    Assert.assertTrue(userModel.size() == count + 3);
  }

  @Test
  public void TestDelete() throws SQLException {
    UserDao dao = new UserDaoImp();
    long id = dao.insert(new UserModel(null, "blabla"));
    int startCount = dao.selectAll().size();
    Assert.assertTrue(dao.deleteById(id));
    int endCount = dao.selectAll().size();
    Assert.assertTrue(startCount == endCount + 1);
  }
}
