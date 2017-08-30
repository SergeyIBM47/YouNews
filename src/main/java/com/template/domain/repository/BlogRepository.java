package com.template.domain.repository;

import com.template.domain.model.Blog;
import com.template.domain.model.enums.CategoryType;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:00 PM
 * Project: Spring MVC
 */
public interface BlogRepository extends RootRepository<Blog, Long> {

    Blog findByName(String name);

    List<Blog> findAllByType(CategoryType type);
}
