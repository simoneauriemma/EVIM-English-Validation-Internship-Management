package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Azienda;
import model.AziendaDAO;

class AziendaDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test1() {
		ArrayList<Azienda> azienda=AziendaDAO.doRetriveAll();
		assertNotEquals(0, azienda.size());
		
		
	}
	@Test
	void test2() {
		Azienda azienda=AziendaDAO.doRetrieveByLoginData("microsoftofficial@tiscali.it", "Xboxthebest");
		assertNotEquals(0, azienda);
		}
	@Test
	void test2_1() {
		Azienda azienda=AziendaDAO.doRetrieveByLoginData("microso@tiscali.it", "Xboxthebest");
		assertNotEquals(0, azienda);
		} 
}
