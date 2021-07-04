package com.napster.tags.services;

import com.napster.tags.domain.Tag;
import com.napster.tags.repository.TagRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private TagRepository tagRepository;
    public TagService(final TagRepository tagRepository) {

        this.tagRepository = tagRepository;
    }

    public Tag fetchByUserOrTag(String query) {
        Optional<Tag> tagOp = tagRepository.findByUser(query);
        return tagOp.get();
    }

    public Long count() {
        return tagRepository.count();
    }

    public Page<Tag> fetchByContentType(String query, Pageable pageable) {
        return tagRepository.findByContentType(query, pageable);
    }
}
