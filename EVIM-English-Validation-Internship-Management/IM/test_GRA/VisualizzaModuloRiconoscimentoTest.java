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
import controller.GestioneModuloRiconoscimento.VisualizzaModuloRiconoscimento;

class VisualizzaModuloRiconoscimentoTest {

	private VisualizzaModuloRiconoscimento visualizaMR;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		visualizaMR = new VisualizzaModuloRiconoscimento();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	//studente femmina
	@Test
	void tc_gra_1_1() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("idRiconoscimento", "1");		
		visualizaMR.doPost(request, response);
	}
	
	//studente maschio
	@Test
	void tc_gra_1_2() throws ServletException, IOException {
		request.addParameter("email","nicolasisti@studenti.unisa.it");
		request.addParameter("password", "nicola1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("idRiconoscimento", "11");		
		visualizaMR.doPost(request, response);
	}
	
	//non loggato
	@Test
	void tc_gra_1_3() throws ServletException, IOException {
		request.addParameter("idRiconoscimento", "11");		
		visualizaMR.doPost(request, response);
	}
	
	//pdcd
	@Test
	void tc_gra_1_4() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		request.addParameter("idRiconoscimento", "1");		
		request.addParameter("emailUser", "simonagrieco@studenti.unisa.it");
		visualizaMR.doPost(request, response);
	}
}
