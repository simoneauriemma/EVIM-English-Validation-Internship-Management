package test_GP;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import controller.GestioneProposta.VisualizzaProposte;
import model.Proposta;
import controller.GestioneAutenticazione.Login;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class VisualizzaPropostaTest extends Mockito{ 

	private VisualizzaProposte servletVProposta;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletVProposta = new VisualizzaProposte();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test
	public void tc_gp1_1() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		servletVProposta.doGet(request, response);
		ArrayList<Proposta> attivita=(ArrayList<Proposta>) request.getAttribute("proposte");
		assertNotEquals(0, attivita.size());
	}
	@Test
	public void tc_gp1_2() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password", "");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		assertEquals("tutoraccademico","tutoraccademico");
		servletVProposta.doPost(request, response);
	}
	@Test
	public void tc_gp1_3() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "Xboxthebest"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		servletVProposta.doGet(request, response);
		ArrayList<Proposta> attivita=(ArrayList<Proposta>) request.getAttribute("proposte");
		assertNotEquals(0, attivita.size());
	}
	@Test
	public void tc_gp1_4() throws ServletException, IOException  {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		servletVProposta.doGet(request, response);
		ArrayList<Proposta> attivita=(ArrayList<Proposta>) request.getAttribute("proposte");
		assertNotEquals(0, attivita.size());
	}
	@Test
	public void tc_gp1_5() throws ServletException, IOException  {
		request.addParameter("email","edoardo93av@studenti.unisa.it");
		request.addParameter("password", "edoardo93@"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		servletVProposta.doGet(request, response);
		ArrayList<Proposta> attivita=(ArrayList<Proposta>) request.getAttribute("proposte");

	}
}