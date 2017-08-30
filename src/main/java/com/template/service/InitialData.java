package com.template.service;

import com.template.domain.model.*;
import com.template.domain.model.enums.CategoryType;
import com.template.domain.repository.*;
import com.template.security.Crypto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by: Sergey Volokh
 * Date: 5/19/2016
 * Time: 3:10 PM
 * Project: Spring MVC
 */
@Component
public class InitialData {

    private final NewsRepository newsRepository;

    private final CommentRepository commentRepository;

    private final BlogRepository blogRepository;

    private final TagRepository tagRepository;

    private final UserRepository userRepository;

    private final Crypto crypto;

    @Autowired
    public InitialData(NewsRepository newsRepository, CommentRepository commentRepository, BlogRepository blogRepository, TagRepository tagRepository, UserRepository userRepository, Crypto crypto) {
        this.newsRepository = newsRepository;
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
        this.crypto = crypto;
    }

    @PostConstruct
    public void initData() {
        insertUserData();
//        insertProjectData();
        insertBlogData();

    }

    private void insertUserData() {
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();

//        user2.setSkills(Arrays.asList(skill1, skill2));
//        user3.setSkills(Arrays.asList(skill1, skill2, skill3));

        user1.setId(1L);
        user1.setFirstName("Serg");
        user1.setLastName("Lowren");
        user1.setEmail("serg@icloud.com");
        user1.setNickName("sergio");
        user1.setPassword(crypto.encodePassword("123"));
        user1.setPhotoUrl("http://cs624226.vk.me/v624226715/20670/0CK7aVQ8TI0.jpg");
        user1.setDescription("Description 1");
        user1.setJobName("Senior Java EE Developer");

        user2.setId(2L);
        user2.setFirstName("Nick");
        user2.setLastName("Gaits");
        user2.setEmail("nick@icloud.com");
        user2.setNickName("nickolia");
        user2.setPassword(crypto.encodePassword("123"));
        user2.setPhotoUrl("https://s-media-cache-ak0.pinimg.com/736x/8b/69/e4/8b69e4ac3d77c93f64e01622b73b8e51.jpg");
        user2.setDescription("Description 2");
        user2.setJobName("Model");

        user3.setId(3L);
        user3.setFirstName("Brian");
        user3.setLastName("Damian");
        user3.setEmail("brian@icloud.com");
        user3.setNickName("brian");
        user3.setPassword(crypto.encodePassword("123"));
        user3.setPhotoUrl("http://pre06.deviantart.net/368b/th/pre/f/2014/113/b/4/model_by_adysaputra13-d7fqym5.jpg");
        user3.setDescription("Description 3");
        user3.setJobName("Model");

        userRepository.save(Arrays.asList(user1, user2, user3));
    }

//    private void insertProjectData() {
//        Project project1 = new Project();
//        Project project2 = new Project();
//        Project project3 = new Project();
//
//        Blog category1 = new Blog();
//        Blog category2 = new Blog();
//        Blog category3 = new Blog();
//
//        category1.setName("Start Up");
//        category2.setName("Commerce");
//        category3.setName("Open Source");
//
//        category1.setType(CategoryType.PROJECT);
//        category2.setType(CategoryType.PROJECT);
//        category3.setType(CategoryType.PROJECT);
//
//        category1.setDescription("Blog description 1");
//        category2.setDescription("Blog description 2");
//        category3.setDescription("Blog description 2");
//
//        category1.setPhotoUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQfXMhQIBcTWk0nVgeqG0MOhtxnOUcdTDYa7rUYLz2XZABLSio8");
//        category2.setPhotoUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTzrcY6FJnlyptm2hCcG_AoczZo8NAQwUWe13gP1gFnd9g4we2t");
//        category3.setPhotoUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTzrcY6FJnlyptm2hCcG_AoczZo8NAQwUWe13gP1gFnd9g4we2t");
//
//        project1.setName("Project Name 1");
//        project2.setName("Project Name 2");
//        project3.setName("Project Name 2");
//
//        project1.setPhotoUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQfXMhQIBcTWk0nVgeqG0MOhtxnOUcdTDYa7rUYLz2XZABLSio8");
//        project2.setPhotoUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTzrcY6FJnlyptm2hCcG_AoczZo8NAQwUWe13gP1gFnd9g4we2t");
//        project3.setPhotoUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTzrcY6FJnlyptm2hCcG_AoczZo8NAQwUWe13gP1gFnd9g4we2t");
//
//        project1.setWebSiteUrl("http://www.my.com");
//        project2.setWebSiteUrl("http://www.you.com");
//        project3.setWebSiteUrl("http://www.you.com");
//
//        project1.setDescription("description 1!!!!!!");
//        project2.setDescription("description 2!!!!!!");
//        project3.setDescription("description 2!!!!!!");
//
//        project1.setBlog(category1);
//        project2.setBlog(category2);
//        project3.setBlog(category3);
//////
////        project1.setOwners(Arrays.asList(new User(1L)));
////        project2.setOwners(Arrays.asList(new User(2L)));
//
//        project1.setMembers(Arrays.asList(new User(2L), new User(3L)));
//        project2.setMembers(Arrays.asList(new User(1L), new User(3L)));
//
//        project1.setNecessarySkills(Arrays.asList(new Skill(1L), new Skill(2L)));
//        project2.setNecessarySkills(Arrays.asList(new Skill(1L), new Skill(2L)));
//
//        categoryRepository.save(Arrays.asList(category1, category2, category3));
//        projectRepository.save(Arrays.asList(project1, project2, project3));
//    }

