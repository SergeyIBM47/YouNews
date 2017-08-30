package com.template.web.form;

import com.template.domain.model.Tags;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 3:40 PM
 * Project: Spring MVC
 */
public class TagsForm extends BaseForm<Tags> {

    private String name;

    public TagsForm(Tags tags) {
        this.id = tags.getId();
        this.name = tags.getName();
    }

    @Override
    public Tags getEntity() {
        return new Tags(id, name);
    }

    @Override
    public TagsForm getForm(Tags entity) {
        return new TagsForm(entity.getId(), entity.getName());
    }

    public TagsForm(){}

    public TagsForm(String name) {
        this.name = name;
    }

    public TagsForm(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
