package test_GP;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneProposta.ModificaProposta;
import controller.GestioneAutenticazione.Login;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class ModificaPropostaTest extends Mockito{ 

	private ModificaProposta servletMProposta;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletMProposta = new ModificaProposta();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		}
	
	@Test
	public void tc_gp2_1() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password", "");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		assertEquals("tutoraccademico","tutoraccademico");
		request.addParameter("idProposta","1");
		request.addParameter("obbettivo","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("competenze", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("modalita", "<knvanvapvpavnpiav");
		servletMProposta.doPost(request, response);
	}
	
	@Test
	public void tc_gp2_2() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("idProposta","1");
		request.addParameter("obiettivo","gksglskgnskgisgjsighj");
		request.addParameter("competenze", "La iafosgisrjgosgoj");
		request.addParameter("attivita", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletMProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("modifica");
		assertTrue(esito);
	}
	@Test
	public void tc_gp2_3() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("idProposta","-1");
		request.addParameter("obiettivo","gksglskgnskgisgjsighj");
		request.addParameter("competenze", "La iafosgisrjgosgoj");
		request.addParameter("attivita", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletMProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("modifica");
		assertFalse(esito);
	}
	@Test 
	public void tc_gp2_4() throws ServletException, IOException  {
		request.addParameter("email","edoardo93av@studenti.unisa.it");
		request.addParameter("password", "edoardo93@"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("idProposta","1");
		request.addParameter("obiettivo","gksglskgnskgisgjsighj");
		request.addParameter("competenze", "La iafosgisrjgosgoj");
		request.addParameter("attivita", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletMProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("modifica");
		assertFalse(esito);
	}
	@Test
	public void tc_gp2_5() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "Xboxthebest"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("idProposta","1");
		request.addParameter("obiettivo","gksglskgnskgisgjsighj");
		request.addParameter("competenze", "La iafosgisrjgosgoj");
		request.addParameter("attivita", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		request.addParameter("tutorAziendale","1");
		servletMProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("modifica");
		assertTrue(esito);
	}
	
	@Test
	public void tc_gp2_6() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "Xboxthebest"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("idProposta","-1");
		request.addParameter("obiettivo","gksglskgnskgisgjsighj");
		request.addParameter("competenze", "La iafosgisrjgosgoj");
		request.addParameter("attivita", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		request.addParameter("tutorAziendale","1");
		servletMProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("modifica");
		assertFalse(esito);
	}
	
}