package test_GT;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneAutenticazione.Login;
import controller.GestioneRichiesta.CreaRichiestaTirocinio;
import controller.GestioneTirocinio.CreaRelazione;

class CreaRelazioneTest {

	private CreaRelazione creaRelTest;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		creaRelTest = new CreaRelazione();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test //testcrearelazione
	void tc_gt2_1() throws ServletException, IOException {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		request.addParameter("descrizione", "questa e la relazione leggere con attenzione");
		request.addParameter("emailstudente", "simonagrieco@studenti.unisa.it");
		creaRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultrelazione"); 
		assertTrue(risultato);
		
	}
	@Test //test accesso negato email diversa da  tutor aziendale
	void tc_gt2_2() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("descrizione", "questa e la relazione leggere con attenzione");
		request.addParameter("emailstudente", "simonagrieco@studenti.unisa.it");
		creaRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultrelazione"); 
		assertFalse(risultato);
		
	}
	@Test
	void tc_gt2_3() throws ServletException, IOException {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		request.addParameter("descrizione");
		request.addParameter("emailstudente", "simonagrieco@studenti.unisa.it");
		creaRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultrelazione"); 
		assertFalse(risultato);
		
	}
	
	@Test
	void tc_gt2_4() throws ServletException, IOException {
		request.addParameter("descrizione");
		request.addParameter("emailstudente", "simonagrieco@studenti.unisa.it");
		creaRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultrelazione"); 
		assertFalse(risultato);
	}
	
	@Test
	void tc_gt2_5() throws ServletException, IOException {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		request.addParameter("descrizione","questa e la relazione leggere con attenzione");
		request.addParameter("emailstudente");
		creaRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultrelazione"); 
		assertFalse(risultato);
		
	}
}





