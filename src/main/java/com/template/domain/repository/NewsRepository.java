package com.template.domain.repository;

import com.template.domain.model.News;
import com.template.domain.model.enums.CategoryType;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:00 PM
 * Project: Spring MVC
 */
public interface NewsRepository extends RootRepository<News, Long> {

    List<News> findAllByBlogType(CategoryType categoryType);

    List<News> findAllByBlogName(String name);

    News findBlogByBlogNameAndTopic(String catName, String topic);

}
