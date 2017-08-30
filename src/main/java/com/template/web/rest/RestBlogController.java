package com.template.web.rest;

import com.template.domain.model.Blog;
import com.template.domain.model.News;
import com.template.domain.model.Comments;
import com.template.domain.model.User;
import com.template.domain.model.enums.CategoryType;
import com.template.service.NewsService;
import com.template.service.BlogService;
import com.template.service.CommentService;
import com.template.service.UserService;
import com.template.web.form.NewsForm;
import com.template.web.form.BlogForm;
import com.template.web.form.CommentsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:48 PM
 * Project: Spring MVC
 */
@RestController
@RequestMapping(value = "/rest/blogs", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestBlogController {

    private final NewsService newsService;

    private final BlogService blogService;

    private final CommentService commentService;

    private final UserService userService;

    @Autowired
    public RestBlogController(NewsService newsService, BlogService blogService, CommentService commentService, UserService userService) {
        this.newsService = newsService;
        this.blogService = blogService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<BlogForm>>> getAllBlogCategory() {
        return supplyAsync(() -> new ResponseEntity<>(blogService
                .findAllByType(CategoryType.BLOG).stream()
                .map(BlogForm::new)
                .collect(Collectors.toList()), HttpStatus.OK));
    }

    @RequestMapping(value = "/one/{id}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<?>> getBlog(@PathVariable("id") Long id) {
        return supplyAsync(() -> new ResponseEntity<>(new NewsForm(newsService.get(id)), HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<NewsForm> postNewNews(@RequestBody @Valid NewsForm newNews) {
        News news = newsService.insert(newNews.getEntity());
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user.getOwnerNews().add(news);
        news = newsService.insert(news);
        return new ResponseEntity<>(newNews.getForm(news), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updateBlog(@RequestBody @Valid NewsForm form) {
        newsService.update(form.getEntity());
        return new ResponseEntity<>("Successfully updated blog id: " + form.getEntity(), HttpStatus.OK);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<CommentsForm>> postNewComment(@RequestBody CommentsForm form) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> {
            News news = newsService.get(form.getBlogId());
            User user = userService.findByEmail(email);

            Comments entity = form.getEntity();
            entity.setUser(user);

            Comments comment = commentService.insert(entity);
            news.getComments().add(comment);

            newsService.insert(news);

            return new ResponseEntity<>(new CommentsForm().getForm(comment), HttpStatus.OK);
        });
    }

    @RequestMapping(value = "/categories/{blogName}", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<NewsForm>>> getBlogByCategory(@PathVariable("blogName") String blogName) {
        return supplyAsync(() -> new ResponseEntity<>(
                newsService.findAllByYouPublicName(blogName)
                        .stream().map(NewsForm::new)
                        .collect(Collectors.toList()), HttpStatus.OK));
    }

    @RequestMapping(value = "/categories/{blogName}/subscribe", method = RequestMethod.PUT)
    public CompletableFuture<ResponseEntity<?>> subscribe(@PathVariable("blogName") String blogName) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> new ResponseEntity<>(userService.addSubscription(email, blogName), HttpStatus.OK));
    }

    @RequestMapping(value = "/categories/{blogName}/unsubscribe", method = RequestMethod.PUT)
    public CompletableFuture<ResponseEntity<?>> unsubscribe(@PathVariable("blogName") String blogName) {
        final String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> new ResponseEntity<>(userService.removeSubscription(email, blogName), HttpStatus.OK));
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<BlogForm>>> getBlogByCategory() {
        return supplyAsync(() -> new ResponseEntity<>(
                blogService.findAllByType(CategoryType.BLOG)
                        .stream().map(BlogForm::new)
                        .collect(Collectors.toList()), HttpStatus.OK));
    }

    @RequestMapping(value = "/categories", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<BlogForm>> createBlog(@RequestBody @Valid BlogForm form) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> {
            User user = userService.findByEmail(email);
            Blog blog = form.getEntity();
            blog.setUser(user);
            user.getOwnedBlogs().add(blog);
            blog = blogService.insert(blog);
            userService.insert(user);
            return new ResponseEntity<>(new BlogForm(blog), HttpStatus.OK);
        });
    }

}