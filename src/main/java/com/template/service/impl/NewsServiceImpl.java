package com.template.service.impl;

import com.template.domain.model.News;
import com.template.domain.model.enums.CategoryType;
import com.template.domain.repository.NewsRepository;
import com.template.domain.repository.RootRepository;
import com.template.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:03 PM
 * Project: Spring MVC
 */
@Service
public class NewsServiceImpl extends RootServiceImpl<News> implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public RootRepository<News, Long> getRepository() {
        return newsRepository;
    }

    @Override
    public Class<News> getEntityClass() {
        return News.class;
    }

    @Override
    public List<News> findAll(Sort sort) {
        return newsRepository.findAll(sort);
    }

    @Override
    public Page<News> findAllPageable(Pageable paging) {
        return newsRepository.findAll(paging);
    }

    @Override
    public List<News> findAllByYouPublicType(CategoryType categoryType) {
        return newsRepository.findAllByBlogType(categoryType);
    }

    @Override
    public List<News> findAllByYouPublicName(String name) {
        return newsRepository.findAllByBlogName(name);
    }

    @Override
    public News findBlogByYouPublicNameAndTopic(String catName, String topic) {
        return newsRepository.findBlogByBlogNameAndTopic(catName, topic);
    }
}
