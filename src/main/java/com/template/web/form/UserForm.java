package com.template.web.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.domain.model.User;
import com.template.domain.model.enums.OnlineStatus;
import com.template.domain.model.enums.UserStatus;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by: Sergey Volokh
 * Date: 5/21/2016
 * Time: 3:23 PM
 * Project: Spring MVC
 */
public class UserForm extends BaseForm<User> {

    private String firstName;

    private String lastName;

    private String email;

    @JsonIgnore
    private String password;

    private String nickName;

    private String photoUrl;

    private Date birthDay;

    private String description;

    private String jobName;

    private UserStatus userStatus = UserStatus.INACTIVE;

    private OnlineStatus onlineStatus = OnlineStatus.OFFLINE;

    private Set<BlogForm> subscriptions = new HashSet<>();

    private List<BlogForm> ownedBlogs = new ArrayList<>();

    private List<NewsForm> ownedNews = new ArrayList<>();

    @Override
    public User getEntity() {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setBirthDay(birthDay);
        user.setNickName(nickName);
        user.setPhotoUrl(photoUrl);
        user.setJobName(jobName);
        user.setDescription(description);
        user.setUserStatus(userStatus);
        user.setOnlineStatus(onlineStatus);
        if (!subscriptions.isEmpty()) {
            user.setSubscription(subscriptions.stream().map(BlogForm::getEntity).collect(Collectors.toSet()));
        }
        if (!ownedNews.isEmpty()) {
            user.setOwnerNews(ownedNews.stream().map(NewsForm::getEntity).collect(Collectors.toList()));
        }
        if (!ownedBlogs.isEmpty()) {
            user.setOwnedBlogs(ownedBlogs.stream().map(BlogForm::getEntity).collect(Collectors.toList()));
        }

        return user;
    }

    @Override
    public UserForm getForm(User entity) {
        UserForm form = new UserForm();
        form.setId(entity.getId());
        form.setFirstName(entity.getFirstName());
        form.setLastName(entity.getLastName());
        form.setNickName(entity.getNickName());
        form.setPhotoUrl(entity.getPhotoUrl());
        form.setPassword(entity.getPassword());
        form.setBirthDay(entity.getBirthDay());
        form.setEmail(entity.getEmail());
        form.setDescription(entity.getDescription());
        form.setJobName(entity.getJobName());
        form.setUserStatus(entity.getUserStatus());
        form.setOnlineStatus(entity.getOnlineStatus());
        if (entity.getSubscription() != null){
            form.setSubscriptions(entity.getSubscription().stream().map(BlogForm::new).collect(Collectors.toSet()));
        }
        if (entity.getOwnerNews() != null){
            form.setOwnedNews(entity.getOwnerNews().stream().map(NewsForm::new).collect(Collectors.toList()));
        }
        if (entity.getOwnedBlogs() != null){
            form.setOwnedBlogs(entity.getOwnedBlogs().stream().map(BlogForm::new).collect(Collectors.toList()));
        }
        return form;
    }

    public UserForm() {
    }

    public UserForm(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setOwnedBlogs(List<BlogForm> ownedBlogs) {
        this.ownedBlogs = ownedBlogs;
    }

    public List<NewsForm> getOwnedNews() {
        return ownedNews;
    }

    public void setOwnedNews(List<NewsForm> ownedNews) {
        this.ownedNews = ownedNews;
    }

    public List<BlogForm> getOwnedBlogs() {
        return ownedBlogs;
    }

    public Set<BlogForm> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<BlogForm> subscriptions) {
        this.subscriptions = subscriptions;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthDay=" + birthDay +
                ", description='" + description + '\'' +
                ", userStatus=" + userStatus +
                ", onlineStatus=" + onlineStatus +
                '}';
    }
}
