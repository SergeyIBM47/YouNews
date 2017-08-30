package com.template.service.impl;

import com.template.domain.model.Message;
import com.template.domain.repository.MessageRepository;
import com.template.domain.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by: Sergey Volokh
 * Date: 6/2/2016
 * Time: 6:53 PM
 * Project: Diplom
 */
@Service
public class MessageServiceImpl extends RootServiceImpl<Message> {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public RootRepository<Message, Long> getRepository() {
        return messageRepository;
    }

    @Override
    public Class<Message> getEntityClass() {
        return Message.class;
    }
}
