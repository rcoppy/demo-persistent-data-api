package com.alexrupp.persistentdataapi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alexrupp.persistentdataapi.models.ChatUser;
import com.alexrupp.persistentdataapi.repositories.ChatUserRepository;

@RestController
class ChatUserController {

  private final ChatUserRepository repository;

  ChatUserController(ChatUserRepository repository) {
    this.repository = repository;
  }


  // Aggregate root
  // tag::get-aggregate-root[]
  @GetMapping("/users")
  List<String> all() {
    List<String> l = new ArrayList<String>(); 

    for (ChatUser u : repository.findAll()) {
        l.add(u.GetHandle()); 
    }
    
    return l; 
  }
  // end::get-aggregate-root[]

//   @PostMapping("/employees")
//   Employee newEmployee(@RequestBody Employee newEmployee) {
//     return repository.save(newEmployee);
//   }

  // Single item
  
//   @GetMapping("/users/{handle}")
//   ChatUser one(@PathVariable String handle) {
//     return repository.findByHandle(handle).get(0);
//   }
}