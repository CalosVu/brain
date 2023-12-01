package com.brain.management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Configuration
@Slf4j
public class BrainApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		SpringApplication.run(BrainApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ConfigurableApplicationContext applicationContext) {
		return args -> {
			log.info("Applicazione avviata...");

			while (true) {
				System.out.println("----------> Seleziona un'opzione:");
				System.out.println("----------> 1. GetListaDipendenti");
				System.out.println("----------> 2. GetDipendente");
				System.out.println("----------> 3. AggiungiDipendente");
				System.out.println("----------> 4. EliminaDipendente");
				System.out.println("----------> 5. ModificaDipendente");
				System.out.println("----------> 6. Exit");

				int scelta = UtilityMain.getScelta();

				switch (scelta) {
				case 1:
					UtilityMain.getListaDipendenti();
					break;
				case 2:
					UtilityMain.getDipendente();
					break;
				case 3:
					UtilityMain.aggiungiDipendente();
					break;
				case 4:
					UtilityMain.eliminaDipendente();
					break;
				case 5:
					UtilityMain.modificaDipendente();
					break;
				case 6:
					System.exit(SpringApplication.exit(applicationContext));
					break;
				default:
					System.out.println("----------> Opzione non valida. Riprova.");
				}
			}
		};
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
