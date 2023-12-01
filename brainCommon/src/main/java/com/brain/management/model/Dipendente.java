package com.brain.management.model;

import java.time.LocalDate;
import java.util.Objects;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dipendente {

	private Long id;
	private String nome;
	private String cognome;
	private LocalDate dataNascita;
	private LocalDate dataAssunzione;

	public Dipendente(Long id, String nome, String cognome, LocalDate dataNascita, LocalDate dataAssunzione) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataNascita = dataNascita;
		this.dataAssunzione = dataAssunzione;
	}	

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Dipendente daConfrontare = (Dipendente) o;
		return nome.equals(daConfrontare.nome) && cognome.equals(daConfrontare.cognome)
				&& dataNascita.equals(daConfrontare.dataNascita) && dataAssunzione.equals(daConfrontare.dataAssunzione);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, cognome, dataNascita, dataAssunzione);
	}
	

}
