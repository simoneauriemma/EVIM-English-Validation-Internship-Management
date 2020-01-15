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
import controller.GestioneModuloRiconoscimento.VisualizzaCompilaModuloRiconoscimento;
import controller.GestioneModuloRiconoscimento.VisualizzaElencoModuliRiconoscimento;

class VisualizzaCompilaModuloRiconoscimentoTest {

	private VisualizzaCompilaModuloRiconoscimento servletVisualizzaM;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		servletVisualizzaM = new VisualizzaCompilaModuloRiconoscimento();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	

	@Test  //login studente a buon fine
	public void tc_gra_1() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		servletVisualizzaM.doPost(request, response);
	}
	
	@Test //nessun utente loggato
	public void tc_gra_2() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password"); 
		servletLogin.doPost(request, response);
		servletVisualizzaM.doPost(request, response);
	}

	@Test
	void tc_gra_3() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		servletVisualizzaM.doPost(request, response);
	}
	
	@Test //accesso come pdcd
	void tc_gra_4() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		servletVisualizzaM.doPost(request, response);
	}
	

	@Test //accesso ufficio carrira
	void tc_gra_5() throws ServletException, IOException {
		request.addParameter("email","segreteria@unisa.it");
		request.addParameter("password", "segreteria1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("segreteria", type);
		servletVisualizzaM.doPost(request, response);
	}
	
}