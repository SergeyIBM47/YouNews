package com.template.service;

import com.template.domain.model.News;
import com.template.domain.model.User;

import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/18/2016
 * Time: 5:02 PM
 * Project: Spring MVC
 */
public interface UserService extends RootService<User> {

    User registerNewUser(User user);

    User addSubscription(String email, String blogName);

    User removeSubscription(String email, String blogName);

    User findByEmail(String email);

    List<News> getNewsBySubscription(String email);

}
