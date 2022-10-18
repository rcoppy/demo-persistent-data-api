package com.alexrupp.persistentdataapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PersistentDataApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PersistentDataApiApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@PostMapping("/authenticate")
	public byte[] authenticate(
			@RequestParam(value = "username") String username,
			@RequestParam(value = "password") String password) {
		return new byte[] { 0x00, 0x00 };
	}
}
