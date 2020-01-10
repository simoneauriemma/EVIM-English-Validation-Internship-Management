package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.User;
import model.UserDAO;

class UserDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {

	User login=UserDAO.doRetrieveByLoginData(0, "edoardo93av@studenti.unisa.it", "edoardo93@");
	assertNotNull(login);

	}
	@Test
	void test1_1() {

	User login=UserDAO.doRetrieveByLoginData(0, null , "edoardo93@");
	assertNull(login);

	}
	@Test
	void test2() {
	boolean login=UserDAO.insertNewUser(new User("rossi58@studenti.unisa.it", "Domenico", "Rossi", 'M', "ciaociao", 0, "infromatica", "sarno", "08/02/1997", "Sarno", "nuova", "3488956558", "5100005"));
	assertTrue(login);

	}
	
/*	void test2_1() {
		boolean login=UserDAO.insertNewUser(new User("simonagrieco@studenti.unisa.it", "Domenico", "Rossi", 'M', "ciaociao", 0, "infromatica", "sarno", "08/02/1997", "Sarno", "nuova", "3488956558", "5100005"));
		assertFalse(login);

		}*/
	
	@Test
	void test3() {

	User login=UserDAO.getStudenteWithEmail("simonagrieco@studenti.unisa.it");
	assertNotNull(login);

}
	@Test
	void test3_1() {

	User login=UserDAO.getStudenteWithEmail("simo@studenti.unisa.it");
	assertNull(login);

}
	
}
