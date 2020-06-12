package com.example.javaunittest.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.h2.tools.Server;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import lombok.extern.log4j.Log4j2;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

/**
 * 注解方式配置h2数据库
 * 目前配置文件的方式还没有找到，回头再找
 * @author ssliu
 * @date 2020-06-10
 */
@Log4j2
public class DaoTestConfig {

  @Bean("dataSource")
  public DataSource dataSource() {
    log.info("load h2 db...");
    EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();

    EmbeddedDatabase db = databaseBuilder
        .setType(EmbeddedDatabaseType.H2)
        //启动时初始化建表语句
        .addScript("classpath:wheel-mysql.sql")
        //启动时准备测试数据
        .addScript("classpath:data.sql")
        .build();

    return db;
  }

  @Bean(name = "h2WebServer", initMethod = "start", destroyMethod = "stop")
  //启动一个H2的web server， 调试时可以通过localhost:8082访问到H2的内容
  //JDBC URL: jdbc:h2:mem:testdb
  //User Name: sa
  //Password: 无
  //注意如果使用断点，断点类型(Suspend Type)一定要设置成Thread而不能是All,否则web server无法正常访问!
  public Server server() throws Exception {
    //在8082端口上启动一个web server
    return Server.createWebServer("-web", "-webAllowOthers", "-webDaemon", "-webPort", "8082");
  }
  @Bean(name = "sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
    sessionFactory.setDataSource(dataSource);
    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    //加载所有的SQL文件
    Resource[] mapperLocations = resolver.getResources("classpath:mappers/*.xml");
    sessionFactory.setMapperLocations(mapperLocations);
    return sessionFactory.getObject();
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    //只需要写DAO接口，不用写实现类，运行时动态生成代理
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("com.example.javaunittest.dao");
    configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
    return configurer;
  }

}
