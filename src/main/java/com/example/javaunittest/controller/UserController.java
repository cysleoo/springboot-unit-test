package com.example.javaunittest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.javaunittest.domain.User;
import com.example.javaunittest.service.UserService;

import lombok.extern.log4j.Log4j2;

/**
 * @author ssliu
 */
@RequestMapping("/user/")
@Log4j2
@RestController
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/all")
  public String getAllUsers(){
    return userService.getAllUsers().toString();
  }
  @GetMapping("/{id}")
  public String getUserById(
      @PathVariable("id") Integer userId,
      @RequestParam("encrypt") boolean encrypt,
      @RequestBody User user){
    log.error(encrypt);
    log.error(user);
    User byId = userService.getById(userId);
    return JSON.toJSONString(byId);
  }




}
