package org.rene.blog.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String testConnection() {
        try {
            String dbName = jdbcTemplate.queryForObject("SELECT current_database();", String.class);
            return "✅ Successfully connected to database: " + dbName;
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
