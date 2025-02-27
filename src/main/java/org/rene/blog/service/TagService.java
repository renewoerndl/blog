package org.rene.blog.service;

import org.rene.blog.dto.TagDTO;
import org.rene.blog.model.Tag;
import org.rene.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * Get all tags.
     *
     * Returns
     * -------
     * List<TagDTO>
     *     A list of TagDTO objects representing all tags.
     */
    public List<TagDTO> getAllTags() {
        List<Tag> tags = tagRepository.findAll();
        return tags.stream().map(TagDTO::new).collect(Collectors.toList());
    }

    /**
     * Create a tag only if it doesn't exist.
     *
     * Parameters
     * ----------
     * tagDTO : TagDTO
     *     The TagDTO object containing the tag information.
     *
     * Returns
     * -------
     * TagDTO
     *     The created or existing TagDTO object.
     */
    public TagDTO createTag(TagDTO tagDTO) {
        Optional<Tag> existingTag = tagRepository.findByName(tagDTO.getName());

        return existingTag.map(TagDTO::new) // Return existing tag if found
                .orElseGet(() -> {
                    Tag newTag = new Tag(tagDTO.getName());
                    return new TagDTO(tagRepository.save(newTag));
                });
    }
}
