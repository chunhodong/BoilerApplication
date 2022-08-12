package com.bronze.boiler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BoilerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoilerApplication.class, args);
	}

}
