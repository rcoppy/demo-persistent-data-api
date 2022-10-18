package com.alexrupp.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    private String handle;
    private String bcrypt_hash; 

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages; 

    @OneToMany(mappedBy = "recipient")
    private List<Message> receivedMessages; 

    public User(String username, String password) {
        this.handle = username;
        this.bcrypt_hash = BCrypt.hashpw(password, username); 
    }

    public boolean ValidatePassword(String password) {
        return BCrypt.hashpw(password, this.handle).equals(this.bcrypt_hash);
    }

    public String GetHandle() {
        return this.handle; 
    }

    public Long GetUserId() {
        return this.user_id;
    }
}
