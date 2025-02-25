package org.rene.blog.repository;

import org.junit.jupiter.api.Test;
import org.rene.blog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class TagRepositoryTests {

    @Autowired
    private TagRepository tagRepository;

    @Test
    public void TagRepository_Save_SavesTag() {
        // Arrange
        Tag tag = new Tag();
        tag.setName("Technology");

        // Act
        Tag savedTag = tagRepository.save(tag);

        // Assert
        assertThat(savedTag).isNotNull();
        assertThat(savedTag.getId()).isNotNull();
        assertThat(savedTag.getName()).isEqualTo("Technology");
    }

    @Test
    public void TagRepository_FindAll_ReturnsAllTags() {
        // Arrange
        Tag tag1 = new Tag();
        tag1.setName("Tech");

        Tag tag2 = new Tag();
        tag2.setName("Health");

        tagRepository.save(tag1);
        tagRepository.save(tag2);

        // Act
        List<Tag> tags = tagRepository.findAll();

        // Assert
        assertThat(tags).isNotNull();
        assertThat(tags.size()).isEqualTo(2);
    }

    @Test
    public void TagRepository_FindByName_ReturnsTag() {
        // Arrange
        Tag tag = new Tag();
        tag.setName("Science");
        tagRepository.save(tag);

        // Act
        Tag foundTag = tagRepository.findByName("Science");

        // Assert
        assertThat(foundTag).isNotNull();
        assertThat(foundTag.getName()).isEqualTo("Science");
    }
}
