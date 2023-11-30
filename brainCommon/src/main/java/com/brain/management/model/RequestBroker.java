package com.brain.management.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RequestBroker {
	
	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private LocalDate dataAssunzione;

}
