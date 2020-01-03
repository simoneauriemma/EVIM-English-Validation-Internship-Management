package test_GA;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

	import controller.Registrazione;

	import java.io.IOException;
	import javax.servlet.ServletException;

	import org.junit.Before;
	import org.junit.Test;
	import org.mockito.Mockito;
	import org.springframework.mock.web.MockHttpServletRequest;
	import org.springframework.mock.web.MockHttpServletResponse;

	public class RegistrazioneTest extends Mockito{
	
	  private Registrazione servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new Registrazione();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }


	  public void tc_ga4_1() throws ServletException, IOException  {
		   request.addParameter("email","fferrucci@unisa.it");
		   request.addParameter("nome","Michele");
		   request.addParameter("cognome","Duraccio");
		   request.addParameter("password","spostati");
		   request.addParameter("cpassword","spostati");
		   request.addParameter("comune","Fiscino");
		   request.addParameter("provincia","Salerno");
		   request.addParameter("indirizzo","Via vico dei sogno");
		   request.addParameter("telefono","3489565885");
		   
		   
	  }
	}