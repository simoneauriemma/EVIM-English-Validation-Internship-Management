package test_GP;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneProposta.creaProposta;
import controller.GestioneAutenticazione.Login;

import java.io.IOException;
import javax.servlet.ServletException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CreaPropostaTest extends Mockito{ 

	private creaProposta servletProposta;
	private Login servletLogin;
	private MockHttpServletRequest request; 
	private MockHttpServletResponse response;

	/**
	 * Before.
	 */
	@Before
	public void setUp() {
		servletProposta = new creaProposta();
		servletLogin = new Login();
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
	}
	@Test
	public void tc_gp1_1() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","2");
		request.addParameter("competenze","gksglskgnskgisgjsighj");
		request.addParameter("attivita", "La iafosgisrjgosgoj");
		request.addParameter("obiettivo", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertTrue(esito);
	}

	@Test
	public void tc_gp1_2() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","-1");
		request.addParameter("competenze","gksglskgnskgisgjsighj");
		request.addParameter("attivita", "La iafosgisrjgosgoj");
		request.addParameter("obiettivo", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}

	@Test
	public void tc_gp1_3() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_4() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","gksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighjgksglskgnskgisgjsighj");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_5() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibsifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_6() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_7() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "lsgjisgvuosjbiosrnpi");
		request.addParameter("obiettivo", "lsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpilsgjisgvuosjbiosrnpi");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_8() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "lsgjisgvuosjbiosrnpi");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_9() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "lsgjisgvuosjbiosrnpi");
		request.addParameter("obiettivo", "�vnbkdnbldsbnlsbndsp");
		request.addParameter("modalita", "�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp�vnbkdnbldsbnlsbndsp");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_10() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","1");
		request.addParameter("competenze","ifjbpsnbpsjviksnvsa�nvpsfvbs��nsifbsnibs");
		request.addParameter("attivita", "lsgjisgvuosjbiosrnpi");
		request.addParameter("obiettivo", "�vnbkdnbldsbnlsbndsp");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}

	@Test
	public void tc_gp1_11() throws ServletException, IOException  {
		request.addParameter("email","mconcetta@studenti.unisa.it");
		request.addParameter("password", "mconcetta1998");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("studente", type);
		request.addParameter("tutorAziendale","2");
		request.addParameter("competenze","La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("attivita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("obiettivo", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("modalita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}

	/*@Test
	public void tc_gp1_12() throws ServletException, IOException  {
		request.addParameter("email","aaster@tutor.unisa.it");
		request.addParameter("password", "ciaociao1");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraziendale", type);
		request.addParameter("tutorAziendale","2");
		request.addParameter("competenze","La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("attivita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("obiettivo", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("modalita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}*/
@Test
	public void tc_gp1_13() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("attivita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("obiettivo", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("modalita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertTrue(esito);
	}
	@Test
	public void tc_gp1_14() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazione");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_15() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_16() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "La Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di Ateneo ospita presso la propria sede tirocini di formazioneLa Biblioteca Centrale di ");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_17() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_18() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("obiettivo", "lvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_19() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("obiettivo", "");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_20() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log); 
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("obiettivo", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("modalita", "lvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjslvnspbpsjvpsbpsbpsbpjs");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_21() throws ServletException, IOException  {
		request.addParameter("email","mariogiorgio@unisa.it");
		request.addParameter("password", "umpalumpa2");
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");
		assertTrue(log);
		assertEquals("tutoraccademico", type);
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("obiettivo", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("modalita", "");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	@Test
	public void tc_gp1_22() throws ServletException, IOException  {
		request.addParameter("email");
		request.addParameter("password", "");
		servletLogin.doPost(request, response);
		boolean log=(boolean) request.getAttribute("loggedTest");
		request.getSession().setAttribute("utenteLoggato", null);
		assertFalse(log);
		assertEquals("tutoraccademico","tutoraccademico");
		request.addParameter("competenze","lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("attivita", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("obiettivo", "lvnspbpsjvpsbpsbpsbpjs");
		request.addParameter("modalita", "<knvanvapvpavnpiav");
		servletProposta.doPost(request, response);
	}
	
	@Test
	public void tc_gp1_23() throws ServletException, IOException  {
		request.addParameter("email","microsoftofficial@tiscali.it");
		request.addParameter("password", "microsoft1"); 
		servletLogin.doPost(request, response);
		String type=request.getSession().getAttribute("type").toString();
		boolean log=(boolean) request.getAttribute("logged");  
		assertTrue(log);
		assertEquals("azienda", type);
		request.addParameter("tutorAziendale","2");
		request.addParameter("competenze","gksglskgnskgisgjsighj");
		request.addParameter("attivita", "Il numero di caratteri è molto importante per Twitter, Facebook o un semplice testo ad un collaboratore o funzionario commerciale. Quello che dite può non essere così importante come il modo in cui lo dite. E quanti caratteri usate.");
		request.addParameter("obiettivo", "La Bigjspgkjogogjfdj");
		request.addParameter("modalita", "La Biblioteca Centrale ddkgshjsjgiosn");
		servletProposta.doPost(request, response);
		boolean esito=(boolean) request.getAttribute("risultatoInserimentoProposta");
		assertFalse(esito);
	}
	
}
