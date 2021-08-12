package com.napster.tags.services;

import com.napster.tags.domain.Tag;
import com.napster.tags.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    private TagRepository tagRepository;
    public TagService(final TagRepository tagRepository) {

        this.tagRepository = tagRepository;
    }

    public Page<Tag> fetchByUserOrTag(String query, Pageable pageable) {
        return tagRepository.findByUser(query, pageable);
    }

    public Long count() {
        return tagRepository.count();
    }

    public Page<Tag> fetchByContentType(String query, Pageable pageable) {
        return tagRepository.findByContentType(query, pageable);
    }

    public Tag createATag(Tag tag) {

        return tagRepository.save(tag);
    }
}
