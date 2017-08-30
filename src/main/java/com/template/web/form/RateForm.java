package com.template.web.form;

import com.template.domain.model.Rate;

/**
 * Created by: Sergey Volokh
 * Date: 6/6/2016
 * Time: 9:53 PM
 * Project: springmvcs
 */
public class RateForm extends BaseForm<Rate> {

    private Integer like;

    private Integer dislike;

    private Integer star;

    private Integer rating;

    private Integer shared;

    private UserForm user;

    @Override
    public Rate getEntity() {
        Rate rate = new Rate();
        rate.setId(id);
        rate.setLike(like);
        rate.setDislike(dislike);
        rate.setStar(star);
        rate.setRating(rating);
        rate.setShared(shared);
//        rate.setUser(user.getEntity());

        return rate;
    }

    @Override
    public RateForm getForm(Rate entity) {
        RateForm form = new RateForm();
        form.setId(entity.getId());
        form.setLike(entity.getLike());
        form.setDislike(entity.getDislike());
        form.setStar(entity.getStar());
        form.setRating(entity.getRating());
        form.setShared(entity.getShared());
        if(entity.getUser() != null)
            form.setUser(new UserForm().getForm(entity.getUser()));

        return form;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getShared() {
        return shared;
    }

    public void setShared(Integer shared) {
        this.shared = shared;
    }

    public UserForm getUser() {
        return user;
    }

    public void setUser(UserForm user) {
        this.user = user;
    }
}
