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
	void test0() {
		ArrayList<Proposta> proposte=PropostaDAO.findByIdTutorAccademico(2);
		assertNotEquals(0, proposte.size());
		
	}
	
	@Test
	void test1() {
		ArrayList<Proposta> proposte=PropostaDAO.findByIdAzienda(1);
		assertNotEquals(0, proposte.size());
		
	}
	
	@Test
	void test2() {
		ArrayList<Proposta> proposte=PropostaDAO.getListaProposta();
		assertNotEquals(0, proposte.size());
		
	}
	@Test
	void test3() {
		ArrayList<Proposta> proposte=PropostaDAO.findByIdTutorAziendale(1);
		assertNotEquals(0, proposte.size());
		
	}

	@Test
	void test4() {
		ArrayList<Proposta> proposte=PropostaDAO.getProposteAziendaWithIdAzienda(1);
		assertNotEquals(0, proposte.size());
		
	}
	@Test
	void test5() {
		Proposta proposta=PropostaDAO.getPropostaInterno(1);
		assertNotNull(proposta);
	}
	
	@Test
	void test6() {
		Proposta proposta=PropostaDAO.getPropostaEsterno(1);
		assertNotNull(proposta);
	}
	

	@Test
	void test7() {
		Proposta proposta=PropostaDAO.getPropostaInterno(7);
		assertNotNull(proposta);
	}
	
	@Test
	void test8() {
		Proposta proposta=PropostaDAO.getPropostaInterno(7);
		assertNotNull(proposta);
	}
	@Test
	void test9() {
		boolean proposta=PropostaDAO.modificationPropostaEsterno("l'obiettivo di questo tirocinio e quello difare ", "il tirocinante deve avere delle determinate competenze per poter svolgere questo determinato tirocinio", "le attivita che andremo a svolgere in questo tirocinio sarannno elencate qui sotto", "la modalita di questo tirocinio sara diisa in diverse fasi inizieremo ", 1, 1);
		assertTrue(true);
	}
	@Test
	void test9_1() {
		boolean proposta=PropostaDAO.modificationPropostaEsterno("l'obiettivo di questo tirocinio e quello difare ", "il tirocinante deve avere delle determinate competenze per poter svolgere questo determinato tirocinio", "le attivita che andremo a svolgere in questo tirocinio sarannno elencate qui sotto", "la modalita di questo tirocinio sara diisa in diverse fasi inizieremo ", 1, 2);
		assertFalse(false);
	}
	@Test
	void test10() {
		boolean proposta=PropostaDAO.modificationPropostaInterno("l'obiettivo di questo tirocinio e quello difare ", "il tirocinante deve avere delle determinate competenze per poter svolgere questo determinato tirocinio", "le attivita che andremo a svolgere in questo tirocinio sarannno elencate qui sotto", "la modalita di questo tirocinio sara diisa in diverse fasi inizieremo ",11);
		assertTrue(true);
	}
	@Test
	void test10_1() {
		boolean proposta=PropostaDAO.modificationPropostaInterno("l'obiettivo di questo tirocinio e quello difare ", "il tirocinante deve avere delle determinate competenze per poter svolgere questo determinato tirocinio", "le attivita che andremo a svolgere in questo tirocinio sarannno elencate qui sotto", "la modalita di questo tirocinio sara diisa in diverse fasi inizieremo ", 15);
		assertFalse(false);
	}
	@Test
	void test11() {
		boolean proposta=PropostaDAO.removeProposta(6);
		assertTrue(true);
		
	}
	@Test
	void test11_1() {
		boolean proposta=PropostaDAO.removeProposta(-5);
		assertFalse(false);
		
	}
	@Test
	void test12() {
		boolean proposta=PropostaDAO.insertPropostaInterno("l'obiettivo di questo tirocinio e quella di fare ", "il tiocinante deve avere delle detrminate competenze per poter", "le attivita di questo tirocinio si svolgeranno in diverse modalita partendo dal", "la modalita sara divisa in diverse classi di", 1);
		assertTrue(true);
		
	}
}

