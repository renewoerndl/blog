package org.rene.blog.controller;

import org.rene.blog.dto.PostDTO;
import org.rene.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * REST controller for managing blog posts.
 * Provides endpoints for creating, retrieving, updating, and deleting blog posts.
 * Also includes functionality to associate posts with tags.
 *
 * Mapped to API endpoints under "/api/posts".
 */
@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    /**
     * Constructs a PostController with the specified PostService.
     *
     * @param postService the PostService instance to manage blog post operations
     */
    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    /**
     * Retrieves a list of all blog posts.
     *
     * @return a list of all blog posts as DTOs
     */
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return a ResponseEntity containing the post DTO if found, or a not found response if the post does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPost(@PathVariable Long id) {
        PostDTO postDTO = postService.getPostById(id);
        return ResponseEntity.ok(postDTO);
    }


    /**
     * Creates a new blog post with the specified tags.
     *
     * @param postDTO the blog post data transfer object
     * @return a ResponseEntity containing the created blog post DTO
     */
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        PostDTO createdPost = postService.createPost(postDTO);
        return ResponseEntity.ok(createdPost);
    }

    /**
     * Creates a new blog post with tags.
     *
     * @param postDTO the blog post DTO containing title, content, author
     * @param tags    optional list of tags to associate with the post
     * @return ResponseEntity with the created post DTO
     */
    @PostMapping("/with-tags")
    public ResponseEntity<PostDTO> createPostWithTags(@RequestBody PostDTO postDTO,
                                                      @RequestParam(required = false) Set<String> tags) {
        PostDTO createdPost = postService.createPostWithTags(postDTO, tags);
        return ResponseEntity.ok(createdPost);
    }

    /**
     * Updates an existing blog post identified by its ID with the provided data.
     *
     * @param id      the ID of the blog post to update
     * @param postDTO the updated blog post details
     * @return a ResponseEntity containing the updated blog post DTO
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO) {
        PostDTO updatedPost = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(updatedPost);
    }


    /**
     * Deletes a blog post identified by its ID.
     *
     * @param id the ID of the blog post to delete
     * @return a ResponseEntity with no content status
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
