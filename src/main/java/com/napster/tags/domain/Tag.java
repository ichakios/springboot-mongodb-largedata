package com.napster.tags.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Tag {

    public Tag(String id, String user, String tag, Date tagTime, String contentType, String contentId) {
        this.id = id;
        this.user = user;
        this.tag = tag;
        this.tagTime = tagTime;
        this.contentType = contentType;
        this.contentId = contentId;
    }

    @Id
    private String id;

    @Indexed
    private String user;

    private String tag;

    private Date tagTime;

    private String contentType;

    private String contentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTagTime() {
        return tagTime;
    }

    public void setTagTime(Date tagTime) {
        this.tagTime = tagTime;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }
}
