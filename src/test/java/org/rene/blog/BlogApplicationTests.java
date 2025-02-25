package org.rene.blog;

import org.springframework.boot.SpringApplication;

public class BlogApplicationTests {

    public static void main(String[] args) {
        SpringApplication.from(BlogApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
