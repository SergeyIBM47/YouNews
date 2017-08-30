package com.template.domain.model;

import com.template.domain.IdModel;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.*;
import static javax.persistence.TemporalType.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 4:26 PM
 * Project: Spring MVC
 */
@Entity
@Table(name = "blog_comments")
@Access(value = FIELD)
public class Comments implements IdModel {

    @Id
    @GeneratedValue(strategy = TABLE)
    private Long id;

    @Column(name = "comment")
    private String comment;

    @Temporal(DATE)
    private Date date = Date.from(Instant.now());

    @ManyToOne
    private User user;

    @ManyToOne
    private News news;

    public Comments() {}

    public Comments(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
//
//    public News getBlog() {
//        return news;
//    }
//
//    public void setBlog(News news) {
//        this.news = news;
//    }
//
    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
