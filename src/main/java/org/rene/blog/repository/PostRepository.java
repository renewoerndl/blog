package org.rene.blog.repository;

import org.rene.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Repository interface for managing Post entities.
 * This interface provides methods for performing CRUD operations
 * on the Post entity, such as saving, finding, updating, and deleting.
 * It extends JpaRepository to inherit standard database operation capabilities.
 * Additional custom query methods for Post can be defined here if required.
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // Custom Query Method: Find posts by title (case insensitive)
    List<Post> findByTitleContainingIgnoreCase(String title);

    // Custom Query Method: Find posts by content
    List<Post> findByContentContainingIgnoreCase(String content);
}
