package com.alexrupp.persistentdataapi.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class ChatUser { // 'User' is a reserved keyword by DBMS

    @Id
    @GeneratedValue
    private Long chatUserId;

    private String handle;
    private String bcryptHash; 
    private String bcryptSalt; 

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages; 

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages; 

    public ChatUser() {

    }

    public ChatUser(String username, String password) {
        this.handle = username;
        this.bcryptSalt = BCrypt.gensalt(); 
        this.bcryptHash = BCrypt.hashpw(password, this.bcryptSalt); 
    }

    public boolean ValidatePassword(String password) {
        return BCrypt.checkpw(password, bcryptHash);
    }

    public String GetHandle() {
        return this.handle; 
    }

    public Long GetChatUserId() {
        return this.chatUserId;
    }
}
