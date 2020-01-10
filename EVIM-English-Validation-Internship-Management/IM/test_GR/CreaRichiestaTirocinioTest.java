package test_GR;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


import controller.GestioneAutenticazione.Login;
import controller.GestioneRichiesta.CreaRichiestaTirocinio;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CreaRichiestaTirocinioTest extends Mockito{ 

	private CreaRichiestaTirocinio servletCrea;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletCrea = new CreaRichiestaTirocinio();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	@Test //tirocinio esterno
	public void tc_gra_1() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("sel3", ""+7);
		request.addParameter("sel4", ""+2);
		request.addParameter("cfu","11");
		request.addParameter("sel1","tirocinio1");
		
		servletCrea.doPost(request, response);
			}
	
	
	
	@Test //Campi email e password vuoti
	public void tc_gp1_3() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password", "");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		assertEquals("studente", "studente");
		request.addParameter("sel3", ""+7);
		request.addParameter("sel4", ""+2);
		request.addParameter("cfu","11");
		request.addParameter("sel1","tirocinio1");
		servletCrea.doPost(request, response);
	}
	@Test //utente diverso da studente
	public void tc_gra_4() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("sel3", ""+7);
		request.addParameter("sel4", ""+2);
		request.addParameter("cfu","11");
		request.addParameter("sel1","tirocinio1");
		
		servletCrea.doPost(request, response);
		
	}
	@Test //Tirocinio Interno
	public void tc_gra_5() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("sel3", ""+8);
		request.addParameter("sel4", ""+1);
		request.addParameter("cfu","11");
		request.addParameter("sel1","tirocinio2");
		
		servletCrea.doPost(request, response);

	}
	
	@Test //campo tirocinio non selezionato
	public void tc_gra_6() throws ServletException, IOException  {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("sel3", ""+8);
		request.addParameter("sel4", ""+1);
		request.addParameter("cfu","11");
		request.addParameter("sel1","");
		
		servletCrea.doPost(request, response);

	}
	@Test //accedo come presidente del consiglio
	public void tc_gra_7() throws ServletException, IOException  {
		request.addParameter("email","fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("pdcd", type);
		request.addParameter("sel3", ""+8);
		request.addParameter("sel4", ""+1);
		request.addParameter("cfu","11");
		request.addParameter("sel1","");
		servletCrea.doPost(request, response);

	}
}