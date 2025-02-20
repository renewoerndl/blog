package org.rene.blog.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rene.blog.model.Post;
import org.rene.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(PostController.class) // Only loads PostController
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc; // Mock HTTP calls

    @MockBean
    private PostService postService; // Mock Service layer

    @Test
    void testGetAllPosts() throws Exception {
        // Mock Service Response
        when(postService.getAllPosts()).thenReturn(Arrays.asList(
                new Post("Spring Boot", "Spring Boot Content", "John"),
                new Post("REST API", "REST API Content", "Jane")
        ));

        // Perform GET request
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Spring Boot"))
                .andExpect(jsonPath("$[1].title").value("REST API"));

        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void testCreatePost() throws Exception {
        Post newPost = new Post("Unit Testing", "Content about unit testing", "Alice");
        Set<String> tags = new HashSet<>(Arrays.asList("JUnit", "Mockito"));

        when(postService.createPostWithTags(any(Post.class), anySet())).thenReturn(newPost);

        // Perform POST request
        mockMvc.perform(post("/api/posts?tags=JUnit,Mockito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Unit Testing\", \"content\": \"Content about unit testing\", \"author\": \"Alice\" }"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Unit Testing"))
                .andExpect(jsonPath("$.author").value("Alice"));

        verify(postService, times(1)).createPostWithTags(any(Post.class), anySet());
    }
}
