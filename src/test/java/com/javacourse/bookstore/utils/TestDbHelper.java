package com.javacourse.bookstore.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.CompositeDatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class TestDbHelper {

    private final JdbcTemplate jdbcTemplate;

    public TestDbHelper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void uploadScript(String location) {
        try {
            CompositeDatabasePopulator compositeDatabasePopulator = new CompositeDatabasePopulator();
            compositeDatabasePopulator.addPopulators(new ResourceDatabasePopulator(new ClassPathResource(location)));
            compositeDatabasePopulator.populate(jdbcTemplate.getDataSource().getConnection());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void cleanTable(String tableName) {
        try {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=0;");
            jdbcTemplate.update("truncate table " + tableName + ";");
        } finally {
            jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS=1;");
        }
    }


}
