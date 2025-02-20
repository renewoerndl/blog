package org.rene.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The BlogApplication class serves as the entry point to the Blog application,
 * which is a Spring Boot application.
 *
 * This class uses the {@code @SpringBootApplication} annotation to signify
 * it as the primary configuration class for the Spring Boot application.
 * The main method launches the application by invoking {@code SpringApplication.run}.
 */
@SpringBootApplication
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
