package com.example.javaunittest.service;


import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.example.javaunittest.JavaUnitTestApplicationTests;
import com.example.javaunittest.config.BaseTestConfig;
import com.example.javaunittest.domain.User;
import com.example.javaunittest.dao.UserMapper;
import com.example.javaunittest.service.impl.UserServiceImpl;


// @ExtendWith(MockitoExtension.class)
class UserServiceTest extends JavaUnitTestApplicationTests {


  @Mock
  UserMapper userMapper;

  //向要单测的对象，注入mock好的依赖
  //这里测试的是实现类，而不能是接口
  @InjectMocks
  UserServiceImpl userService;


  @Test
  void getAllUsers() {
    // 1. 准备数据。包括mock依赖，要测试对象，以及mock依赖的反应
    ArrayList<User> users = new ArrayList<>();
    User mocker = new User(1, "mocker", 13);
    users.add(mocker);
    when(userMapper.getAll()).thenReturn(users);
    // 2.断言测试
    List<User> allUsers = userService.getAllUsers();
    assertThat(allUsers).hasSize(1).containsExactly(mocker);
  }

}
