package com.alexrupp.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long message_id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="sender_id")
    private User sender; 

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="recipient_id")
    private User recipient; 

    private String contents; 
    private Date created_at;

    public Message(User sender, User recipient, String contents) {
        this.sender = sender; 
        this.recipient = recipient; 
        this.contents = contents; 
        this.created_at = new Date();
    }

    public User GetSender() {
        return sender; 
    }

    public User GetRecipient() {
        return recipient; 
    }

    public Long GetMessageId() {
        return message_id; 
    }

    public Date GetTimestamp() {
        return created_at; 
    }

    public String GetContents() {
        return contents; 
    }
}
