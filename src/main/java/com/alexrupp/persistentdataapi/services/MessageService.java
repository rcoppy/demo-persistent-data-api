package com.alexrupp.persistentdataapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexrupp.persistentdataapi.models.Message;
import com.alexrupp.persistentdataapi.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> list() {
        return messageRepository.findAll();
    }
}