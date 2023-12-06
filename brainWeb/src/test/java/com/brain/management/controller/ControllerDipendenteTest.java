package com.brain.management.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.brain.management.data.StrutturaDatiSingleton;
import com.brain.management.exceptions.DuplicateException;
import com.brain.management.model.Dipendente;
import com.brain.management.services.ServiceDipendente;
import com.brain.management.utils.Constants;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(ControllerDipendente.class)
@Slf4j
public class ControllerDipendenteTest {

	@Autowired
	private MockMvc mockMvc;

//	@Autowired
//	private ServiceDipendente serviceDipendente;

	// viene utilizzato per creare un mock del servizio (ServiceDipendente) e lo
	// inietta nel contesto dell'applicazione.
	@MockBean
	private ServiceDipendente serviceDipendente; // Simula il servizio per il tuo controller

	private static final String URL_DIPENDENTE = Constants.getUrlDipendente;

	/**
	 * Configurazione iniziale per i test. Viene eseguito una volta prima di tutti i
	 * test.
	 *
	 * @throws DuplicateException Lanciato se si verifica l'inserimento di un
	 *                            dipendente già esistente .
	 */
	@BeforeEach
	public void setup() throws DuplicateException {
		log.info("Siamo in test SETUP");
		// Creare un dipendente di test
		// viene eseguito una volta prima di tutti i test
//		StrutturaDatiSingleton dataLoader = StrutturaDatiSingleton.getInstance();
//		Dipendente dipendente = new Dipendente();
//		dipendente.setNome("Lillo");
//		dipendente.setCognome("Vullo");
//		dipendente.setDataNascita(dataLoader.generateRandomDate(1980, 2000));
//		dipendente.setDataAssunzione(dataLoader.generateRandomDate(1998, 2023));
//		serviceDipendente.addDipendente(dipendente);
	}

	/**
	 * Testa il recupero delle informazioni di un dipendente.
	 *
	 * @throws Exception Lanciato se si verifica un problema durante il test.
	 */
	@Test
	public void testGetDipendente() throws Exception {

		log.info("Siamo in testGetDipendente");
		// MockMvc viene usato per simulare le richieste HTTP.
		ResultActions resultActions = mockMvc
				.perform(MockMvcRequestBuilders.get(URL_DIPENDENTE + "1").contentType(MediaType.APPLICATION_JSON));

		// jsonPath è una funzione utilizzata nei test di integrazione per
		// verificare i valori all'interno di una risposta JSON.
		resultActions.andExpect(status().isOk());

		// Ottieni il corpo della risposta come stringa
		String responseBody = resultActions.andReturn().getResponse().getContentAsString();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode response = objectMapper.readTree(responseBody);

		// Verifica i valori nel corpo della risposta
		assertEquals(HttpStatus.OK.getReasonPhrase(), response.path("httpStatus").asText());
		assertEquals("Informazioni dipendente", response.path("message").asText());
		assertNotNull(response);

		// Verifica la presenza di alcuni campi specifici
		assertTrue(response.has("response"));
		assertTrue(response.path("response").has("nome"));
		assertTrue(response.path("response").has("cognome"));
	}

	@Test
	public void testAggiungiDipendente() throws Exception {
		// Dati di test
		StrutturaDatiSingleton dataLoader = StrutturaDatiSingleton.getInstance();
		Dipendente dipendente = new Dipendente();
		dipendente.setNome("Lillo");
		dipendente.setCognome("Vullo");
		dipendente.setDataNascita(dataLoader.generateRandomDate(1980, 2000));
		dipendente.setDataAssunzione(dataLoader.generateRandomDate(1998, 2023));

		// when(...).thenReturn(...) imposta il comportamento desiderato del mock quando
		// viene chiamato il metodo addDipendente.
		when(serviceDipendente.addDipendente(any(Dipendente.class))).thenReturn(1L); // ID simulato

		// Esegui la richiesta API tramite MockMvc
		mockMvc.perform(post("/aggiungidipendente").contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(dipendente))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.httpStatus").value("CREATED"))
				.andExpect(jsonPath("$.message").value("Dipendente aggiunto con successo. id: 1"))
				.andExpect(jsonPath("$.data.nome").value("Lillo")).andExpect(jsonPath("$.data.cognome").value("Vullo"));

		// Verifica che il servizio sia stato chiamato con l'oggetto Dipendente corretto
		verify(serviceDipendente, times(1)).addDipendente(eq(dipendente));
	}

}
