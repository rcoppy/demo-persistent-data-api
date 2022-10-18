package com.alexrupp.persistentdataapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexrupp.persistentdataapi.models.ChatUser;

@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {
    List<ChatUser> findByHandle(String handle);
    List<ChatUser> findByChatUserId(String user_id);
}
