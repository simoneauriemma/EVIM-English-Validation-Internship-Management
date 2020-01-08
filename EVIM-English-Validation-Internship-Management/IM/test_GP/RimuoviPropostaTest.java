package test_GP;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneProposta.RimuoviProposta;
import controller.GestioneAutenticazione.Login;

import java.io.IOException;


import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class RimuoviPropostaTest extends Mockito{ 

	private RimuoviProposta servletRProposta;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletRProposta = new RimuoviProposta();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	
	@Test
	public void tc_gp3_1() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password", "");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		assertEquals("tutoraccademico","tutoraccademico");
		request.addParameter("idProposta","2");
		servletRProposta.doPost(request, response);
	}
	@Test  //CONTROLLARE ID PROPOSTA NEL DB
	public void tc_gp1_5() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "Xboxthebest"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("idProposta","2");
		servletRProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("rimuovi");
		assertTrue(esito);
	}
	@Test
	public void tc_gp1_6() throws ServletException, IOException  {
		request.addParameter("email","antoniosultani@unisa.it");
		request.addParameter("password", "radiomaria11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("idProposta","3");
		servletRProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("rimuovi");
		assertTrue(esito);
	}
	@Test
	public void tc_gp1_7() throws ServletException, IOException  {
		request.addParameter("email","antoniosultani@unisa.it");
		request.addParameter("password", "radiomaria11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("idProposta","3");
		servletRProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("rimuovi");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_8() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("idProposta","3");
		servletRProposta.doPost(request, response);
		
	}
	
	
	
}