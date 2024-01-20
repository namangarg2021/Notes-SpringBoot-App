package com.notesstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MiniExitProjectApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniExitProjectApIsApplication.class, args);
		System.out.println("Server running....");
	}

}
