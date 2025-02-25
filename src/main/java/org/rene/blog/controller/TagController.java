package org.rene.blog.controller;

import org.rene.blog.dto.TagDTO;
import org.rene.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    private final TagService tagService;

    /**
     * Constructs a TagController with the specified TagService.
     *
     * @param tagService the TagService instance to manage tag operations
     */
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    /**
     * Retrieves all tags available in the system.
     *
     * @return a list of all TagDTO objects
     */
    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags() {
        List<TagDTO> tags = tagService.getAllTags();
        return ResponseEntity.ok(tags);
    }

    /**
     * Creates a new tag based on the provided tag name.
     * Ensures tags are unique.
     *
     * @param tagDTO the tag object to be created
     * @return the created TagDTO object
     */
    @PostMapping
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO tagDTO) {
        TagDTO createdTag = tagService.createTag(tagDTO);
        return ResponseEntity.ok(createdTag);
    }
}
