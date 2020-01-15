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
import controller.GestioneTirocinio.QuestionarioS;


class QuestionarioSTest {

	private QuestionarioS questStu;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		questStu = new QuestionarioS();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	@Test //valutazione id fallito
	void tc_gt2_1() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("gruppo","1");
		request.addParameter("gruppo1","1");
		request.addParameter("gruppo2", "1");
		request.addParameter("gruppo3", "1");
		request.addParameter("gruppo4", "1");
		request.addParameter("gruppo5", "1");
		request.addParameter("gruppo6", "1");
		request.addParameter("gruppo7", "1");
		request.addParameter("gruppo8", "5");
		request.addParameter("gruppo9", "1");
		request.addParameter("gruppo10", "1");
		request.addParameter("gruppo11", "1");
		request.addParameter("gruppo12", "1");
		request.addParameter("gruppo13", "1");
		questStu.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultqs"); 
		assertTrue(risultato);
		
	}
	
	/*@Test //valutazione id fallito
	void tc_gt2_2() throws ServletException, IOException {
		request.addParameter("email","simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("gruppo");
		request.addParameter("gruppo1","1");
		request.addParameter("gruppo2", "1");
		request.addParameter("gruppo3", "1");
		request.addParameter("gruppo4", "1");
		request.addParameter("gruppo5", "1");
		request.addParameter("gruppo6", "1");
		request.addParameter("gruppo7", "1");
		request.addParameter("gruppo8", "1");
		request.addParameter("gruppo9", "1");
		request.addParameter("gruppo10", "1");
		request.addParameter("gruppo11", "1");
		request.addParameter("gruppo12", "1");
		request.addParameter("gruppo13", "1");
		questStu.doPost(request, response);
		boolean risultato=(boolean) request.getAttribute("resultqs"); 
		assertFalse(risultato);
		
	}*/
}