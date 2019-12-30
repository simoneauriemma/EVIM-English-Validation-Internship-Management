package test;

	import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

	import controller.Login;

	import java.io.IOException;
	import javax.servlet.ServletException;

	import org.junit.Before;
	import org.junit.Test;
	import org.mockito.Mockito;
	import org.springframework.mock.web.MockHttpServletRequest;
	import org.springframework.mock.web.MockHttpServletResponse;

	public class LoginTest extends Mockito{
	
	  private Login servlet;
	  private MockHttpServletRequest request;
	  private MockHttpServletResponse response;

	  /**
	   * Before.
	   */
	  @Before
	  public void setUp() {
	    servlet = new Login();
	    request = new MockHttpServletRequest();
	    response = new MockHttpServletResponse();
	  }
	  
	  @Test
	  public void tc_ga1_1() throws ServletException, IOException  {
		   request.addParameter("email","fferrucci@unisa.it");
		   request.addParameter("password", "Ferrucci11");
		   servlet.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("pdcd", type);
	  }
	  
	  @Test
	  public void tc_ga1_2() throws ServletException, IOException  {
		   request.addParameter("email","mariogiorgio@unisa.it");
		   request.addParameter("password", "umpalumpa2");
		   servlet.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("tutoraccademico", type);
	  }
	  
	  @Test
	  public void tc_ga1_3() throws ServletException, IOException  {
		   request.addParameter("email","mconcetta@studenti.unisa.it");
		   request.addParameter("password", "mconcetta1998");
		   servlet.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("studente", type);
	  }
	  
	  @Test
	  public void tc_ga1_4() throws ServletException, IOException  {
		   request.addParameter("email","aino@tutor.unisa.it");
		   request.addParameter("password", "ciaociao1");
		   servlet.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("tutoraziendale", type);
	  }
	  
	  @Test
	  public void tc_ga1_5() throws ServletException, IOException  {
		   request.addParameter("email","microsoftofficial@tiscali.it");
		   request.addParameter("password", "Xboxthebest");
		   servlet.doPost(request, response);
		   String type=request.getSession().getAttribute("type").toString();
		   boolean log=(boolean) request.getAttribute("logged");
		   assertTrue(log);
		   assertEquals("azienda", type);
	  }
	  
	  @Test
	  public void tc_ga1_6() throws ServletException, IOException  {
		   request.addParameter("email","aamicrosoftofficial@tiscali.it");
		   request.addParameter("password", "Xboxthebest");
		   servlet.doPost(request, response);
		   
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }

	  @Test
	  public void tc_ga1_7() throws ServletException, IOException  {
		   request.addParameter("email","aaaino@tutor.unisa.it");
		   request.addParameter("password", "ciaociao1");
		   servlet.doPost(request, response);
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }
	  
	  @Test
	  public void tc_ga1_8() throws ServletException, IOException  {
		   request.addParameter("email","afferrucci@unisa.it");
		   request.addParameter("password", "Ferrucci11");
		   servlet.doPost(request, response);
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }
	  
	  @Test
	  public void tc_ga1_9() throws ServletException, IOException  {
		   request.addParameter("email","amariogiorgio@unisa.it");
		   request.addParameter("password", "umpalumpa2");
		   servlet.doPost(request, response);
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }
	  
	  @Test
	  public void tc_ga1_10() throws ServletException, IOException  {
		   request.addParameter("email");
		   request.addParameter("password", "mconcetta1998");
		   servlet.doPost(request, response);
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }
	  
	  @Test
	  public void tc_ga1_11() throws ServletException, IOException  {
		  request.addParameter("email","mmconcetta@studenti.unisa.it");
		   request.addParameter("password", "umpalumpa2");
		   servlet.doPost(request, response);
		   boolean type=(boolean) request.getAttribute("logged");
		   assertFalse(type);
	  }
	  
	  
}
