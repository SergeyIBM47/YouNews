package com.template.service.impl;

import com.template.domain.model.Blog;
import com.template.domain.model.News;
import com.template.domain.model.User;
import com.template.domain.repository.BlogRepository;
import com.template.domain.repository.NewsRepository;
import com.template.domain.repository.RootRepository;
import com.template.domain.repository.UserRepository;
import com.template.exception.AlreadyExistsException;
import com.template.security.Crypto;
import com.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:03 PM
 * Project: Spring MVC
 */
@Service
@Transactional
public class UserServiceImpl extends RootServiceImpl<User> implements UserService {

    private final UserRepository userRepository;

    private final BlogRepository blogRepository;

    private final NewsRepository newsRepository;

    private final Crypto crypto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BlogRepository blogRepository, NewsRepository newsRepository, Crypto crypto) {
        this.userRepository = userRepository;
        this.blogRepository = blogRepository;
        this.newsRepository = newsRepository;
        this.crypto = crypto;
    }

    @Override
    public RootRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public Page<User> findAllPageable(Pageable paging) {
        return userRepository.findAll(paging);
    }

    @Override
    public User registerNewUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null)
            throw new AlreadyExistsException("User with this email are exist!");
        user.setPassword(crypto.encodePassword(user.getPassword()));
        user = userRepository.save(user);

        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User addSubscription(String email, String blogName) {
        User user = userRepository.findByEmail(email);
        Blog blog = blogRepository.findByName(blogName);

        user.getSubscription().add(blog);

        return userRepository.save(user);
    }

    @Override
    public User removeSubscription(String email, String blogName) {
        User user = userRepository.findByEmail(email);
        Blog blog = blogRepository.findByName(blogName);

        user.getSubscription().remove(blog);

        return userRepository.save(user);
    }

    @Override
    public List<News> getNewsBySubscription(String email) {
        User user = userRepository.findByEmail(email);
        return user.getSubscription().stream()
                .map(Blog::getName)
                .map(newsRepository::findAllByBlogName)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(News::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}
