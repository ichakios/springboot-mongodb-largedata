package com.napster.tags.repository;

import com.napster.tags.domain.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TagRepository  extends MongoRepository<Tag, String> {

    Page<Tag> findByUser(String user, Pageable pageable);

    Page<Tag> findByContentType(String contentType, Pageable pageable);
}
