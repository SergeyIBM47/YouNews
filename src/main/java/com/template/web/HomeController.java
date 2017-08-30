package com.template.web;

import com.template.domain.model.User;
import com.template.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by: Sergey Volokh
 * Date: 5/8/2016
 * Time: 4:22 PM
 * Project: Spring MVC
 */
@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "forward: /index.html";
    }

    @RequestMapping(value = "/logins", method = RequestMethod.GET)
    public String login() {
        return "forward: /login.html";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerNewUser() {
        return "forward: /user_register_page.html";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerNewUser(String firstName, String lastName, String nickName, String email, String password) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setNickName(nickName);
        user.setEmail(email);
        user.setPassword(password);
        userService.registerNewUser(user);
        return "redirect: /";
    }
}
