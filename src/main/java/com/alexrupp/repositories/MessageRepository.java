package com.alexrupp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alexrupp.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
}
