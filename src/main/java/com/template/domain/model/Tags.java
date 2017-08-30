package com.template.domain.model;

import com.template.domain.IdModel;

import javax.persistence.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 2:49 PM
 * Project: Spring MVC
 */
@Entity
@Table(name = "tags")
@Access(AccessType.FIELD)
public class Tags implements IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private String name;

    public Tags(){}

    public Tags(String name) {
        this.name = name;
    }

    public Tags(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
