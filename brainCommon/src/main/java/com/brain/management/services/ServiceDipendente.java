package com.brain.management.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.brain.management.data.StrutturaDatiSingleton;
import com.brain.management.exceptions.DuplicateException;
import com.brain.management.exceptions.ErrorDeleteException;
import com.brain.management.exceptions.ErrorNotFoundException;
import com.brain.management.model.Dipendente;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceDipendente {

	private StrutturaDatiSingleton listaDipendenti = StrutturaDatiSingleton.getInstance();

	public Dipendente getDipendente(long id) throws ErrorNotFoundException {

		Dipendente dipendente = listaDipendenti.getDipendenti().get(id);

		if (dipendente != null) {
			log.info("Informazioni dipendente ricercato:");
			log.info("ID: {}, Nome: {}, Cognome: {}, Data di Nascita: {}, Data Assunzione: {}", dipendente.getId(),
					dipendente.getNome(), dipendente.getCognome(), dipendente.getDataNascita(),
					dipendente.getDataAssunzione());

			return dipendente;
		} else {
			throw new ErrorNotFoundException("Nessun dipendente trovato con id: " + id);
		}

	}

	public Map<Long, Dipendente> getListaDipendenti() {

		log.info("ServiceDipendente per getListaDipendenti");

		for (Map.Entry<Long, Dipendente> entry : listaDipendenti.getDipendenti().entrySet()) {
			Dipendente dipendente = entry.getValue();

			log.info("ID: {}, Nome: {}, Cognome: {}, Data di Nascita: {}, Data Assunzione: {}", dipendente.getId(),
					dipendente.getNome(), dipendente.getCognome(), dipendente.getDataNascita(),
					dipendente.getDataAssunzione());
		}

		return listaDipendenti.getDipendenti();

	}

	public Long addDipendente(Dipendente nuovoDipendente) throws DuplicateException {

		if (listaDipendenti.getDipendenti().containsValue(nuovoDipendente)) {
			throw new DuplicateException("Dipendente già presente nella struttura dati");
		} else {
			return listaDipendenti.aggiungiDipendente(nuovoDipendente);
		}

	}

	public Dipendente getDeleteDipendenteById(Long id) throws ErrorNotFoundException, ErrorDeleteException {

		Dipendente dipendenteDaEliminare = listaDipendenti.getDipendenti().get(id);

		if (dipendenteDaEliminare != null) {
			listaDipendenti.eliminaDipendente(id);
			log.info("Dipendente eliminato con successo!");

			return dipendenteDaEliminare;
		} else {
			throw new ErrorNotFoundException("Nessun dipendente trovato con id: " + id);
		}

	}

	public Dipendente modificaDipendente(Long id, Dipendente datiModificati) throws ErrorNotFoundException {

		Dipendente dipendenteDaModificare = listaDipendenti.getDipendenti().get(id);

		if (dipendenteDaModificare != null) {

			dipendenteDaModificare.setNome(datiModificati.getNome());
			dipendenteDaModificare.setCognome(datiModificati.getCognome());
			dipendenteDaModificare.setDataNascita(datiModificati.getDataNascita());
			dipendenteDaModificare.setDataAssunzione(datiModificati.getDataAssunzione());

			log.info("Dati del dipendente modificati con successo. Nuovi dati: {}", dipendenteDaModificare.toString());
			return dipendenteDaModificare;
		} else {
			throw new ErrorNotFoundException("Nessun dipendente trovato con id: " + id);
		}

	}

}
