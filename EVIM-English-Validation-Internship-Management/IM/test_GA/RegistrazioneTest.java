package test_GA;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

	import controller.Registrazione;
import model.DriverManagerConnectionPool;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;

	import org.junit.Before;
	import org.junit.Test;
    import org.junit.jupiter.api.AfterAll;
    import org.junit.jupiter.api.AfterEach;
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
	  
	//un tutor accademico prova a registrarsi inserendo un valore non valido nel campo "email"
      @Test
	  public void tc_ga4_1() throws ServletException, IOException  {
		   request.addParameter("email","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaalberto@unisa.it");
		   request.addParameter("nome","Alberto");
		   request.addParameter("cognome","Negro");
		   request.addParameter("password","architettura");
		   request.addParameter("cpassword","architettura");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
	  
	//un tutor accademico prova a registrarsi senza inserire il campo "cognome"
      @Test
	  public void tc_ga4_2() throws ServletException, IOException  {
		   request.addParameter("email","alberto@unisa.it");
		   request.addParameter("nome","Alberto");
		   request.addParameter("cognome","");
		   request.addParameter("password","architettura");
		   request.addParameter("cpassword","architettura");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
	  
	//un tutor accademico prova a registrarsi senza inserire il campo "nome"
      @Test
	  public void tc_ga4_3() throws ServletException, IOException  {
		   request.addParameter("email","alberto@unisa.it");
		   request.addParameter("nome","");
		   request.addParameter("cognome","Negro");
		   request.addParameter("password","architettura");
		   request.addParameter("cpassword","architettura");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }

	//un tutor accademico prova a registrarsi inserendo correttamente tutti i campi
      @Test
	  public void tc_ga4_4() throws ServletException, IOException  {
		   request.addParameter("email","alberto@unisa.it");
		   request.addParameter("nome","Alberto");
		   request.addParameter("cognome","Negro");
		   request.addParameter("password","archi");
		   request.addParameter("cpassword","archi");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
	  
    //un tutor accademico prova a registrarsi senza confermare correttamente la password
      @Test
	  public void tc_ga4_5() throws ServletException, IOException  {
		   request.addParameter("email","alberto@unisa.it");
		   request.addParameter("nome","Alberto");
		   request.addParameter("cognome","Negro");
		   request.addParameter("password","architettura");
		   request.addParameter("cpassword","archituttera");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //un tutor accademico prova a registrarsi inserendo correttamente tutti i campi
      @Test
	  public void tc_ga4_6() throws ServletException, IOException  {
		   request.addParameter("email","alberto@unisa.it");
		   request.addParameter("nome","Alberto");
		   request.addParameter("cognome","Negro");
		   request.addParameter("password","architettura");
		   request.addParameter("cpassword","architettura");
		   request.addParameter("sesso","M");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertTrue(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire il campo "nome"
      @Test
	  public void tc_ga4_7() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire il campo "cognome"
      @Test
	  public void tc_ga4_8() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi scegliendo una password troppo corta
      @Test
	  public void tc_ga4_9() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apriti");
		   request.addParameter("cpassword","apriti");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire il campo "conferma password"
      @Test
	  public void tc_ga4_10() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire due password corrispondenti
      @Test
	  public void tc_ga4_11() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisasemo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire il campo "email"
      @Test
	  public void tc_ga4_12() throws ServletException, IOException  {
		   request.addParameter("email","");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi inserendo un valore non valido nel campo "email"
      @Test
	  public void tc_ga4_13() throws ServletException, IOException  {
		   request.addParameter("email","rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi inserendo la data di nascita in un formato non corretto
      //l'unico formato accettato è infatti AAAA-MM-GG
      @Test
	  public void tc_ga4_14() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","18/03/1993");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
	  
	//uno studente prova a registrarsi senza inserire il campo "indirizzo"
      @Test
	  public void tc_ga4_15() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
	//uno studente prova a registrarsi ma inserisce nel campo 
    //"indirizzo" una sequenza di caratteri maggiore di 100
      @Test
   	  public void tc_ga4_16() throws ServletException, IOException  {
   		   request.addParameter("email","r.mario@studenti.unisa.it");
   		   request.addParameter("nome","Mario");
   		   request.addParameter("cognome","Rossi");
   		   request.addParameter("password","apritisesamo");
   		   request.addParameter("cpassword","apritisesamo");
   		   request.addParameter("sesso","M");
   		   request.addParameter("corso","triennale");
   		   request.addParameter("matricola","0512105865");
   		   request.addParameter("data","1993-05-18");
   		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
   		   request.addParameter("indirizzo","Viaasdrgjslodfgncsdliopsdjfkasdrgjslodfgncsdliopsdjfkkkasdrgjslodfgncsdliopsdjfkasdrgjslodfgncsdliopsdjfk");
   		   request.addParameter("telefono","3478356882");
   		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
   		   servlet.doPost(request, response);
   		   boolean esito = (boolean) request.getAttribute("result");
   		   assertFalse(esito);
   	  }
      
    //uno studente prova a registrarsi ma inserisce nel campo 
    //"indirizzo" dei caratteri non consentiti
      @Test
	  public void tc_ga4_17() throws ServletException, IOException  {
		   request.addParameter("email","r.mario@studenti.unisa.it");
		   request.addParameter("nome","Mario");
		   request.addParameter("cognome","Rossi");
		   request.addParameter("password","apritisesamo");
		   request.addParameter("cpassword","apritisesamo");
		   request.addParameter("sesso","M");
		   request.addParameter("corso","triennale");
		   request.addParameter("matricola","0512105865");
		   request.addParameter("data","1993-05-18");
		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
		   request.addParameter("indirizzo","Via Vico dei sogni 35 £%");
		   request.addParameter("telefono","3478356882");
		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
		   servlet.doPost(request, response);
		   boolean esito = (boolean) request.getAttribute("result");
		   assertFalse(esito);
	  }
      
    //uno studente prova a registrarsi senza inserire il campo "telefono"
        @Test
  	  public void tc_ga4_18() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");;
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi ma il campo "telefono" 
      //inserito supera la lunghezza massima consentita
        @Test
  	  public void tc_ga4_19() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","423423523532523532235757576");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");;
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "telefono"
        @Test
  	  public void tc_ga4_20() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","");
  	   	   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "matricola"
      //(la stringa deve essere di 10 caratteri numerici)
        @Test
  	  public void tc_ga4_21() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","05865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "matricola"
      //(la stringa deve essere di 10 caratteri numerici)
        @Test
  	  public void tc_ga4_22() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","051210586523423");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "matricola"
      //(la stringa deve essere di 10 caratteri numerici)
        @Test
  	  public void tc_ga4_23() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","=512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza selezionare una provincia di nascita
        @Test
  	  public void tc_ga4_24() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  	       request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "comune di nascita"
        @Test
  	  public void tc_ga4_25() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "comune di nascita"
        @Test
  	  public void tc_ga4_26() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino provincia di Avellino situata in Campania in Italia");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "luogonascita"
        @Test
  	  public void tc_ga4_27() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","$vellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "data di nascita"
        @Test
  	  public void tc_ga4_28() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "provincia di residenza"
        @Test
  	  public void tc_ga4_29() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "comune di residenza"
        @Test
  	  public void tc_ga4_30() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo un valore non valido nel campo "comune di residenza"
        @Test
  	  public void tc_ga4_31() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino provincia di Avellino situata in Campania in Italia");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
        //uno studente prova a registrarsi inserendo un valore non valido nel campo "comune di residenza"
        @Test
  	  public void tc_ga4_32() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","$vellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi senza inserire il campo "corso di laurea"
        @Test
  	  public void tc_ga4_33() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertFalse(esito);
  	  }
        
      //uno studente prova a registrarsi inserendo correttamente tutti i campi
        @Test
  	  public void tc_ga4_34() throws ServletException, IOException  {
  		   request.addParameter("email","r.mario@studenti.unisa.it");
  		   request.addParameter("nome","Mario");
  		   request.addParameter("cognome","Rossi");
  		   request.addParameter("password","apritisesamo");
  		   request.addParameter("cpassword","apritisesamo");
  		   request.addParameter("sesso","M");
  		   request.addParameter("corso","triennale");
  		   request.addParameter("matricola","0512105865");
  		   request.addParameter("data","1993-05-18");
  		   request.addParameter("comuneN","Avellino");
		   request.addParameter("provinciaN","AV");
  		   request.addParameter("indirizzo","Via Vico dei sogni 35");
  		   request.addParameter("telefono","3478356882");
  		   request.addParameter("comuneR","Avellino");
		   request.addParameter("provinciaR","AV");
  		   servlet.doPost(request, response);
  		   boolean esito = (boolean) request.getAttribute("result");
  		   assertTrue(esito);
  	  }     
}