    private void insertBlogData() {
        News news1 = new News();
        News news2 = new News();
        News news3 = new News();
        News news4 = new News();

        Blog blogBlog1 = new Blog();
        Blog blogBlog2 = new Blog();
        Blog blogBlog3 = new Blog();
        Blog blogBlog4 = new Blog();

        Tags tag1 = new Tags();
        Tags tag2 = new Tags();
        Tags tag3 = new Tags();
        Tags tag4 = new Tags();

        tag1.setName("tag 1");
        tag2.setName("tag 2");
        tag3.setName("tag 3");
        tag4.setName("tag 4");

        List<Tags> tagsList = Arrays.asList(tag1, tag2, tag3, tag4);

        blogBlog1.setType(CategoryType.BLOG);
        blogBlog2.setType(CategoryType.BLOG);
        blogBlog3.setType(CategoryType.BLOG);
        blogBlog4.setType(CategoryType.BLOG);

        blogBlog1.setName("News");
        blogBlog2.setName("Latest Trends");
        blogBlog3.setName("Must Read");
        blogBlog4.setName("Premium");

        blogBlog1.setDescription("New Description");
        blogBlog2.setDescription("Last Description");
        blogBlog3.setDescription("Popular Description");
        blogBlog4.setDescription("Premium Description");

        blogBlog1.setPhotoUrl("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcTsTPdmDEOgAb-VsieuBBk1nxHNm2kEmgOLdrrGgEwJSEKgmXbZ");
        blogBlog2.setPhotoUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSbx8MGstIbiavnObeDId6Hb5Azj0kxvW_VnN-z0KtMyTyp0yI9");
        blogBlog3.setPhotoUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcQ8RKmKrTANUdbuHKjp9t4ysUZcakrrFojoio20Dxf694W7M2hi");
        blogBlog4.setPhotoUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSbx8MGstIbiavnObeDId6Hb5Azj0kxvW_VnN-z0KtMyTyp0yI9");

        news1.setBlog(blogBlog1);
        news2.setBlog(blogBlog2);
        news3.setBlog(blogBlog3);
        news4.setBlog(blogBlog4);

        Comments comment1 = new Comments();
        Comments comment2 = new Comments();
        comment1.setComment("comment 1");
        comment2.setComment("comment 2");
        comment1.setUser(new User(1L));
        comment2.setUser(new User(2L));
        comment1.setDate(new Date(System.currentTimeMillis()));
        comment2.setDate(new Date(System.currentTimeMillis() - 64_000L));

        news1.setComments(Arrays.asList(comment1, comment2));

        news1.setTopic("topic1");
        news1.setDescription("Description 1");

        news1.setTags(Arrays.asList(tagsList.get(1), tagsList.get(2)));

        news2.setTopic("topic2");
        news2.setDescription("Description 2");

        news2.setTags(Arrays.asList(tagsList.get(0), tagsList.get(3)));

        news3.setTopic("topic3");
        news3.setDescription("Description 3");

        news4.setTopic("topic4");
        news4.setDescription("Description 4");

        news1.setContent("Content!!!!");
        news2.setContent("Content!!!!");
        news3.setContent("Content!!!!");
        news4.setContent("Content!!!!");

        tagRepository.save(tagsList);

        commentRepository.save(comment1);
        commentRepository.save(comment2);

        blogRepository.save(blogBlog1);
        blogRepository.save(blogBlog2);
        blogRepository.save(blogBlog3);
        blogRepository.save(blogBlog4);

        newsRepository.save(news1);
        newsRepository.save(news2);
        newsRepository.save(news3);
        newsRepository.save(news4);
    }

}
