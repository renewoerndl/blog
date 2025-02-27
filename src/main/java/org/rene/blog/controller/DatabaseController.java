package org.rene.blog.controller;

import org.rene.blog.service.DatabaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database")
public class DatabaseController {

    private final DatabaseService databaseService;

    /**
     * Constructor to initialize the DatabaseService.
     *
     * Parameters
     * ----------
     * databaseService : DatabaseService
     *     The service to handle database operations.
     */
    public DatabaseController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    /**
     * Handle GET requests to /api/database/test.
     *
     * Returns
     * -------
     * String
     *     A message indicating the status of the database connection.
     */
    @GetMapping("/test")
    public String testDatabaseConnection() {
        return databaseService.testConnection();
    }
}


