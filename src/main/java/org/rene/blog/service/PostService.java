package org.rene.blog.service;

import org.rene.blog.dto.PostDTO;
import org.rene.blog.model.Post;
import org.rene.blog.model.Tag;
import org.rene.blog.repository.PostRepository;
import org.rene.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    // Get all posts (returning DTOs)
    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(PostDTO::new).collect(Collectors.toList());
    }

    // Get a single post by ID (returning Optional DTO)
    public PostDTO getPostById(Long id) {
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            return new PostDTO(postOptional.get());
        }
        throw new RuntimeException("Post not found");
    }

    // Create a new post from DTO
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        // Save Post
        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost);
    }

    // Create a new post with tags
    public PostDTO createPostWithTags(PostDTO postDTO, Set<String> tagNames) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        // Convert tag names to Tag entities
        Set<Tag> tags = tagNames.stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName)))) // Create if not exists
                .collect(Collectors.toSet());

        post.setTags(tags);
        Post savedPost = postRepository.save(post);
        return new PostDTO(savedPost);
    }

    // Update an existing post
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(postDTO.getAuthor());

        // Update tags
        Set<Tag> tags = postDTO.getTags().stream()
                .map(tagName -> tagRepository.findByName(tagName)
                        .orElseGet(() -> tagRepository.save(new Tag(tagName))))
                .collect(Collectors.toSet());

        post.setTags(tags);
        Post updatedPost = postRepository.save(post);
        return new PostDTO(updatedPost);
    }

    // Delete a post
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new RuntimeException("Post not found");
        }
        postRepository.deleteById(id);
    }
}
