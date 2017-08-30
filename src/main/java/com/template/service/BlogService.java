package com.template.service;

import com.template.domain.model.Blog;
import com.template.domain.model.enums.CategoryType;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/25/2016
 * Time: 7:31 PM
 * Project: Diplom
 */
public interface BlogService extends RootService<Blog>  {

    List<Blog> findAllByType(CategoryType type);

}
