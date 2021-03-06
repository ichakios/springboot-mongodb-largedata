package com.napster.tags.controller;


import com.napster.tags.domain.Tag;
import com.napster.tags.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/searchByContentType")
    @ResponseBody
    public ResponseEntity<Page<Tag>> fetchByContentType(
            @RequestParam(value = "q", required = false) String query,
            Pageable pageable) {
        return ResponseEntity.ok(tagService.fetchByContentType(query,pageable));
    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<Tag> fetchByUserOrTag(
            @RequestParam(value = "q", required = false) String query) {
        return ResponseEntity.ok(tagService.fetchByUserOrTag(query));
    }


    @GetMapping("/count")
    @ResponseBody
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(tagService.count());
    }

}
