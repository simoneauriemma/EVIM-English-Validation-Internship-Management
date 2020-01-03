package test_GA;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import controller.Login;
import controller.Logout;

	import java.io.IOException;
	import javax.servlet.ServletException;

	import org.junit.Before;
	import org.junit.Test;
	import org.mockito.Mockito;
	import org.springframework.mock.web.MockHttpServletRequest;
	import org.springframework.mock.web.MockHttpServletResponse;

	public class LogoutTest extends Mockito{
	
	  private Logout servlet;
	  private Login servletLogin;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
		servletLogin = new Login();
	    servlet = new Logout();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void tc_ga2_2() throws ServletException, IOException  {
		  request.addParameter("email","fferrucci@unisa.it");
		   request.addParameter("password", "Ferrucci11");
		   servletLogin.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("pdcd", type);
		   servlet.doGet(request, response);
		   assertNull(request.getAttribute("logged"));
	  }
	  }