package org.rene.blog.repository;

import org.rene.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Tag entities.
 * This interface provides CRUD operations for Tag entities and extends JpaRepository,
 * inheriting standard database operation capabilities.
 * Additional custom query methods, specific to Tag, can be defined here if needed.
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
