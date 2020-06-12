package com.example.javaunittest.service;

import java.util.List;

import com.example.javaunittest.domain.User;

public interface UserService {

  List<User> getAllUsers();

  /**
   * 通过ID获取user
   * @param id
   * @return
   */
  User getById(int id);

}
