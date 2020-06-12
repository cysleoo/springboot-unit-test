package com.example.javaunittest.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.javaunittest.domain.User;

@Mapper
public interface
UserMapper {

  List<User> getAll();

  User getById(Integer id);

  boolean updateById(User user);

  boolean deleteById(Integer id);


}
