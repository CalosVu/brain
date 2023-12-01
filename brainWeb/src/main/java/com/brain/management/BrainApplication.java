package com.brain.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.brain.management.model.Dipendente;
import com.brain.management.model.ResponseDto;
import com.brain.management.utils.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration
@Slf4j
public class BrainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		SpringApplication.run(BrainApplication.class, args);

		log.info("Applicazione avviata...");

		Long dipendenteId = 1L;
		String url = Constants.getUrlDipendente + dipendenteId;

		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<ResponseDto<Dipendente>> responseEntity =
		        restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<ResponseDto<Dipendente>>() {});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
		    ResponseDto<Dipendente> responseDto = responseEntity.getBody();
		    Dipendente dipendente = responseDto.getResponse();

		    System.out.println(dipendente);
		} else {
		    System.err.println("Errore nella chiamata API: " + responseEntity.getStatusCode());
		}
		
		

//
//			// for (Dipendente dipendente : dipendenti) {
//			System.out.println(dipendente);
//			// }


	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
