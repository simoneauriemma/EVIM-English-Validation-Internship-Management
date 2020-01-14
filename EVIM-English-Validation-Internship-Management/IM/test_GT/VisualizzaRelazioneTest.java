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
import controller.GestioneTirocinio.VisualizzaRelazione;

class VisualizzaRelazioneTest {

	private VisualizzaRelazione servletVisualizzaR;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		servletVisualizzaR = new VisualizzaRelazione();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
/*
	@Test //nessun utente loggato
	public void tc_gt_1() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password"); 
		servletLogin.doPost(request, response);
		servletVisualizzaR.doPost(request, response);
	}*/
	
	@Test //accesso come tutor aziendale
	public void tc_gt_2() throws ServletException, IOException {
		request.addParameter("email","egrande@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		request.setAttribute("idRelazione", 8);
		servletVisualizzaR.doPost(request, response);
	}
	
	
	
}