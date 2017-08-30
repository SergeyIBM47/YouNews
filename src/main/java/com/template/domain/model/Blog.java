package com.template.domain.model;

import com.template.domain.IdModel;
import com.template.domain.model.enums.CategoryType;

import javax.persistence.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 6:31 PM
 * Project: Spring MVC
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = "blog", uniqueConstraints = @UniqueConstraint(columnNames = {"name", "type"}))
public class Blog implements IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CategoryType type;

    @ManyToOne
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Blog blog = (Blog) o;

        if (name != null ? !name.equals(blog.name) : blog.name != null) return false;
        if (type != blog.type) return false;
        return user != null ? user.equals(blog.user) : blog.user == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
