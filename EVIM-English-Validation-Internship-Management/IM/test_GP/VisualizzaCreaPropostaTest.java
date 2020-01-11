package test_GP;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;



import controller.GestioneProposta.visualizzaCreaProposta;

import controller.GestioneAutenticazione.Login;

import java.io.IOException;


import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class VisualizzaCreaPropostaTest extends Mockito{ 

	private visualizzaCreaProposta servletVCProposta;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletVCProposta = new visualizzaCreaProposta();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	@Test //campi nulli
	public void tc_gp4_1() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		servletVCProposta.doPost(request, response);
		assertFalse((boolean)request.getAttribute("Autorizzato"));
	}
	@Test //accesso come pdcd non consentito
	public void tc_gp4_2() throws ServletException, IOException  {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("pdcd",type);
		servletVCProposta.doPost(request, response);
		assertFalse((boolean)request.getAttribute("Autorizzato"));
	}
	

	@Test //accesso come tutor accademico consentito
	public void tc_gp4_3() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		servletVCProposta.doGet(request, response);
		assertTrue((boolean)request.getAttribute("Autorizzato"));
	}
	
	@Test
	public void tc_gp4_4() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		servletVCProposta.doGet(request, response);
		assertTrue((boolean)request.getAttribute("Autorizzato"));
	
	}
	
	@Test
	public void tc_gp4_5() throws ServletException, IOException  {
		request.addParameter("email","uughi@tutor.unisa.it");
		request.addParameter("password", "ciaociao1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		servletVCProposta.doGet(request, response);
		assertFalse((boolean)request.getAttribute("Autorizzato"));
	
	}
	
	
}
