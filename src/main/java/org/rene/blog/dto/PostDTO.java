package org.rene.blog.dto;

import org.rene.blog.model.Post;
import java.util.Set;
import java.util.stream.Collectors;

public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Set<String> tags;

    // ✅ Default constructor (needed for Jackson)
    public PostDTO() {}

    // ✅ Constructor to create PostDTO from a Post entity
    public PostDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.tags = post.getTags().stream().map(tag -> tag.getName()).collect(Collectors.toSet());
    }

    // ✅ Constructor to create PostDTO manually (for tests)
    public PostDTO(Long id, String title, String content, String author, Set<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.tags = tags;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }
}
