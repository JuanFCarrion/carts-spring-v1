package com.example.CartProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CartProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartProjectApplication.class, args);
	}

}
