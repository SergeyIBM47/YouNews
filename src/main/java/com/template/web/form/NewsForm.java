package com.template.web.form;

import com.template.domain.model.News;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 3:05 PM
 * Project: Spring MVC
 */
public class NewsForm extends BaseForm<News> {

    private String topic;

    private String description;

    private String content;

    private String photoUrl;

    private BlogForm blog;

    private List<TagsForm> tags = new ArrayList<>();

    private List<CommentsForm> comments = new ArrayList<>();

    public NewsForm() {}

    public NewsForm(News entity) {
        this.id = entity.getId();
        this.topic = entity.getTopic();
        this.description = entity.getDescription();
        this.content = entity.getContent();
        this.photoUrl = entity.getPhotoUrl();
        if (entity.getTags() != null) {
            this.tags = entity.getTags().stream().map(TagsForm::new).collect(Collectors.toList());
        }
//        if (entity.getComments() != null)
//            this.comments = entity.getComments().stream().map(CommentsForm::new).collect(Collectors.toList());
    }

    @Override
    public News getEntity() {
        News news = new News();
        news.setId(id);
        news.setTopic(topic);
        news.setDescription(description);
        news.setContent(content);
        news.setPhotoUrl(photoUrl);
        if (blog != null){
          news.setBlog(blog.getEntity());
        }
        if (!tags.isEmpty())
            news.setTags(tags.stream().map(TagsForm::getEntity).collect(Collectors.toList()));
        if (!comments.isEmpty())
            news.setComments(comments.stream().map(CommentsForm::getEntity).collect(Collectors.toList()));

        return news;
    }

    @Override
    public NewsForm getForm(News entity) {
        NewsForm newsForm = new NewsForm();
        newsForm.setId(entity.getId());
        newsForm.setTopic(entity.getTopic());
        newsForm.setDescription(entity.getDescription());
        newsForm.setContent(entity.getContent());
        newsForm.setPhotoUrl(entity.getPhotoUrl());
        if (entity.getTags() != null)
            newsForm.setTags(entity.getTags().stream().map((t) -> new TagsForm().getForm(t)).collect(Collectors.toList()));
        if (entity.getComments() != null)
            newsForm.setComments(entity.getComments().stream().map((t) -> new CommentsForm().getForm(t)).collect(Collectors.toList()));
        return newsForm;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public BlogForm getBlog() {
        return blog;
    }

    public void setBlog(BlogForm blog) {
        this.blog = blog;
    }

    public List<TagsForm> getTags() {
        return tags;
    }

    public void setTags(List<TagsForm> tags) {
        this.tags = tags;
    }

    public List<CommentsForm> getComments() {
        return comments;
    }

    public void setComments(List<CommentsForm> comments) {
        this.comments = comments;
    }
}
