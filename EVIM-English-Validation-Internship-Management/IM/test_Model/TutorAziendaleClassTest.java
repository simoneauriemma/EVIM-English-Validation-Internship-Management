package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TutorAziendale;

class TutorAziendaleClassTest {
	private TutorAziendale tutaz;
	@BeforeEach
	void setUp() throws Exception {
	tutaz=new TutorAziendale(1,2,"Michele","Duraccio","michele@tutor.unisa.it","ciaociao","3478323225");
	}
 
	@Test
	void test_Get() {
		assertEquals(1,tutaz.getId());  
		assertEquals(2,tutaz.getIdAzienda());
		assertEquals("Michele",tutaz.getNome());
		assertEquals("Duraccio",tutaz.getCognome());
		assertEquals("michele@tutor.unisa.it",tutaz.getEmail());
		assertEquals("ciaociao",tutaz.getPassword());
		assertEquals("3478323225",tutaz.getTelefono());
		
	}
	@Test
	void test_Set() {
		tutaz.setId(3);
		assertEquals(3,tutaz.getId());
		
		tutaz.setIdAzienda(6);
		assertEquals(6,tutaz.getIdAzienda());
		
		tutaz.setNome("Domenico");
		assertEquals("Domenico",tutaz.getNome());
		
		tutaz.setCognome("Rossi");
		assertEquals("Rossi",tutaz.getCognome());
		
		tutaz.setEmail("Domenico@tutor.unisa.it");
		assertEquals("Domenico@tutor.unisa.it",tutaz.getEmail());
		
		tutaz.setPassword("ciao");
		assertEquals("ciao", tutaz.getPassword());

		tutaz.setTelefono("3485625447");
		assertEquals("3485625447",tutaz.getTelefono());
	}

}
