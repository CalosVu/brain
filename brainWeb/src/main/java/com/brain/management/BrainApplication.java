package com.brain.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.brain.management.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration
@Slf4j
public class BrainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(BrainApplication.class, args);

		log.info("Applicazione avviata...");		
				
		Long dipendenteId = 1L;  
        String url = Constants.getUrlDipendente + dipendenteId;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
