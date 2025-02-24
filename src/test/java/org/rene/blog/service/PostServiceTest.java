package org.rene.blog.service;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
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
        // Arrange: Mock Data erstellen
        Post post1 = new Post();
        post1.setId(1L);
        post1.setTitle("Post 1");

        Post post2 = new Post();
        post2.setId(2L);
        post2.setTitle("Post 2");

        List<Post> mockPosts = Arrays.asList(post1, post2);

        // Verhalten des Repositories definieren
        when(postRepository.findAll()).thenReturn(mockPosts);

        // Act: Methode aufrufen
        List<Post> result = postService.getAllPosts();

        // Assert: Ergebnisse überprüfen
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Post 1", result.get(0).getTitle());
        assertEquals("Post 2", result.get(1).getTitle());

        // Verifizierung des Repository-Aufrufs
        verify(postRepository, times(1)).findAll();
    }

    @Test
    void testCreatePost() {
    }

    @Test
    void testUpdatePost() {
    }

    @Test
    void testDeletePost() {
    }

}
