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
import controller.GestioneRichiesta.CreaRichiestaTirocinio;

class VisualizaModuloRiconoscimentoTest {

	private VisualizzaCompilaModuloRiconoscimento visualizaMR;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;
	
	@BeforeEach
	void setUp() throws Exception {
		visualizaMR = new VisualizzaCompilaModuloRiconoscimento();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	void tc_gra_1_0() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		visualizaMR.doPost(request, response);
	}
	
	@Test
	void tc_gra_1_1() throws ServletException, IOException {
		
		visualizaMR.doPost(request, response);
	}
	
	@Test
	void tc_gra_1_2() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		visualizaMR.doPost(request, response);
	}
	
	@Test
	void tc_gra_1_3() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		visualizaMR.doPost(request, response);
	}
	
	@Test
	void tc_gra_1_4() throws ServletException, IOException {
		request.addParameter("email","segreteria@unisa.it");
		request.addParameter("password", "segreteria1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		visualizaMR.doPost(request, response);
	}
	
	
	@Test
	void tc_gra_1_5() throws ServletException, IOException {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		visualizaMR.doPost(request, response);
	}

}
