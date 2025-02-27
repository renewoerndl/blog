package org.rene.blog.controller;

import org.junit.jupiter.api.Test;
import org.rene.blog.dto.PostDTO;
import org.rene.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    public void testGetPostById() throws Exception {
        Long postId = 1L;
        PostDTO postDTO = new PostDTO(
                postId,
                "Test Title",
                "Test Content",
                "John Doe",
                Set.of("Java", "Spring Boot")
        );

        when(postService.getPostById(postId)).thenReturn(postDTO);

        mockMvc.perform(get("/api/posts/" + postId))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                    {
                        "id": 1,
                        "title": "Test Title",
                        "content": "Test Content",
                        "author": "John Doe",
                        "tags": ["Java", "Spring Boot"]
                    }
            """));
    }

}
