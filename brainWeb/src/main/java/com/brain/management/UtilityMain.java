package com.brain.management;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.brain.management.model.Dipendente;
import com.brain.management.model.Request;
import com.brain.management.model.ResponseDto;
import com.brain.management.utils.Constants;

public class UtilityMain {

	private static RestTemplate restTemplate = new RestTemplate();
	private static Scanner scanner = new Scanner(System.in);

	public static int getScelta() {
		while (true) {
			try {
				return scanner.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("----------> Input non valido. Inserisci un numero.");
				scanner.nextLine();
			}
		}
	}

	public static void getDipendente() {
		try {
			System.out.println("----------> Inserisci l'ID del dipendente:");
			long dipendenteId = scanner.nextInt();
			System.out.println("----------> Recupero del dipendente con ID " + dipendenteId + "...");

			String url = Constants.getUrlDipendente + dipendenteId;

			ResponseEntity<ResponseDto<Dipendente>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<ResponseDto<Dipendente>>() {
					});

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				Dipendente dipendente = responseEntity.getBody().getResponse();
				System.out.println(dipendente);
			} else {
				System.err.println("----------> Errore nella chiamata API: " + responseEntity.getBody().getErrore());
			}

		} catch (InputMismatchException e) {
			System.out.println("----------> Input non valido. L'ID deve essere un numero.");
			scanner.nextLine();
		}
	}

	public static void getListaDipendenti() {
		String url = Constants.getUrlListaDipendenti;

		ResponseEntity<ResponseDto<Map<Long, Dipendente>>> responseEntity = restTemplate.exchange(url, HttpMethod.GET,
				null, new ParameterizedTypeReference<ResponseDto<Map<Long, Dipendente>>>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			Map<Long, Dipendente> listaDipendenti = responseEntity.getBody().getResponse();

			for (Map.Entry<Long, Dipendente> entry : listaDipendenti.entrySet()) {
				Dipendente dipendente = entry.getValue();
				System.out.println("----------> Dipendente: " + dipendente.toString());
			}
		} else {
			System.err.println("----------> Errore nella chiamata API: " + responseEntity.getBody().getErrore());
		}

	}

	public static void aggiungiDipendente() {

		String url = Constants.getUrlAggiungiDipendente;

		System.out.println("----------> Inserisci i dati del nuovo dipendente:");

		System.out.print("Nome:");
		String nome = getStringInput();

		System.out.print("Cognome:");
		String cognome = getStringInput();

		System.out.print("Data di nascita (formato dd/MM/yyyy):");
		String dataNascita = getDataStringInput();

		System.out.print("Data di assunzione (formato dd/MM/yyyy):");
		String dataAssunzione = getDataStringInput();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascitaDate = LocalDate.parse(dataNascita, formatter);
		LocalDate dataAssunzioneDate = LocalDate.parse(dataAssunzione, formatter);

		Request nuovoDipendente = new Request(nome, cognome, dataNascitaDate, dataAssunzioneDate);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Request> requestEntity = new HttpEntity<>(nuovoDipendente, headers);

		ResponseEntity<ResponseDto<Dipendente>> responseEntity = restTemplate.exchange(url, HttpMethod.POST,
				requestEntity, new ParameterizedTypeReference<ResponseDto<Dipendente>>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			Dipendente dipendente = responseEntity.getBody().getResponse();
			System.out.println("----------> Nuovo dipendente aggiunto con successo!");
			System.out.println(dipendente);
		} else {
			System.err.println("----------> Errore nella chiamata API: " + responseEntity.getBody().getErrore());
		}

	}

	public static void eliminaDipendente() {
		try {
			System.out.println("Inserisci l'ID del dipendente da eliminare:");
			long dipendenteId = scanner.nextInt();

			String url = Constants.getUrlEliminaDipendente + dipendenteId;

			ResponseEntity<ResponseDto<Dipendente>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<ResponseDto<Dipendente>>() {
					});

			if (responseEntity.getStatusCode().is2xxSuccessful()) {
				Dipendente dipendente = responseEntity.getBody().getResponse();
				System.out.println("----------> Dipendente con ID " + dipendenteId + " Eliminato!");
				System.out.println(dipendente);
			} else {
				System.err.println("----------> Errore nella chiamata API: " + responseEntity.getBody().getErrore());
			}

		} catch (InputMismatchException e) {
			System.out.println("----------> Input non valido. L'ID deve essere un numero.");
			scanner.nextLine();
		}
	}

	public static void modificaDipendente() {

		String url = "";
		try {
			System.out.println("Inserisci l'ID del dipendente da modificare:");
			long dipendenteId = scanner.nextInt();
			url = Constants.getUrlModificaDipendente + dipendenteId;
		} catch (InputMismatchException e) {
			System.out.println("----------> Input non valido. L'ID deve essere un numero.");
			scanner.nextLine();
		}

		System.out.println("----------> Inserisci i nuovi dati del dipendente:");

		System.out.print("Nome:");
		String nome = getStringInput();

		System.out.print("Cognome:");
		String cognome = getStringInput();

		System.out.print("Data di nascita (formato dd/MM/yyyy):");
		String dataNascita = getDataStringInput();

		System.out.print("Data di assunzione (formato dd/MM/yyyy):");
		String dataAssunzione = getDataStringInput();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dataNascitaDate = LocalDate.parse(dataNascita, formatter);
		LocalDate dataAssunzioneDate = LocalDate.parse(dataAssunzione, formatter);

		Request nuovoDatiDipendente = new Request(nome, cognome, dataNascitaDate, dataAssunzioneDate);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Request> requestEntity = new HttpEntity<>(nuovoDatiDipendente, headers);

		ResponseEntity<ResponseDto<Dipendente>> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,
				requestEntity, new ParameterizedTypeReference<ResponseDto<Dipendente>>() {
				});

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			Dipendente dipendente = responseEntity.getBody().getResponse();
			System.out.println("----------> Nuovi dati del dipendente modificati con successo!");
			System.out.println(dipendente);
		} else {
			System.err.println("----------> Errore nella chiamata API: " + responseEntity.getBody().getErrore());
		}

	}

	private static String getStringInput() {
		while (true) {
			String input = scanner.next();
			if (input.matches("^[a-zA-Z]{1,30}$")) {
				return input;
			} else {
				System.out.println("----------> Input non valido. Inserisci una stringa valida: ");
			}
		}
	}

	private static String getDataStringInput() {
		while (true) {
			String input = scanner.next();
			if (input.matches(
					"^(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/(198[0-9]|199[0-9]|200[0-9]|201[0-9]|202[0-3])$")) {
				return input;
			} else {
				System.out.println("----------> Input non valido. Inserisci una data valida: ");
			}
		}
	}

}
