package com.example.javaunittest.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alibaba.fastjson.JSON;
import com.example.javaunittest.JavaUnitTestApplicationTests;
import com.example.javaunittest.domain.User;
import com.example.javaunittest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

//该注解和@SpringBootTest同级，不能共存，所以controller的测试都不继承父类
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {


  @Autowired
  private MockMvc mockMvc;

  // 一个userService的mock的bean
  // mock的所有方法返回的结果是null，或空的list
  @MockBean
  UserService userService;

  // 一个工具
  // 对象 和 jsonstring进行转换
  // 通fastjson 的 json
  // @Autowired
  // private ObjectMapper objectMapper;


  @Test
  void getAllUsers() throws Exception {
    mockMvc.perform(get("/user/all")
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

  @Test
  void getById() throws Exception {
    User userParam = new User(1, "test for request body", 13);

    //1. 验证请求是否有问题
    mockMvc.perform(get("/user/{id}",1)
        .contentType("application/json")
        //参数
        .param("encrypt","false")
        //requestbody 要求的对象参数
        // .content(objectMapper.writeValueAsString(userParam)))
        .content(JSON.toJSONString(userParam)))
        .andExpect(status().isOk());

  //  2.验证是否正确调用service的某方法
    verify(userService,times(1)).getById(1);

  }

}
