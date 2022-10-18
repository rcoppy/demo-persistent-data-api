package com.alexrupp.persistentdataapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexrupp.persistentdataapi.models.ChatUser;
import com.alexrupp.persistentdataapi.repositories.ChatUserRepository;

@Service
public class ChatUserService {

    @Autowired
    private ChatUserRepository userRepository;

    public List<ChatUser> list() {
        return userRepository.findAll();
    }
}