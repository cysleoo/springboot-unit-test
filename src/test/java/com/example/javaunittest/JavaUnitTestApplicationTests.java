package com.example.javaunittest;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.example.javaunittest.config.BaseTestConfig;
import com.example.javaunittest.config.DaoTestConfig;

// @SpringBootTest(classes = BaseTestConfig.class)


@SpringBootTest(classes = DaoTestConfig.class)

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JavaUnitTestApplicationTests {
}
