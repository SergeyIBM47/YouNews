package com.template.domain.repository;

import com.template.domain.model.User;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:00 PM
 * Project: Spring MVC
 */
public interface UserRepository extends RootRepository<User, Long> {

    User findByEmail(String email);
}
