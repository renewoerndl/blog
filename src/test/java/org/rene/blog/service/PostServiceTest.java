package org.rene.blog.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rene.blog.model.Post;
import org.rene.blog.repository.PostRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Enables Mockito for JUnit 5
class PostServiceTest {

    @Mock
    private PostRepository postRepository; // Mock repository

    @InjectMocks
    private PostService postService; // Inject mock into service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPosts() {
        // Mocking repository response
        when(postRepository.findAll()).thenReturn(Arrays.asList(
                new Post("Spring Boot Guide", "Content for Spring Boot", "John Doe"),
                new Post("REST API Best Practices", "Content for REST API", "Jane Smith")
        ));

        // Call service method
        List<Post> posts = postService.getAllPosts();

        // Assertions
        assertEquals(2, posts.size());
        assertEquals("Spring Boot Guide", posts.get(0).getTitle());
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testCreatePost() {
        Post newPost = new Post("Unit Testing", "Testing with JUnit & Mockito", "Alice");

        when(postRepository.save(any(Post.class))).thenReturn(newPost);

        Post savedPost = postService.createPostWithTags(newPost, null);

        assertNotNull(savedPost);
        assertEquals("Unit Testing", savedPost.getTitle());
        verify(postRepository, times(1)).save(any(Post.class));
    }
}
