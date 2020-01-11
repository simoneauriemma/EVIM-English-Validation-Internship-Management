package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Relazione;
import model.RelazioneDAO;
import model.User;

class RelazioneDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void test1 (){
	boolean inserire=RelazioneDAO.insertRelezione(1, "edoardo93@studenti.unisa.it", "la descrrizione dipende da diverse attivita svolte all'interno del tirocinio", "approvato");
	assertTrue(inserire);
}
	/*@Test
	void test1_1 (){
	boolean inserire=RelazioneDAO.insertRelezione(1, null, "la descrrizione dipende da diverse attivita svolte all'interno del tirocinio", "approvato");
	assertFalse(inserire);
}*/
	@Test
	void test2 (){
	ArrayList<User> inserire=RelazioneDAO.doRetriveStudenti(1);
	assertNotNull(inserire);
}
	/*@Test
	void test2_1(){
	ArrayList<User> inserire=RelazioneDAO.doRetriveStudenti(-1);
	assertNotNull(inserire);
}*/
	@Test
	void test3 (){
	Relazione r=RelazioneDAO.doRetriveRelazionefromId(8);
	assertNotNull(r);
}
	
	
	
}