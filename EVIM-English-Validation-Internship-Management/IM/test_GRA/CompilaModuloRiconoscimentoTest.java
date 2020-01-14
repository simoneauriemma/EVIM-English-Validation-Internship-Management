package test_GRA;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import controller.GestioneAutenticazione.Login;
import controller.GestioneModuloRiconoscimento.CompilaModuloRiconoscimento;
import controller.GestioneModuloRiconoscimento.VisualizzaElencoModuliRiconoscimento;

class CompilaModuloRiconoscimentoTest {

	private CompilaModuloRiconoscimento servletCompilaM;
	private Login servletLogin;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@BeforeEach
	void setUp() throws Exception {
		servletCompilaM = new CompilaModuloRiconoscimento();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}

	// login studente
	@Test
	public void tc_gra_1_1() throws ServletException, IOException {
		request.addParameter("email", "simonagrieco@studenti.unisa.it");
		request.addParameter("password", "grieco1998");
		servletLogin.doPost(request, response);
		String type = request.getSession().getAttribute("type").toString();
		boolean log = (boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("enteAzienda", "Microsoft");
		servletCompilaM.doPost(request, response);
	}

	// login studente
	@Test
	public void tc_gra_1_2() throws ServletException, IOException {
		request.addParameter("email", "fferrucci@unisa.it");
		request.addParameter("password", "Ferrucci11");
		servletLogin.doPost(request, response);
		String type = request.getSession().getAttribute("type").toString();
		boolean log = (boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("pdcd", type);
		servletCompilaM.doPost(request, response);
	}

	// login studente
	@Test
	public void tc_gra_1_3() throws ServletException, IOException {
		request.addParameter("email", "segreteria@unisa.it");
		request.addParameter("password", "segreteria1");
		servletLogin.doPost(request, response);
		String type = request.getSession().getAttribute("type").toString();
		boolean log = (boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("segreteria", type);
		servletCompilaM.doPost(request, response);
	}

	// login nullo
	@Test
	public void tc_gra_1_4() throws ServletException, IOException {
		servletCompilaM.doPost(request, response);
	}

	// login tutoracc
	@Test
	public void tc_gra_1_5() throws ServletException, IOException {
		request.addParameter("email", "mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type = request.getSession().getAttribute("type").toString();
		boolean log = (boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		servletCompilaM.doPost(request, response);
	}

}