package org.rene.blog.controller;

import org.rene.blog.model.Tag;
import org.rene.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing tags in the system.
 * Provides endpoints for retrieving and creating tags.
 * This controller is mapped to API endpoints under "/api/tags".
 */
@RestController
@RequestMapping("/api/tags")
public class TagController {

    /**
     * Service instance for handling operations related to tags.
     * Provides access to methods for retrieving, creating, and managing tags
     * through the {@link TagService}.
     *
     * This variable is automatically injected by Spring's dependency injection mechanism.
     */
    @Autowired
    private TagService tagService;

    /**
     * Retrieves all tags available in the system.
     *
     * @return a list of all Tag objects
     */
    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    /**
     * Creates a new tag based on the provided tag data.
     *
     * @param tag the tag object to be created
     * @return the created tag object
     */
    @PostMapping
    public Tag createTag(@RequestBody Tag tag) {
        return tagService.createTag(tag);
    }
}
