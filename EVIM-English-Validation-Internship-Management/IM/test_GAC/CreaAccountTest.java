package test_GAC;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneAccount.CreaAccount;
import controller.GestioneAutenticazione.Login;
import model.DriverManagerConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

	public class CreaAccountTest extends Mockito{
	
	  private CreaAccount servletCreaAccount;
	  private Login servletLogin;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servletCreaAccount = new CreaAccount();
	    servletLogin = new Login();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	//l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "nome" è vuoto
      @Test
	  public void tc_gac_1_1() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "cognome" è vuoto
      @Test
	  public void tc_gac_1_2() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "email" è vuoto
      @Test
	  public void tc_gac_1_3() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "conferma password" è vuoto
      @Test
	  public void tc_gac_1_4() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma le password non coincidono
      @Test
	  public void tc_gac_1_5() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellacaio");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
      //l'azienda prova a creare un nuovo account ma il campo "telefono" è vuoto
      @Test
	  public void tc_gac_1_6() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account ma il campo "password" è vuoto
      @Test
	  public void tc_gac_1_7() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);

	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor inserendo correttamente tutti i dati
      @Test
	  public void tc_gac_1_8() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertTrue(esito);

	  }
      
      //l'azienda prova a creare un nuovo account per un tutor aziendale gia esistente
      @Test
	  public void tc_gac_1_9() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "microsoft1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
		   
      }

		 //utente non autorizzato
		      @Test
			  public void tc_gac_1_10() throws ServletException, IOException  {
		    	   request.addParameter("email","fferrucci@unisa.it");
		    	   request.addParameter("password", "Ferrucci11"); 
		    	   servletLogin.doPost(request, response);
		    	   String type = request.getSession().getAttribute("type").toString();
		    	   boolean log = (boolean) request.getAttribute("logged");  
		    	   assertTrue(log);
		    	   assertEquals("pdcd", type);
		    	   request.addParameter("nome","Mario");
				   request.addParameter("cognome","Ambrosio");
				   request.addParameter("telefono", "3332233444");
				   request.addParameter("emaila", "ambrosiomario@hotmail.it");
				   request.addParameter("passworda","bellaciao");
				   request.addParameter("confermaPassword","bellaciao");
				   servletCreaAccount.doPost(request, response);
				   boolean esito = (boolean) request.getAttribute("utenteCreato");
				   assertFalse(esito);
		   
	  }
      
    //azienda non loggata
      @Test
	  public void tc_gac_1_11() throws ServletException, IOException  {
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("telefono", "3332233444");
		   request.addParameter("emaila", "ambrosiomario@hotmail.it");
		   request.addParameter("passworda","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);

	  }
      
	}