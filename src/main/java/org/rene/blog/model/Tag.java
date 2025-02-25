package org.rene.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Tag entity in the system.
 * This class is mapped to the "tags" table in the database.
 * A tag can be associated with multiple posts through a many-to-many relationship.
 */
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    /**
     * Many-to-Many relationship with Post.
     * A tag can be associated with multiple posts, and a post can have multiple tags.
     * This relationship is managed through the "post_tags" join table.
     */
    @ManyToMany(mappedBy = "tags")
    @JsonIgnore // Prevents infinite recursion when serializing JSON
    private Set<Post> posts = new HashSet<>();

    // Default constructor (needed by JPA)
    public Tag() {}

    // Parameterized constructor
    public Tag(String name) {
        this.name = name;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    /**
     * Helper method to add a post to this tag.
     *
     * @param post The post to associate with this tag
     */
    public void addPost(Post post) {
        this.posts.add(post);
        post.getTags().add(this);
    }

    /**
     * Helper method to remove a post from this tag.
     *
     * @param post The post to disassociate from this tag
     */
    public void removePost(Post post) {
        this.posts.remove(post);
        post.getTags().remove(this);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
