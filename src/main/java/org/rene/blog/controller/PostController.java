package org.rene.blog.controller;

import org.rene.blog.model.Post;
import org.rene.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     * @return a list of all blog posts
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return  postService.getAllPosts();
    }

    /**
     * Retrieves a post by its ID.
     *
     * @param id the ID of the post to retrieve
     * @return a ResponseEntity containing the post if found, or a not found response if the post does not exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new blog post with the specified tags.
     *
     * @param post the blog post to create
     * @param tags the set of tags to associate with the post
     * @return a ResponseEntity containing the created blog post
     */
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post, @RequestParam Set<String> tags) {
        Post createdPost = postService.createPostWithTags(post, tags);
        return ResponseEntity.ok(createdPost);
    }

    /**
     * Updates an existing blog post identified by its ID with the provided data.
     *
     * @param id the ID of the blog post to update
     * @param post the updated blog post details
     * @return a ResponseEntity containing the updated blog post
     */
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        return ResponseEntity.ok(postService.updatePost(id, post));
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
