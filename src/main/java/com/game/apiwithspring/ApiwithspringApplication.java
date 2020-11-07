package com.game.apiwithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ApiwithspringApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiwithspringApplication.class, args);
	}

}
