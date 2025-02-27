package org.rene.blog.repository;

import org.junit.jupiter.api.Test;
import org.rene.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class TagRepositoryTests {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void testFindByName() {
        Tag tag = new Tag("Spring Boot");
        tagRepository.save(tag);

        Optional<Tag> foundTag = tagRepository.findByName("Spring Boot");
        assertTrue(foundTag.isPresent());
    }
}
