package com.example.baseballtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BaseballtalkApplication {
	public static void main(String[] args) {
		SpringApplication.run(BaseballtalkApplication.class, args);
	}
}