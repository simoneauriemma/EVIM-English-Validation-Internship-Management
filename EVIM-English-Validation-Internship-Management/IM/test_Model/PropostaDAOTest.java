package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Proposta;
import model.PropostaDAO;

class PropostaDAOTest {

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void test() {
		ArrayList<Proposta> proposte=PropostaDAO.findByIdTutorAccademico(2);
		assertNotEquals(0, proposte.size());
		
	}

}
