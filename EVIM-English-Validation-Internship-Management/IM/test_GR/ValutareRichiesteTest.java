package test_GR;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
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

	public class ValutareRichiesteTest extends Mockito{
	
	  private ValutareRichieste servletValuta;
	  private Login servletLogin;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servletValuta = new ValutareRichieste();
	    servletLogin = new Login();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	//un tutor accademico intende rifiutare una richiesta
      @Test
	  public void tc_gr_4_1() throws ServletException, IOException  {
    	   request.addParameter("email","mariogiorgio@unisa.it");
    	   request.addParameter("password", "umpalumpa2"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraccademico", type);
    	   request.addParameter("confermato", "no");
    	   servletValuta.doPost(request, response);
		   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
    //un tutor accademico intende approvare una richiesta
      @Test
	  public void tc_gr_4_2() throws ServletException, IOException  {
    	   request.addParameter("email","mariogiorgio@unisa.it");
    	   request.addParameter("password", "umpalumpa2"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraccademico", type);
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
		   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
    //un tutor aziendale intende rifiutare una richiesta
      @Test
	  public void tc_gr_4_3() throws ServletException, IOException  {
    	   request.addParameter("email","uughi@tutor.unisa.it");
    	   request.addParameter("password", "ciaociao1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraziendale", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("confermato", "no");
    	   request.addParameter("azienda", "azienda");
    	   servletValuta.doPost(request, response);
		   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
    //un tutor aziendale intende approvare una richiesta
      @Test
	  public void tc_gr_4_4() throws ServletException, IOException  {
    	   request.addParameter("email","uughi@tutor.unisa.it");
    	   request.addParameter("password", "ciaociao1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraziendale", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "azienda");
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
    	   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
    //un tutor aziendale non loggato intende approvare una richiesta
      @Test
	  public void tc_gr_4_5() throws ServletException, IOException  {
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "azienda");
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
    	   boolean log = (boolean) request.getAttribute("login");  
    	   assertFalse(log);
	  }
      
    //si trova loggato uno studente
      @Test
	  public void tc_gr_4_6() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "azienda");
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
    	   boolean log1 = (boolean) request.getAttribute("login");  
    	   assertFalse(log1);
	  }
      
    //un tutor aziendale intende approvare una richiesta (dato azienda passato come errato)
      @Test
	  public void tc_gr_4_7() throws ServletException, IOException  {
    	   request.addParameter("email","uughi@tutor.unisa.it");
    	   request.addParameter("password", "ciaociao1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraziendale", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "zienda");
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
    	   boolean log1 = (boolean) request.getAttribute("login");  
    	   assertFalse(log1);
	  }
      
    //un tutor aziendale intende approvare una richiesta (dato azienda passato come errato)
      @Test
	  public void tc_gr_4_8() throws ServletException, IOException  {
    	   request.addParameter("email","mariogiorgio@unisa.it");
    	   request.addParameter("password", "umpalumpa2"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraccademico", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "azienda");
    	   request.addParameter("confermato", "si");
    	   servletValuta.doPost(request, response);
    	   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
    //un tutor aziendale intende approvare una richiesta (dato azienda passato come errato)
      @Test
	  public void tc_gr_4_9() throws ServletException, IOException  {
    	   request.addParameter("email","mariogiorgio@unisa.it");
    	   request.addParameter("password", "umpalumpa2"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("tutoraccademico", type);
    	   request.addParameter("id", "2");
    	   request.addParameter("azienda", "azienda");
    	   request.addParameter("confermato", "no");
    	   servletValuta.doPost(request, response);
    	   int esito = (int) request.getAttribute("esito");
		   assertNotEquals(1, esito);
	  }
      
	}