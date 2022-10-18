package com.alexrupp.persistentdataapi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexrupp.persistentdataapi.models.ChatUser;
import com.alexrupp.persistentdataapi.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySender(ChatUser sender);
    List<Message> findByRecipient(ChatUser recipient);
}
