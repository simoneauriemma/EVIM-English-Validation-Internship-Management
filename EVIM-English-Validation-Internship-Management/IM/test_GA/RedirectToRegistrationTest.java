package test_GA;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;





import controller.GestioneAutenticazione.Login;
import controller.GestioneAutenticazione.RedirectToRegistration;

import java.io.IOException;


import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class RedirectToRegistrationTest extends Mockito{ 

	private RedirectToRegistration servletRedirect;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletRedirect = new RedirectToRegistration();
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
		servletRedirect.doPost(request, response);
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
		servletRedirect.doGet(request, response);
		assertFalse((boolean)request.getAttribute("Autorizzato"));
	}
	
}