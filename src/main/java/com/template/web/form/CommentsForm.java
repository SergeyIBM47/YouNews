package com.template.web.form;

import com.template.domain.model.Comments;
import com.template.domain.model.User;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 3:31 PM
 * Project: Spring MVC
 */
public class CommentsForm extends BaseForm<Comments> {

    private String comment;

    private String userNick;

    private Long userId;

    private Long blogId;

    private Long newsId;

    private Date date;

    public CommentsForm() {}

    public CommentsForm(Comments entity) {
        this.comment = entity.getComment();
        this.date = entity.getDate();
        if (entity.getUser() != null) {
            this.userId = entity.getUser().getId();
            this.userNick = entity.getUser().getNickName();
        }

//        if (entity.getBlog() != null)
//            form.setBlogId(entity.getBlog().getId());
//        if (entity.getNews() != null)
//            form.setNewsId(entity.getNews().getId());
    }

    @Override
    public Comments getEntity() {
        Comments comments = new Comments();
        comments.setId(id);
        comments.setComment(comment);
        comments.setDate(date);
        comments.setUser(new User(userId));

//        if (blogId != null)
//            comments.setBlog(new News(blogId));
//        if (newsId != null)
//            comments.setNews(new News(newsId));

        return comments;
    }

    @Override
    public CommentsForm getForm(@Valid Comments entity) {
        CommentsForm form = new CommentsForm();
        form.setId(entity.getId());
        form.setComment(entity.getComment());
        form.setDate(entity.getDate());
        if (entity.getUser() != null) {
            form.setUserId(entity.getUser().getId());
            form.setUserNick(entity.getUser().getNickName());
        }
//        if (entity.getBlog() != null)
//            form.setBlogId(entity.getBlog().getId());
//        if (entity.getNews() != null)
//            form.setNewsId(entity.getNews().getId());

        return form;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Long getNewsId() {
        return newsId;
    }

    public void setNewsId(Long newsId) {
        this.newsId = newsId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
