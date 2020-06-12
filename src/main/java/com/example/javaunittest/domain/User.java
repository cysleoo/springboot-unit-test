package com.example.javaunittest.domain;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ssliu
 */
  @Getter
  @Setter
  @ToString
  @EqualsAndHashCode
@Component
public class User {
  private Integer id;
  private String name;
  private Integer age;

  public User(Integer id, String name, Integer age) {
    this.id = id;
    this.name = name;
    this.age = age;
  }

  public  User(){}
}
