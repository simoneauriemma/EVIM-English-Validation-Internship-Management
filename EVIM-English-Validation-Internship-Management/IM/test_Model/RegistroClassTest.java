package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Registro;

class RegistroClassTest {
private Registro r;

	@BeforeEach
	void setUp() throws Exception {
		r=new Registro(1,3,1,3,"completo");
	}

	@Test
	void test_Get() {
		assertEquals(1, r.getID_Registro());
		assertEquals(3, r.getTirocinioEsterno());	
		assertEquals(1, r.getFirmaResponsabile());
		assertEquals(3, r.getFirmaTutorAccamico());
		assertEquals("completo", r.getStatus());
		}
	@Test
	void test_Set() {
		r.setID_Registro(2);
		assertEquals(2, r.getID_Registro());
		
		r.setTirocinioEsterno(4);
		assertEquals(4, r.getTirocinioEsterno());
		
		r.setFirmaResponsabile(5);
		assertEquals(5, r.getFirmaResponsabile());
		
		r.setFirmaTutorAccamico(6);
		assertEquals(6, r.getFirmaTutorAccamico());
		
		r.setStatus("in attesa");
		assertEquals("in attesa", r.getStatus());
	}

}
