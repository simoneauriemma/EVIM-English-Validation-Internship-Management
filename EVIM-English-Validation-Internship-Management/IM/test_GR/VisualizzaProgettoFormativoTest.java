package test_GR;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.GestioneRichiesta.VisualizzaProgettoFormativo;
import controller.GestioneAutenticazione.Login;
import model.PDFProgettoFormativo;
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

	public class VisualizzaProgettoFormativoTest extends Mockito{
	
	  private VisualizzaProgettoFormativo servletVisualizza;
	  private Login servletLogin;
	  private PDFProgettoFormativo servletPDF;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
		servletVisualizza = new VisualizzaProgettoFormativo();
	    servletLogin = new Login();
	    servletPDF = new PDFProgettoFormativo();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	//uno studente della triennale intende visualizzare il PF relativo a un tirocinio interno senza loggarsi
      @Test
	  public void tc_gr_5_1() throws ServletException, IOException  {
    	   request.addParameter("idTirocinio", "2");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
	
	//uno studente della triennale intende visualizzare il PF relativo a un tirocinio interno da 6
      @Test
	  public void tc_gr_5_2() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "2");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio interno da 11
      @Test
	  public void tc_gr_5_3() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "7");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio esterno da 17
      @Test
	  public void tc_gr_5_4() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "4");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio esterno da 23
      @Test
	  public void tc_gr_5_5() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "3");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio esterno da 6
      @Test
	  public void tc_gr_5_6() throws ServletException, IOException  {
    	   request.addParameter("email","edoardo93@studenti.unisa.it");
    	   request.addParameter("password", "edoardo93"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "3");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio esterno da 12
      @Test
	  public void tc_gr_5_7() throws ServletException, IOException  {
    	   request.addParameter("email","attiliodellag1@studenti.unisa.it");
    	   request.addParameter("password", "attilio1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "2");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
     
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio da 6
      @Test
	  public void tc_gr_5_8() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "5");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio da 6
      @Test
	  public void tc_gr_5_9() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "1");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio da 6
      @Test
	  public void tc_gr_5_10() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "6");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio da 6
      @Test
	  public void tc_gr_5_11() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "7");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio da 6
      @Test
	  public void tc_gr_5_12() throws ServletException, IOException  {
    	   request.addParameter("email","attiliodellag1@studenti.unisa.it");
    	   request.addParameter("password", "attilio1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "7");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
      /*
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio interno da 6
      @Test
	  public void tc_gr_5_7() throws ServletException, IOException  {
    	   request.addParameter("email","mconcetta@studenti.unisa.it");
    	   request.addParameter("password", "mconcetta1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "3");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio interno da 17
      @Test
	  public void tc_gr_5_8() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "4");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio interno (che non può fare)
      @Test
	  public void tc_gr_5_9() throws ServletException, IOException  {
    	   request.addParameter("email","edoardo93@studenti.unisa.it");
    	   request.addParameter("password", "edoardo93"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "4");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio esterno da 6 della magistrale
      @Test
	  public void tc_gr_5_10() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "3");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio interno (che non può fare)
      @Test
	  public void tc_gr_5_11() throws ServletException, IOException  {
    	   request.addParameter("email","edoardo93@studenti.unisa.it");
    	   request.addParameter("password", "edoardo93"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "5");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della magistrale intende visualizzare il PF relativo a un tirocinio interno (che non può fare)
      @Test
	  public void tc_gr_5_12() throws ServletException, IOException  {
    	   request.addParameter("email","attiliodellag1@studenti.unisa.it");
    	   request.addParameter("password", "attilio1"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "6");
    	   request.addParameter("tirocinio", "interno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio esterno da 11
      @Test
	  public void tc_gr_5_13() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "4");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio esterno da 17
      @Test
	  public void tc_gr_5_14() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "5");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
	  }
      
    //uno studente della triennale intende visualizzare il PF relativo a un tirocinio esterno da 23
      @Test
	  public void tc_gr_5_15() throws ServletException, IOException  {
    	   request.addParameter("email","simonagrieco@studenti.unisa.it");
    	   request.addParameter("password", "grieco1998"); 
    	   servletLogin.doPost(request, response);
    	   String type = request.getSession().getAttribute("type").toString();
    	   boolean log = (boolean) request.getAttribute("logged");  
    	   assertTrue(log);
    	   assertEquals("studente", type);
    	   request.addParameter("idTirocinio", "6");
    	   request.addParameter("tirocinio", "esterno");
    	   servletVisualizza.doPost(request, response);
      }*/
    
      
      /* utente loggato e non
       * id del tirocinio (se ci sta oppure no)
       * tirocinio interno || esterno
       * 
       * cfu per interno (triennale e magistrale) ed esterno (solo esterno)
       * 
       * */
       
      
	}