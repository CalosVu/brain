package com.brain.management.model;

import java.util.List;

import lombok.Data;

@Data
public class BodyResponseDto {
	
	private List<Dipendente> infoDipendenti;	
	
	public BodyResponseDto(List<Dipendente> infoDipendenti) {
		super();
		this.infoDipendenti = infoDipendenti;		
	} 	

}
