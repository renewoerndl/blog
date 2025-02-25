package org.rene.blog.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import org.rene.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest(PostController.class) // Only loads PostController
@ExtendWith(MockitoExtension.class)
class PostControllerTests {

    @Autowired
    private MockMvc mockMvc; // Mock HTTP calls

    @MockBean
    private PostService postService; // Mock Service layer

    @Test
    void testGetAllPosts() throws Exception {
        System.out.println("-------getAllPosts---------");
    }

    @Test
    void testCreatePost() throws Exception {


    }

    @Test
    void testUpdatePost() throws Exception {
    }

    @Test
    void testDeletePost() throws Exception {
    }

}
