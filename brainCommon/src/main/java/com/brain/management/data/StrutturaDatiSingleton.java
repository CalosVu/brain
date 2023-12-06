package com.brain.management.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.brain.management.exceptions.ErrorDeleteException;
import com.brain.management.model.Dipendente;
import com.github.javafaker.Faker;

import lombok.Data;

@Data
public class StrutturaDatiSingleton {

	private final Map<Long, Dipendente> dipendenti = new HashMap<>();
	private long contatoreId = 0;

	private static final StrutturaDatiSingleton instance = new StrutturaDatiSingleton();

	private StrutturaDatiSingleton() {
		final Faker fakeData = new Faker();

		for (int i = 1; i <= 5; i++) {

			contatoreId++;
			Dipendente dipendente = new Dipendente(contatoreId, fakeData.name().firstName(), fakeData.name().lastName(),
					generateRandomDate(1980, 2000), generateRandomDate(1998, 2023));
			dipendenti.put(contatoreId, dipendente);
		}
	}

	public static StrutturaDatiSingleton getInstance() {
		return instance;
	}

	public LocalDate generateRandomDate(int startDate, int endDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataRandom = String.format("%02d/%02d/%04d", getRandomNumberInRange(1, 31),
				getRandomNumberInRange(1, 12), getRandomNumberInRange(startDate, endDate));

		return LocalDate.parse(dataRandom, formatter);

	}

	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public Long aggiungiDipendente(Dipendente dipendente) {
		dipendente.setId(++contatoreId);
		dipendenti.put(contatoreId, dipendente);
		return contatoreId;
	}

	public void eliminaDipendente(Long id) throws ErrorDeleteException {

		try {
			dipendenti.remove(id);
		} catch (Exception e) {
			throw new ErrorDeleteException("Nessun è stato possibile cancellare il dipendente con id: [" + id
					+ "]. Errore: " + e.getMessage());
		}
	}

}
