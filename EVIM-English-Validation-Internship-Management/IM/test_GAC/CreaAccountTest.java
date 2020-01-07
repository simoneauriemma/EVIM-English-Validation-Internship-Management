package test_GAC;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneAccount.CreaAccount;
import controller.Login;
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
	  

	//l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "nome" non è valido
      @Test
	  public void tc_gac_1_1() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","M");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "nome" non è valido
      @Test
	  public void tc_gac_1_2() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario&");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "cognome" non è valido
      @Test
	  public void tc_gac_1_3() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","A");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "cognome" non è valido
      @Test
	  public void tc_gac_1_4() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio&");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "nome utente" non è valido
      @Test
	  public void tc_gac_1_5() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "a");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "nome utente" non è valido
      @Test
	  public void tc_gac_1_6() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario%%%%");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "password" non è valido
      @Test
	  public void tc_gac_1_7() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellacaio");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor ma il campo "conferma password" non è valido
      @Test
	  public void tc_gac_1_8() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellacaio");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertFalse(esito);
	  }
      
    //l'azienda prova a creare un nuovo account per un proprio tutor inserendo correttamente tutti i dati
      @Test
	  public void tc_gac_1_9() throws ServletException, IOException  {
    	   request.addParameter("email","microsoftofficial@tiscali.it");
    	   request.addParameter("password", "Xboxthebest"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("azienda", type);
    	   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Ambrosio");
		   request.addParameter("email", "ambrosiomario");
		   request.addParameter("password","bellaciao");
		   request.addParameter("confermaPassword","bellaciao");
		   servletCreaAccount.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("utenteCreato");
		   assertTrue(esito);
	  }
      
	}