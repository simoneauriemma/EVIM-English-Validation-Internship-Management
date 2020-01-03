package test_Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Azienda;

class AziendaClassTest {
private Azienda az;
	@BeforeEach
	void setUp() throws Exception {
		az=new Azienda(4,"AASDRF89","34586585","Giuseppe","ciaociao","microsoft@gamil.it","www.microsoft.it","via passanti","azienda molto famosa","435","1234","342","3435");
	}

	@Test
	void test_Get() {
		assertEquals(4,az.getID_Azienda());
		assertEquals("AASDRF89",az.getCF());
		assertEquals("34586585",az.getTelefono());
		assertEquals("Giuseppe",az.getNome());
		assertEquals("ciaociao",az.getPassword());
		assertEquals("microsoft@gamil.it",az.getEmail());
		assertEquals("www.microsoft.it",az.getSitoWeb());
		assertEquals("via passanti",az.getIndirizzo());
		assertEquals("azienda molto famosa",az.getDescrizione());
		assertEquals("435",az.getNumeroDipendenti());
		assertEquals("1234",az.getCodiceAteco());
		assertEquals("342",az.getIdReferente());
		assertEquals("3435",az.getIdConvenzione());
	}
	@Test
	void test_Post() {
		az.setID_Azinda(5);
		assertEquals(5,az.getID_Azienda());
		
		az.setCF("DMR34");
		assertEquals("DMR34",az.getCF());
		
		az.setTelefono("35356769");
		assertEquals("35356769",az.getTelefono());
		
		az.setNome("Francesco");
		assertEquals("Francesco",az.getNome());
		
		az.setEmail("pinco@gmail.it");
		assertEquals("pinco@gmail.it",az.getEmail());
		
		az.setSitoWeb("www.pinco.it");
		assertEquals("www.pinco.it",az.getSitoWeb());
		
		az.setIndirizzo("via vico");
		assertEquals("via vico",az.getIndirizzo());
		
		az.setDescrizione("pinco pallo");
		assertEquals("pinco pallo",az.getDescrizione());
		
		az.setNumeroDipendenti("585858");
		assertEquals("585858",az.getNumeroDipendenti());
		
		az.setCodiceAteco("6565");
		assertEquals("6565",az.getCodiceAteco());
		
		az.setIdReferente("417");
		assertEquals("417",az.getIdReferente());
		
		az.setIdConvenzione("7894");
		assertEquals("7894",az.getIdConvenzione());
		
		az.setPassword("ladro");
		assertEquals("ladro",az.getPassword());
		
	}

}
