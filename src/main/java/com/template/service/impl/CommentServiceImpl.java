package com.template.service.impl;

import com.template.domain.model.Comments;
import com.template.domain.model.News;
import com.template.domain.model.User;
import com.template.domain.repository.CommentRepository;
import com.template.domain.repository.NewsRepository;
import com.template.domain.repository.RootRepository;
import com.template.service.CommentService;
import org.hibernate.Hibernate;
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
public class CommentServiceImpl extends RootServiceImpl<Comments> implements CommentService {

    private final CommentRepository commentRepository;

    private final NewsRepository newsRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, NewsRepository newsRepository) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
    }

    @Override
    public RootRepository<Comments, Long> getRepository() {
        return commentRepository;
    }

    @Override
    public Class<Comments> getEntityClass() {
        return Comments.class;
    }

    @Override
    public List<Comments> findAll(Sort sort) {
        return commentRepository.findAll(sort);
    }

    @Transactional
    @Override
    public Comments addComment(News news, User user, Comments comments) {
        comments.setUser(user);
        comments.setNews(news);

        insert(comments);

        Hibernate.initialize(news.getComments());
        news.getComments().add(comments);

        return comments;
    }

    @Override
    public Page<Comments> findAllPageable(Pageable paging) {
        return commentRepository.findAll(paging);
    }
}
