package org.rene.blog.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor for DatabaseService.
     *
     * Parameters
     * ----------
     * dataSource : DataSource
     *     The data source to be used by the JdbcTemplate.
     */
    public DatabaseService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Tests the database connection.
     *
     * Returns
     * -------
     * String
     *     A message indicating whether the connection was successful or not.
     */
    public String testConnection() {
        try {
            String dbName = jdbcTemplate.queryForObject("SELECT current_database();", String.class);
            return "✅ Successfully connected to database: " + dbName;
        } catch (Exception e) {
            return "Database connection failed: " + e.getMessage();
        }
    }
}
