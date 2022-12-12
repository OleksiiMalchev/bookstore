package com.javacourse.bookstore.configuration;

import com.javacourse.bookstore.utils.TestDbHelper;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.MySQLContainer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

@TestConfiguration
public class TestDatabaseConfig {
    private MySQLContainer mySQLContainer;

    @PostConstruct
    public void config() {
        mySQLContainer = new MySQLContainer<>("mysql:8.0.30")
                .withDatabaseName("test_db")
                .withUsername("test")
                .withPassword("test")
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

    @Bean
    public TestDbHelper testDbHelper(@Autowired DataSource dataSource) {
        return new TestDbHelper(new JdbcTemplate(dataSource));
    }

    @PreDestroy
    public void destroy() {
        mySQLContainer.stop();
    }
}
