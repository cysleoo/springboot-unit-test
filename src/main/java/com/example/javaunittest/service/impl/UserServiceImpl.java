package com.example.javaunittest.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.example.javaunittest.domain.User;
import com.example.javaunittest.dao.UserMapper;
import com.example.javaunittest.service.UserService;

@Service
public class UserServiceImpl implements UserService {
  @Resource
  UserMapper userMapper;

  @Override
  public List<User> getAllUsers() {
    return userMapper.getAll();
  }


  @Override
  public User getById(int id) {
    return userMapper.getById(id);
  }
}
