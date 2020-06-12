package com.example.javaunittest.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.javaunittest.JavaUnitTestApplicationTests;
import com.example.javaunittest.domain.User;

class UserMapperTest extends JavaUnitTestApplicationTests {

  @Resource
  UserMapper userMapper;

  @Test
  void getAll() {
    List<User> allData = userMapper.getAll();
    User mocker = new User(1, "mocker", 13);
    assertThat(allData).hasSize(1).containsExactly(mocker);
  }

  @Test
  void getById() {
    User actualUser = userMapper.getById(1);
    User expectedUser = new User(1, "mocker", 13);
    assertThat(actualUser).isNotNull().isEqualTo(expectedUser);
  }

  @Test
  void updateById() {
    //1.准备数据
    User dbUser = new User(1, "mocker", 13);
    String expectedName = "update by id";
    int expectedAge = 23;
    dbUser.setName(expectedName);
    dbUser.setAge(expectedAge);
    userMapper.updateById(dbUser);

    User actualUser = userMapper.getById(dbUser.getId());
    //  2.验证数据
    assertThat(actualUser)
        .isNotNull()
        .isEqualTo(dbUser);
  }

  @Test
  void deleteById() {
  //  1.准备数据
    List<User> beforeDelete = userMapper.getAll();
    int size = beforeDelete.size();
    userMapper.deleteById(beforeDelete.get(0).getId());

  //  2.
    List<User> afterDelete = userMapper.getAll();
    assertThat(afterDelete).hasSize(size-1);

  }
}
