package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.QuestionariDAO;
import model.Questionario_s;
import model.Questionario_t;


class QuestionarioDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}

@Test
	void test1() {
	
	/*
	 * int id_questionario, String email, int assistenza_disp, int informazioni, int servizi,
			int assistenza, int logistica, int ambiente, int durata, int mansioni, int attivita, int formazione,
			int possibilita, int valutazione, int competenze
	 */
	 boolean crea=QuestionariDAO.insertQuestionarioS(new Questionario_s(1,"uughi@tutor.unisa.it", 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
		assertTrue(crea);
		
	}
@Test
void test2() {

boolean crea=QuestionariDAO.insertQuestionarioT(new Questionario_t(1,"uughi@tutor.unisa.it", 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
assertTrue(crea);

}
}
