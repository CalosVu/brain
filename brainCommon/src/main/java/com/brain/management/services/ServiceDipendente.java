package com.brain.management.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.brain.management.data.StrutturaDatiSingleton;
import com.brain.management.model.Dipendente;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceDipendente {

	private StrutturaDatiSingleton listaDipendenti = StrutturaDatiSingleton.getInstance();

	public Dipendente getDipendente(long id) {

		for (Map.Entry<Integer, Dipendente> entry : listaDipendenti.getDipendenti().entrySet()) {
			Integer idDipendente = entry.getKey();
			Dipendente dipendente = entry.getValue();

			if (id == idDipendente) {

				log.info("ID: {}, Nome: {}, Cognome: {}, Data di Nascita: {}, Data Assunzione: {}", idDipendente,
						dipendente.getNome(), dipendente.getCognome(), dipendente.getDataNascita(),
						dipendente.getDataAssunzione());
				
				return dipendente;
			}
		}

		return null;
	}

	public Map<Integer, Dipendente> getListaDipendenti() {

		log.info("ServiceDipendente per getListaDipendenti");

		for (Map.Entry<Integer, Dipendente> entry : listaDipendenti.getDipendenti().entrySet()) {
			Integer idDipendente = entry.getKey();
			Dipendente dipendente = entry.getValue();

			log.info("ID: {}, Nome: {}, Cognome: {}, Data di Nascita: {}, Data Assunzione: {}", idDipendente,
					dipendente.getNome(), dipendente.getCognome(), dipendente.getDataNascita(),
					dipendente.getDataAssunzione());
		}

		return listaDipendenti.getDipendenti();

	}

}
