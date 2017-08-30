package com.template.web.rest;

import com.template.domain.model.User;

import com.template.service.UserService;
import com.template.web.form.UserForm;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

/**
 * Created by: Sergey Volokh
 * Date: 5/20/2016
 * Time: 2:18 PM
 * Project: Spring MVC
 */
@RestController
@RequestMapping(value = "/rest/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestUserController {

    private final UserService userService;

    @Autowired
    public RestUserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<UserForm>> getCurrentUserProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return supplyAsync(() -> {
            User user = userService.findByEmail(email);
            UserForm form = new UserForm().getForm(user);
//        List<Project> projects = projectService.findAllByMembersId(user.getId());
//        form.setTeamMemberProjects(projects.stream().map((p) -> new ProjectForm().getForm(p)).collect(Collectors.toList()));

            return new ResponseEntity<>(form, HttpStatus.OK);

        });
    }

    @RequestMapping(value = "/all-people", method = RequestMethod.GET)
    public CompletableFuture<ResponseEntity<List<UserForm>>> getAllUserProfile() {
        return supplyAsync(() -> {
            List<User> users = userService.findAll(new Sort(Sort.Direction.ASC, "nickName"));
            List<UserForm> forms = users.stream().map((u) -> {
                UserForm form = new UserForm();
                form.setId(u.getId());
                form.setFirstName(u.getFirstName());
                form.setLastName(u.getLastName());
                form.setNickName(u.getNickName());
                form.setPhotoUrl(u.getPhotoUrl());
                form.setDescription(u.getDescription());
                form.setJobName(u.getJobName());
                return form;
            }).collect(Collectors.toList());

            return new ResponseEntity<>(forms, HttpStatus.OK);

        });
    }

    @RequestMapping(method = RequestMethod.PUT)
    public CompletableFuture<ResponseEntity<UserForm>> updateUserAccount(@RequestBody @Valid UserForm form) {
        return supplyAsync(() -> {
            User user = userService.get(form.getId());
            form.setPassword(user.getPassword());
            user = form.getEntity();
            userService.update(user);
            return new ResponseEntity<>(form, HttpStatus.OK);
        });
    }

    @RequestMapping(method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<User>> addUserAccount(@RequestBody @Valid UserForm user) {
        return supplyAsync(() -> new ResponseEntity<>(userService.insert(user.getEntity()), HttpStatus.OK));
    }

    @RequestMapping(value = "/add/project/{id}", method = RequestMethod.POST)
    public CompletableFuture<ResponseEntity<?>> addUserProject(@PathVariable("id") Long id) {
//        Project project = projectService.get(id);
//        if(!user.getOwnedBlogs().contains(project)){
////            project.getMembers().add(user);
//            projectService.update(project);
//        }

        return supplyAsync(() -> new ResponseEntity<>(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()), HttpStatus.OK));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<UserForm>> deleteUserAccount(@RequestBody @Valid UserForm user) {
        return supplyAsync(() -> {
            userService.delete(user.getEntity().getId());
            return new ResponseEntity<>(user, HttpStatus.OK);
        });
    }

}
