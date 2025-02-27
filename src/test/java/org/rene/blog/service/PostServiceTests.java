package org.rene.blog.service;

import org.junit.jupiter.api.Test;
import org.rene.blog.dto.PostDTO;
import org.rene.blog.model.Post;
import org.rene.blog.model.Tag;
import org.rene.blog.repository.PostRepository;
import org.rene.blog.repository.TagRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostServiceTests {

    @MockBean
    private PostRepository postRepository;

    @MockBean
    private TagRepository tagRepository;

    @MockBean
    private PostService postService;

    @Test
    public void testGetPostById() {
        Long postId = 1L;
        Post post = new Post();
        post.setId(postId);
        post.setTitle("Test Title");
        post.setContent("Test Content");
        post.setAuthor("John Doe");

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        PostDTO postDTO = postService.getPostById(postId);
        assertEquals("Test Title", postDTO.getTitle());
        assertEquals("John Doe", postDTO.getAuthor());
    }
}
