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
import controller.GestioneTirocinio.ValutaRelazione;

class ValutaRelazioneTest {

	private ValutaRelazione valRelTest;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		valRelTest = new ValutaRelazione();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	@Test //valutazione id fallito
	void tc_gt2_1() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("approva","1");
		request.addParameter("idrelazione", "5");
		
		valRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("result"); 
		assertFalse(risultato);
		
	}
	
	@Test //valutazione andata a buon fine
	void tc_gt2_2() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("approva","1");
		request.addParameter("idrelazione", "5");
		valRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("result"); 
		assertFalse(risultato);
		
	}
	
	@Test //valutazione andata a buon fine
	void tc_gt2_3() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("approva","");
		request.addParameter("idrelazione", "5");
		valRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("result"); 
		assertFalse(risultato);
		
	}
	
	@Test //test accesso negato email diversa da  tutor aziendale
	void tc_gt2_4() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("approva","1");
		request.addParameter("idrelazione", "5");
		valRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("result"); 
		assertFalse(risultato);
	}
	
	@Test //test accesso negato email diversa da  tutor aziendale
	void tc_gt2_5() throws ServletException, IOException {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("approva","1");
		request.addParameter("idrelazione", "54");
		valRelTest.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("result"); 
		assertFalse(risultato);
	}
	
	
	
	
	
	
	
	
	
}
