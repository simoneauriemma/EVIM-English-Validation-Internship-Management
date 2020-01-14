package test_GRA;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneAutenticazione.Login;
import controller.GestioneModuloRiconoscimento.ApprovaRifiutaModuloRiconoscimento;

class ApprovaRifiutaModuloRiconoscimentoTest {

	private ApprovaRifiutaModuloRiconoscimento servletApprova;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		servletApprova = new ApprovaRifiutaModuloRiconoscimento();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	//pdcd approva la richiesta
	@Test
	void tc_gra_1_0() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		request.addParameter("modifica", "approva");
		request.addParameter("idRiconoscimento", "10");
		servletApprova.doPost(request, response);
	}
	
	//pdcd rifiuta la richiesta
	@Test
	void tc_gra_1_1() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		request.addParameter("modifica", "rifiuta");
		request.addParameter("idRiconoscimento", "11");
		servletApprova.doPost(request, response);
	}
	
	//tutoracc intende entrare
	@Test
	void tc_gra_1_2() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("modifica", "rifiuta");
		request.addParameter("idRiconoscimento", "1");
		servletApprova.doPost(request, response);
	}
	
	//studente intende entrare
	@Test
	void tc_gra_1_3() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("modifica", "rifiuta");
		request.addParameter("idRiconoscimento", "1");
		servletApprova.doPost(request, response);
	}
	
	//nessun utente loggato
		@Test
		void tc_gra_1_4() throws ServletException, IOException {
			request.addParameter("modifica", "rifiuta");
			request.addParameter("idRiconoscimento", "1");
			servletApprova.doPost(request, response);
		}
	
}