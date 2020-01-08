package test_Model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TutorAziendale;
import model.TutorAziendaleDAO;





class TuttorAziendaleDAOTest {

	@BeforeEach
	void setUp() throws Exception {
	}
	@Test
	void test0() {
	 TutorAziendale A=TutorAziendaleDAO.findbyID();
		assertNotEquals(0, A);
		
	}

		
		@Test
		void test1() {
		 int idProp=TutorAziendaleDAO.doRetriveByIDProposta(1);
			assertNotEquals(0, idProp);
			
		}
	}

