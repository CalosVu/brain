package com.brain.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brain.management.exceptions.ErrorNotFoundException;
import com.brain.management.model.Dipendente;
import com.brain.management.model.ResponseDto;
import com.brain.management.services.ServiceDipendente;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/dipendente")
public class ControllerDipendente {

	@Autowired
	private ServiceDipendente serviceDipendente;

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto<Dipendente>> getDipendente(@PathVariable Long id) throws ErrorNotFoundException {

		Dipendente dipendente = serviceDipendente.getDipendente(id);
		ResponseDto<Dipendente> responseDto = null;
		
		if (dipendente != null) {
			log.info("Informazioni dipendente ricercato: {}", dipendente.toString());

			responseDto = new ResponseDto<Dipendente>(HttpStatus.OK, "Informazioni dipendente", null, dipendente);
		} else {
			log.info("Nessun dipendente trovato: {}");

			responseDto = new ResponseDto<Dipendente>(HttpStatus.OK, "Nessun dipendente trovato", "Not Found", null);
		}

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

	@GetMapping("/lista")
	public ResponseEntity<ResponseDto<Map<Integer, Dipendente>>> getListaDipendenti() {

		log.info("Recupero lista dipendenti");
		Map<Integer, Dipendente> listaDipendenti = serviceDipendente.getListaDipendenti();

		ResponseDto<Map<Integer, Dipendente>> responseDto = new ResponseDto<>(HttpStatus.OK,
				"Lista dei dipendenti recuperata con successo", null, listaDipendenti);

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

}