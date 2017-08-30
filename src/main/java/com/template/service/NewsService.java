package com.template.service;

import com.template.domain.model.News;
import com.template.domain.model.enums.CategoryType;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:02 PM
 * Project: Spring MVC
 */
public interface NewsService extends RootService<News> {

    List<News> findAllByYouPublicType(CategoryType categoryType);

    List<News> findAllByYouPublicName(String name);

    News findBlogByYouPublicNameAndTopic(String catName, String topic);

}
