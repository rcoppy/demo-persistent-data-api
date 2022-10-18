package com.alexrupp.persistentdataapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alexrupp.persistentdataapi.models.ChatUser;
import com.alexrupp.persistentdataapi.repositories.ChatUserRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ChatUserRepository repository) {

        return args -> {
            if (repository.findAll().isEmpty()) {
                log.info("Preloading " + repository.save(new ChatUser("alex", "pancakes")));
                log.info("Preloading " + repository.save(new ChatUser("leo", "omelettes")));
                log.info("Preloading " + repository.save(new ChatUser("erika", "waffles")));
            }
        };
    }
}