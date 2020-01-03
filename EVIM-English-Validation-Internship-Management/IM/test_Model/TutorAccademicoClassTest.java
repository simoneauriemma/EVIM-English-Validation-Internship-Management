package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TutorAccademico;


class TutorAccademicoClassTest {
	private TutorAccademico TutAc;
	@BeforeEach
	void setUp() throws Exception {
		TutAc=new TutorAccademico(1,"Andrea","Bianchi","ciao123","M","Adrea@unisa.it");
	}



	@Test
	void test_Get() {
		assertEquals(1,TutAc.getIdTutorAccademico());
		assertEquals("Andrea",TutAc.getNome());
		assertEquals("Bianchi",TutAc.getCognome());
		assertEquals("ciao123",TutAc.getPassword());
		assertEquals("M",TutAc.getSesso());
		assertEquals("Adrea@unisa.it",TutAc.getEmail());
	}
	@Test
	void test_Set() {
		TutAc.setIdTutorAccademico(5);
		assertEquals(5,TutAc.getIdTutorAccademico());
		
		TutAc.setNome("Giacomo");
		assertEquals("Giacomo", TutAc.getNome());
		
		TutAc.setCognome("Verdi");
		assertEquals("Verdi", TutAc.getCognome()); 
		
		TutAc.setPassword("apriti");
		assertEquals("apriti", TutAc.getPassword());
		
		TutAc.setSesso("F");
		assertEquals("F", TutAc.getSesso());
		
		TutAc.setEmail("Giacomo@unisa.it");
		assertEquals("Giacomo@unisa.it", TutAc.getEmail());
		
		
		
	}
}
