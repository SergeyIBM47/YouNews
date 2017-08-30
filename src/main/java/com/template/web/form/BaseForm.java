package com.template.web.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 2:59 PM
 * Project: Spring MVC
 */
public abstract class BaseForm<T> implements IdForm {

    protected Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public abstract T getEntity();

    public abstract BaseForm getForm(T entity);

}
