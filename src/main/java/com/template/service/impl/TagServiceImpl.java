package com.template.service.impl;

import com.template.domain.model.Tags;
import com.template.domain.repository.RootRepository;
import com.template.domain.repository.TagRepository;
import com.template.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:03 PM
 * Project: Spring MVC
 */
@Service
@Transactional
public class TagServiceImpl extends RootServiceImpl<Tags> implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Override
    public RootRepository<Tags, Long> getRepository() {
        return tagRepository;
    }

    @Override
    public Class<Tags> getEntityClass() {
        return Tags.class;
    }

    @Override
    public List<Tags> findAll(Sort sort) {
        return tagRepository.findAll(sort);
    }

    @Override
    public Page<Tags> findAllPageable(Pageable paging) {
        return tagRepository.findAll(paging);
    }
}
