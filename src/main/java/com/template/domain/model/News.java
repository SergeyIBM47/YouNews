package com.template.domain.model;

import com.template.domain.IdModel;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 2:47 PM
 * Project: Spring MVC
 */
@Entity
@Table(name = "blogs")
@Access(AccessType.FIELD)
public class News implements IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "blog_topic_name", nullable = false)
    private String topic;

    @Column(name = "blog_description", nullable = false)
    private String description;

    @Column(name = "blog_content", nullable = false)
    private String content;

    @URL
    @Column(name = "blog_photo_url")
    private String photoUrl;

    @Column(name = "created_at", unique = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @JoinColumn(name = "category_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Blog blog;

    @JoinColumn(name = "rate_id")
    @OneToOne(fetch = FetchType.EAGER)
    private Rate rate;

    @Column(name = "blog_tag")
    @OneToMany(fetch = FetchType.EAGER)
    private List<Tags> tags;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comments> comments;

    public News(){}

    public News(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
