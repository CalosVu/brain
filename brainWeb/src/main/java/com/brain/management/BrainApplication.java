package com.brain.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration
@Slf4j
public class BrainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BrainApplication.class, args);

		log.info("Applicazione avviata...");
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
