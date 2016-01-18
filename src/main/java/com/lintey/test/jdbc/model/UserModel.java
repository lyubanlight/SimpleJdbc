package com.lintey.test.jdbc.model;

public class UserModel {

  private Long id;
  private String name;



  public UserModel(Long id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "UserModel [id=" + id + ", name=" + name + "]";
  }



}
