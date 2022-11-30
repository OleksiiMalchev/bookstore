package com.javacourse.bookstore.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@Configuration
public class TestDatabaseConfig {
    private MySQLContainer mySQLContainer;

    @PostConstruct
    public void config() {
        mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
                .withDatabaseName("test_db")
                .withUsername("test").withPassword("test")
                .withEnv("MYSQL_ROOT_HOST", "%");
        mySQLContainer.addExposedPort(3338);
        mySQLContainer.start();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(mySQLContainer.getDriverClassName());
        hikariConfig.setJdbcUrl(mySQLContainer.getJdbcUrl());
        hikariConfig.setUsername(mySQLContainer.getUsername());
        hikariConfig.setPassword(mySQLContainer.getPassword());
        return new HikariDataSource(hikariConfig);
    }

    @PreDestroy
    public void destroy() {
        mySQLContainer.stop();
    }
}
