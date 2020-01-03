package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Registro;
import model.TutorAziendale;

class TutorAziendaleClassTest {
	private TutorAziendale TutAz;
	@BeforeEach
	void setUp() throws Exception {
	TutAz=new TutorAziendale(1,2,"Michele","Duraccio","michele@tutor.unisa.it","ciaociao","3478323225");
	}

	@Test
	void test_Get() {
		assertEquals(1,TutAz.getId());
		assertEquals(2,TutAz.getIdAzienda());
		assertEquals("Michele",TutAz.getNome());
		assertEquals("Duraccio",TutAz.getCognome());
		assertEquals("michele@tutor.unisa.it",TutAz.getEmail());
		assertEquals("ciaociao",TutAz.getPassword());
		assertEquals("3478323225",TutAz.getTelefono());
		
	}
	@Test
	void test_Set() {
		TutAz.setId(3);
		assertEquals(3,TutAz.getId());
		
		TutAz.setIdAzienda(6);
		assertEquals(6,TutAz.getIdAzienda());
		
		TutAz.setNome("Domenico");
		assertEquals("Domenico",TutAz.getNome());
		
		TutAz.setCognome("Rossi");
		assertEquals("Rossi",TutAz.getCognome());
		
		TutAz.setEmail("Domenico@tutor.unisa.it");
		assertEquals("Domenico@tutor.unisa.it",TutAz.getEmail());
		
		TutAz.setPassword("ciao");
		assertEquals("ciao", TutAz.getPassword());

		TutAz.setTelefono("3485625447");
		assertEquals("3485625447",TutAz.getTelefono());
	}

}
