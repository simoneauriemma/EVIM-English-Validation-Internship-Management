package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TutorAccademico;
import model.TutorAccademicoDAO;





class TutorAccademicoDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	void test0() {
	 ArrayList<TutorAccademico> listaTutor=TutorAccademicoDAO.doRetrieveAll();
		assertNotEquals(0, listaTutor);
		
	}
	@Test
	void test1() {
	 TutorAccademico utente=TutorAccademicoDAO.doRetrieveByLoginData("mariogiorgio@unisa.it", "umpalumpa2");
		assertNotEquals(0, utente);
		
	}
	@Test
	void test1_1() {
	 TutorAccademico utente=TutorAccademicoDAO.doRetrieveByLoginData("adhvbja", "umpalumpa2");
		assertNull(utente);
		
	}
	@Test
	void test2() {
	 boolean crea=TutorAccademicoDAO.insertNewTutorAccademico("Michele", "Duraccio", "ciaociao", "M", "michele@unisa.it");
		assertTrue(crea);
		
	}
	
	/*@Test
	void test2_1() {
	 boolean crea=TutorAccademicoDAO.insertNewTutorAccademico("Michele", "Duraccio", "ciaociao", "M", "michele@unisa.itmichele@unisa.itmichele@unisa.itmichele@unisa.itmichele@unisa.itmichele@unisa.it");
	 assertFalse(crea);
	}*/
}

