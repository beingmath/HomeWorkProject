package jxau.soft.dao;

import jxau.soft.pojo.User;

public interface IUser {
  User selectUser(String name);
  void insertUser(User user);
  User selectUserBy(User user);
}