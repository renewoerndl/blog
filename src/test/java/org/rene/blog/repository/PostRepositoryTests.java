package org.rene.blog.repository;

import org.junit.jupiter.api.Test;
import org.rene.blog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PostRepositoryTests {

    @Autowired
    private PostRepository postRepository;

    @Test
    public void PostRepository_FindAll_ReturnsAllPosts() {
        // Arrange
        Post post1 = new Post();
        post1.setTitle("First Post");
        post1.setContent("This is the first post.");

        Post post2 = new Post();
        post2.setTitle("Second Post");
        post2.setContent("This is the second post.");

        postRepository.save(post1);
        postRepository.save(post2);

        // Act
        List<Post> posts = postRepository.findAll();

        // Assert
        assertThat(posts).isNotNull();
        assertThat(posts.size()).isEqualTo(2);
    }

    @Test
    public void PostRepository_FindById_ReturnsPostWithId() {
        // Arrange
        Post post = new Post();
        post.setTitle("Unique Post");
        post.setContent("Content of unique post.");

        Post savedPost = postRepository.save(post);

        // Act
        Optional<Post> foundPost = postRepository.findById(savedPost.getId());

        // Assert
        assertThat(foundPost).isPresent();
        assertThat(foundPost.get().getId()).isEqualTo(savedPost.getId());
    }

    @Test
    public void PostRepository_Save_SavesPost() {
        // Arrange
        Post post = new Post();
        post.setTitle("New Post");
        post.setContent("This is a new post.");

        // Act
        Post savedPost = postRepository.save(post);

        // Assert
        assertThat(savedPost).isNotNull();
        assertThat(savedPost.getId()).isNotNull();
        assertThat(savedPost.getTitle()).isEqualTo("New Post");
        assertThat(savedPost.getContent()).isEqualTo("This is a new post.");
    }
}
