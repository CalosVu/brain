package com.brain.management.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brain.management.exceptions.DuplicateException;
import com.brain.management.exceptions.ErrorDeleteException;
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
		ResponseDto<Dipendente> responseDto = new ResponseDto<Dipendente>(HttpStatus.OK, "Informazioni dipendente",
				null, dipendente);

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

	@GetMapping("/lista")
	public ResponseEntity<ResponseDto<Map<Long, Dipendente>>> getListaDipendenti() {

		log.info("Recupero lista dipendenti");
		Map<Long, Dipendente> listaDipendenti = serviceDipendente.getListaDipendenti();

		ResponseDto<Map<Long, Dipendente>> responseDto = new ResponseDto<>(HttpStatus.OK,
				"Lista dei dipendenti recuperata con successo", null, listaDipendenti);

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

	@PostMapping("/aggiungidipendente")
	public ResponseEntity<ResponseDto<Dipendente>> aggiungiDipendente(@RequestBody Dipendente nuovoDipendente)
			throws DuplicateException {

		Long idDipendente = serviceDipendente.addDipendente(nuovoDipendente);

		log.info("Nuovo dipendente aggiunto con successo: {}", nuovoDipendente.toString());

		ResponseDto<Dipendente> responseDto = new ResponseDto<Dipendente>(HttpStatus.CREATED,
				"Dipendente aggiunto con successo. id: " + idDipendente, null, nuovoDipendente);

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());

	}

	@GetMapping("/delete/{id}")
	public ResponseEntity<ResponseDto<Dipendente>> getDeleteDipendente(@PathVariable Long id)
			throws ErrorNotFoundException, ErrorDeleteException {

		Dipendente dipendente = serviceDipendente.getDeleteDipendenteById(id);
		ResponseDto<Dipendente> responseDto = new ResponseDto<Dipendente>(HttpStatus.OK, "Dipendente eliminato", null,
				dipendente);

		return new ResponseEntity<>(responseDto, responseDto.getHttpStatus());
	}

	@PutMapping("/modifica/{id}")
	public ResponseEntity<ResponseDto<Dipendente>> modificaDipendente(@PathVariable Long id,
			@RequestBody Dipendente datiModificati) throws ErrorNotFoundException {

		Dipendente dipendenteModificato = serviceDipendente.modificaDipendente(id, datiModificati);

		ResponseDto<Dipendente> responseDto = new ResponseDto<>(HttpStatus.OK,
				"Dati del dipendente modificati con successo", null, dipendenteModificato);
		return new ResponseEntity<>(responseDto, HttpStatus.OK);

	}

}