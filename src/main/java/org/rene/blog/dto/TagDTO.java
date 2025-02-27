package org.rene.blog.dto;

import org.rene.blog.model.Tag;

/**
 * TagDTO is a Data Transfer Object for the Tag entity.
 * It is used to transfer data between different layers of the application.
 *
 * Attributes
 * ----------
 * id : Long
 *     The unique identifier of the tag.
 * name : String
 *     The name of the tag.
 *
 * Methods
 * -------
 * TagDTO(Tag tag)
 *     Constructor that initializes the TagDTO with a Tag entity.
 * getId()
 *     Returns the unique identifier of the tag.
 * setId(Long id)
 *     Sets the unique identifier of the tag.
 * getName()
 *     Returns the name of the tag.
 * setName(String name)
 *     Sets the name of the tag.
 */
public class TagDTO {
    private Long id;
    private String name;

    public TagDTO(Tag tag) {
        this.id = tag.getId();
        this.name = tag.getName();
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
}
