package com.example.javaunittest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.log4j.Log4j2;

@SpringBootApplication
@Log4j2
public class JavaUnitTestApplication {

  public static void main(String[] args) {
    log.info("main class start up ...");
    SpringApplication.run(JavaUnitTestApplication.class, args);
  }

}
