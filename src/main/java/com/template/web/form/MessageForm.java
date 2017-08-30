package com.template.web.form;

import com.template.domain.model.Message;

import java.util.Date;

/**
 * Created by: Sergey Volokh
 * Date: 6/8/2016
 * Time: 7:04 PM
 * Project: springmvcs
 */
public class MessageForm  extends BaseForm<Message>{

    private String message;

    private Date createdAt;


    public MessageForm() {
    }

    public MessageForm (Message entity) {
        this.id = entity.getId();
        this.message = entity.getMessage();
        this.createdAt = entity.getCreatedAt();
    }

    @Override
    public Message getEntity() {
        Message entity = new Message();
        entity.setId(id);
        entity.setMessage(message);
        entity.setCreatedAt(createdAt);

        return entity;
    }

    @Override
    public MessageForm getForm(Message entity) {
        MessageForm form = new MessageForm();
        form.setId(entity.getId());
        form.setMessage(entity.getMessage());
        form.setCreatedAt(entity.getCreatedAt());
        return form;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
