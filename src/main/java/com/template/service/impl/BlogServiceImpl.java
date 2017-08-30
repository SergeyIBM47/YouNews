package com.template.service.impl;

import com.template.domain.model.Blog;
import com.template.domain.model.enums.CategoryType;
import com.template.domain.repository.BlogRepository;
import com.template.domain.repository.RootRepository;
import com.template.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/25/2016
 * Time: 7:32 PM
 * Project: Diplom
 */
@Service
@Transactional
public class BlogServiceImpl extends RootServiceImpl<Blog> implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public RootRepository<Blog, Long> getRepository() {
        return blogRepository;
    }

    @Override
    public Class<Blog> getEntityClass() {
        return Blog.class;
    }

    @Override
    public List<Blog> findAllByType(CategoryType type) {
        return blogRepository.findAllByType(type);
    }

}
