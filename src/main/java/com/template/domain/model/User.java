package com.template.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.template.domain.IdModel;
import com.template.domain.model.enums.OnlineStatus;
import com.template.domain.model.enums.UserStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * Created by: Sergey Volokh
 * Date: 5/13/2016
 * Time: 7:45 PM
 * Project: Spring MVC
 */
@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User implements IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @NotNull
    @NotEmpty(message = "First name must be not empty!")
    @Size(min = 1, max = 64)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @NotEmpty(message = "Last name must be not empty!")
    @Size(min = 1, max = 64)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotNull
    @NotEmpty(message = "E-mail name must be not empty!")
    @Size(min = 1, max = 64)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Size(min = 1, max = 64)
    @Column(name = "password")
    private String password;

    @NotNull
    @NotEmpty(message = "Nick name must be not empty!")
    @Size(min = 1, max = 64)
    @Column(name = "nick_name", nullable = false, unique = true)
    private String nickName;

    @Column(name = "user_photo_url")
    private String photoUrl;

    @Column(name = "user_job_name")
    private String jobName;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @CreationTimestamp
    @Column(name = "created_at", unique = false)
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Size(max = 512)
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus = UserStatus.INACTIVE;

    @Enumerated(EnumType.STRING)
    private OnlineStatus onlineStatus = OnlineStatus.OFFLINE;

    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "user_subscription",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id")
    )
    private Set<Blog> subscription = new HashSet<>();


    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(name = "project_owners",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<News> ownerNews = new ArrayList<>();

    @Fetch(FetchMode.JOIN)
    @OneToMany
    @JoinTable(name = "blog_owner",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "blog_id")
    )
    private List<Blog> ownedBlogs = new ArrayList<>();

    public User(){}

    public User(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public OnlineStatus getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(OnlineStatus onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Set<Blog> getSubscription() {
        return subscription;
    }

    public void setSubscription(Set<Blog> subscription) {
        this.subscription = subscription;
    }

    public List<News> getOwnerNews() {
        return ownerNews;
    }

    public void setOwnerNews(List<News> ownerNews) {
        this.ownerNews = ownerNews;
    }

    public List<Blog> getOwnedBlogs() {
        return ownedBlogs;
    }

    public void setOwnedBlogs(List<Blog> ownedBlogs) {
        this.ownedBlogs = ownedBlogs;
    }
}
