package com.template.domain.model;

import com.template.domain.IdModel;

import javax.persistence.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/24/2016
 * Time: 5:07 PM
 * Project: Diplom
 */
@Entity
@Table(name = "rating")
public class Rate  implements IdModel{

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "like_count")
    private Integer like;

    @Column(name = "dislike_count")
    private Integer dislike;

    @Column(name = "star")
    private Integer star;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "shared")
    private Integer shared;

    @OneToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
