package test_GR;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneRichiesta.ValutareRichieste;
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

	public class RifiutaRichiestaTest extends Mockito{
	
	  private ValutareRichieste servletRifiuta;
	  private Login servletLogin;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servletRifiuta = new ValutareRichieste();
	    servletLogin = new Login();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	//un tutor accademico intende rifiutare una richiesta
      @Test
	  public void tc_gr_4_1() throws ServletException, IOException  {
    	   request.addParameter("email","alberto@unisa.it");
    	   request.addParameter("password", "architettura"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraccademico", type);
    	   request.addParameter("confermato", "no");
    	   servletRifiuta.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("risposta");
		   assertTrue(esito);
	  }
      
    //un tutor aziendale intende rifiutare una richiesta
      @Test
	  public void tc_gr_4_2() throws ServletException, IOException  {
    	   request.addParameter("email","uughi@tutor.unisa.it");
    	   request.addParameter("password", "ciaociao1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraziendale", type);
    	   request.addParameter("confermato", "no");
    	   servletRifiuta.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("risposta");
		   assertTrue(esito);
	  }
      
	}