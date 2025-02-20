package org.rene.blog.service;

import org.rene.blog.model.Post;
import org.rene.blog.model.Tag;
import org.rene.blog.repository.PostRepository;
import org.rene.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.Optional;

/**
 * Service class for managing blog posts.
 * This class handles the business logic for operations such as creating, retrieving, updating,
 * and deleting posts. It interacts with the data repository to perform these operations.
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }



    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    /**
     * Creates a new blog post and associates it with the provided set of tags.
     * If a tag does not already exist in the repository, it will be created and saved.
     * The post is then saved with its associated tags in the repository.
     *
     * @param post the blog post to be created
     * @param tagNames the set of tag names to associate with the blog post
     * @return the created blog post with associated tags
     */
    public Post createPostWithTags(Post post, Set<String> tagNames) {
        for (String tagName : tagNames) {
            Tag tag = tagRepository.findByName(tagName);
            if (tag == null) {
                tag = new Tag(tagName);
                tagRepository.save(tag);
            }
            post.getTags().add(tag);
        }
        return postRepository.save(post);
    }

    public Post updatePost(Long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(updatedPost.getTitle());
                    post.setContent(updatedPost.getContent());
                    post.setTags(updatedPost.getTags());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<Post> searchByTitle(String title) {
        return postRepository.findByTitleContainingIgnoreCase(title);
    }
}
