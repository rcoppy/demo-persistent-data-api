package com.alexrupp.persistentdataapi.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexrupp.persistentdataapi.models.ChatUser;
import com.alexrupp.persistentdataapi.models.Message;
import com.alexrupp.persistentdataapi.repositories.ChatUserRepository;
import com.alexrupp.persistentdataapi.repositories.MessageRepository;

@RestController
public class MessageController {

    public static class Credentials {
        private String handle;
        private String key;

        public Credentials(String handle, String key) {
            this.handle = handle;
            this.key = key;
        }

        public String getHandle() {
            return handle;
        }

        public String getKey() {
            return key;
        }
    }

    public static class MessageData {

        private String sender;
        private String recipient;
        private String contents;
        private String date;

        public MessageData(String sender, String recipient, String contents, String date) {
            this.sender = sender;
            this.recipient = recipient;
            this.contents = contents;
            this.date = date;
        }

        public String getSender() {
            return sender;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getContents() {
            return contents;
        }

        public String getDate() {
            return date;
        }

    }

    public static class MessageStream {
        private List<MessageData> messages;

        public MessageStream(List<MessageData> messages) {
            this.messages = messages;
        }

        public List<MessageData> getMessages() {
            return messages;
        }
    }

    public static class MessageDispatch {
        private String key; 
        private String sender; 
        private String recipient; 
        private String contents; 

        public MessageDispatch(String key, String sender, String recipient, String contents) {
            this.key = key; 
            this.sender = sender; 
            this.recipient = recipient; 
            this.contents = contents; 
        }
            
        public String getKey() {
            return key; 
        }

        public String getSender() {
            return sender; 
        }

        public String getRecipient() {
            return recipient; 
        }

        public String getContents() {
            return contents; 
        }
    }

    private final MessageRepository messageRepository;

    @Autowired
    private ChatUserRepository userRepository;

    MessageController(MessageRepository repository) {
        this.messageRepository = repository;
    }

    @PostMapping(value = "/messages/send", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.ALL_VALUE })
    ResponseEntity<String> sendMessage(@RequestBody MessageDispatch dispatch) {
        ChatUser user = userRepository.findByHandle(dispatch.sender).get(0);

        if (!user.ValidatePassword(dispatch.key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ChatUser recipient = userRepository.findByHandle(dispatch.recipient).get(0); 

        messageRepository.save(new Message(user, recipient, dispatch.contents));

        return ResponseEntity.ok().body("Message sent successfully");
    }

    @PostMapping(value = "/messages/sent", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<MessageStream> allSent(@RequestBody Credentials credentials) {
        return getMessagesOfType(credentials, (ChatUser u) -> messageRepository.findBySender(u));
    }

    @PostMapping(value = "/messages/received", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    ResponseEntity<MessageStream> allReceived(@RequestBody Credentials credentials) {
        return getMessagesOfType(credentials, (ChatUser u) -> messageRepository.findByRecipient(u));
    }

    ResponseEntity<MessageStream> getMessagesOfType(Credentials credentials, Function<ChatUser, List<Message>> findBy) {
        ChatUser user = userRepository.findByHandle(credentials.handle).get(0);

        if (!user.ValidatePassword(credentials.key)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<Message> rawMessages = findBy.apply(user);

        List<MessageData> processedMessages = new ArrayList<MessageData>();

        for (Message m : rawMessages) {
            processedMessages.add(new MessageData(
                    m.GetSender().GetHandle(), m.GetRecipient().GetHandle(), m.GetContents(),
                    m.GetTimestamp().toString()));
        }

        return ResponseEntity.ok().body(new MessageStream(processedMessages));
    }

}
