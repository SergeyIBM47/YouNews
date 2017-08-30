package com.template.service;

import com.template.domain.model.Comments;
import com.template.domain.model.News;
import com.template.domain.model.User;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:02 PM
 * Project: Spring MVC
 */
public interface CommentService extends RootService<Comments> {

    Comments addComment(News news, User user, Comments comment);

}
