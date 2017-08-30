package com.template.web.form;

import com.template.domain.model.Blog;
import com.template.domain.model.enums.CategoryType;

import static com.template.domain.model.enums.CategoryType.BLOG;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 3:44 PM
 * Project: Spring MVC
 */
public class BlogForm extends BaseForm<Blog> {

    private String name;

    private String description;

    private String photoUrl;

    private CategoryType type = BLOG;

    public BlogForm() {
    }

    public BlogForm(Blog entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.photoUrl = entity.getPhotoUrl();
        this.type = entity.getType();
    }

    @Override
    public Blog getEntity() {
        Blog blog = new Blog();
        blog.setId(id);
        blog.setName(name);
        blog.setDescription(description);
        blog.setPhotoUrl(photoUrl);
        blog.setType(type);

        return blog;
    }

    @Override
    public BlogForm getForm(Blog entity) {
        BlogForm form = new BlogForm();
        form.setId(entity.getId());
        form.setName(entity.getName());
        form.setDescription(entity.getDescription());
        form.setPhotoUrl(entity.getPhotoUrl());
        form.setType(entity.getType());

        return form;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }
}
