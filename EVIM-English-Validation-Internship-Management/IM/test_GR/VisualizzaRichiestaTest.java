package test_GR;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import controller.GestioneRichiesta.VisualizzaRichieste;
import controller.GestioneAutenticazione.Login;
import model.PDFProgettoFormativo;
import model.DriverManagerConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class VisualizzaRichiestaTest extends Mockito{

	private VisualizzaRichieste servletVisualizzaR;
	private Login servletLogin;
	private PDFProgettoFormativo servletPDF;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletVisualizzaR = new VisualizzaRichieste();
		servletLogin = new Login();
		servletPDF = new PDFProgettoFormativo();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test //nessun utente loggato
	public void tc_gr_1() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password"); 
		servletLogin.doPost(request, response);
		servletVisualizzaR.doPost(request, response);
	}
	@Test  //login studente triennale a buon fine
	public void tc_gr_2() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		servletVisualizzaR.doPost(request, response);
	}


	@Test  //login studente magistrale a buon fine
	public void tc_gr_3() throws ServletException, IOException  {
		request.addParameter("email","attiliodellag1@studenti.unisa.it");
		request.addParameter("password", "attilio1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		servletVisualizzaR.doPost(request, response);
	}

	@Test //accesso come pdcd
	public void tc_gr_4() throws ServletException, IOException {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		servletVisualizzaR.doPost(request, response);
	}
	@Test //accesso ufficio carrira
	public void tc_gr_5() throws ServletException, IOException {
		request.addParameter("email","segreteria@unisa.it");
		request.addParameter("password", "segreteria1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("segreteria", type);
		servletVisualizzaR.doPost(request, response);
	}

	@Test //accesso come tutor academico
	public void tc_gr_6() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		servletVisualizzaR.doPost(request, response);
	}
	
	@Test //accesso come tutor aziendale
	public void tc_gr_7() throws ServletException, IOException {
		request.addParameter("email","egrande@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		servletVisualizzaR.doPost(request, response);
	}


	@Test //accesso come tutor aziendale
	public void tc_gr_8() throws ServletException, IOException {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		servletVisualizzaR.doPost(request, response);
	}



}
