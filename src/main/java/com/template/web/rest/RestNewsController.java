package com.template.web.rest;

import com.template.domain.model.enums.CategoryType;
import com.template.security.UserDetails;
import com.template.service.BlogService;
import com.template.service.UserService;
import com.template.web.form.BlogForm;
import com.template.web.form.NewsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/25/2016
 * Time: 7:44 PM
 * Project: Diplom
 */
@RestController
@RequestMapping(value = "/rest/news", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestNewsController {

    private final BlogService blogService;
    private final UserService userService;

    @Autowired
    public RestNewsController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<NewsForm>>> list(){
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> new ResponseEntity<>(userService.getNewsBySubscription(email)
                .stream().map(NewsForm::new).collect(Collectors.toList()), HttpStatus.OK));
    }

    @RequestMapping(value = "/blogs", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<BlogForm>>> getBlogCategory() {
        return supplyAsync(() -> new ResponseEntity<>(blogService.findAllByType(CategoryType.BLOG)
                .stream().map(BlogForm::new)
                .collect(Collectors.toList())
                , HttpStatus.OK));
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<BlogForm>>> getProjectsCategory() {
        return supplyAsync(() -> new ResponseEntity<>(blogService.findAllByType(CategoryType.PROJECT)
                .stream().map(BlogForm::new)
                .collect(Collectors.toList())
                , HttpStatus.OK));
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<BlogForm>>> getNewsCategory() {
        return supplyAsync(() -> new ResponseEntity<>(blogService.findAllByType(CategoryType.NEWS)
                .stream().map(BlogForm::new)
                .collect(Collectors.toList())
                , HttpStatus.OK));
    }

}
