package com.brain.management.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Request {

	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private LocalDate dataAssunzione;

	public Request(String nome, String cognome, LocalDate dataNascita, LocalDate dataAssunzione) {
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
	}

}
