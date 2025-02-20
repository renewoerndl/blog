package org.rene.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a blog post entity in the system.
 * This class is mapped to the "posts" table in the database.
 * It contains attributes associated with a blog post such as ID, title, content, and author.
 * Includes constructors, getters, setters, and a toString method for debugging purposes.
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @Column(nullable = false, length = 200)
    private String author;

    /**
     * Represents the many-to-many relationship between posts and tags.
     * Each post can be associated with multiple tags, and each tag can belong to multiple posts.
     * This relationship is managed through a join table named "post_tags" that links the "posts" and "tags" tables.
     * The join table includes "post_id" and "tag_id" as foreign keys referencing the respective tables.
     *
     * The `@JsonIgnoreProperties` annotation is applied to prevent potential infinite recursion issues
     * during JSON serialization and deserialization by ignoring the "posts" field in the Tag entity.
     */
    // Many-to-Many Relationship with Tag
    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @JsonIgnoreProperties("posts") // Prevent infinite loop
    private Set<Tag> tags = new HashSet<>();

    // Default constructor
    public Post() {}

    // Parameterized constructor
    public Post(Long id, String title, String content, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Returns a string representation of the Post object.
     * The representation includes the values of the id, title, and content fields.
     *
     * @return a string representing the Post object with its id, title, and content.
     */
    // toString() method for debugging
    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", tags=" + tags +
                '}';
    }
}
