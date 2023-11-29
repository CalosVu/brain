package com.brain.management.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.brain.management.model.Dipendente;

import lombok.Data;

@Data
public class StrutturaDatiSingleton {

	private final Map<Integer, Dipendente> dipendenti = new HashMap<>();

	private static final StrutturaDatiSingleton instance = new StrutturaDatiSingleton();

	private StrutturaDatiSingleton() {

		for (int i = 1; i <= 5; i++) {

			Dipendente dipendente = new Dipendente("Nome_" + i, "Cognome_" + i, null, null);
			dipendente.setDataNascita(generateRandomDate(1980, 2000));
			dipendente.setDataAssunzione(generateRandomDate(1980, 2023));
			dipendenti.put(i, dipendente);
		}
	}

	public static StrutturaDatiSingleton getInstance() {
		return instance;
	}

	private LocalDate generateRandomDate(int startDate, int endDate) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dataRandom = String.format("%02d/%02d/%04d", getRandomNumberInRange(1, 31),
				getRandomNumberInRange(1, 12), getRandomNumberInRange(startDate, endDate));

		return LocalDate.parse(dataRandom, formatter);

	}

	private int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

}
