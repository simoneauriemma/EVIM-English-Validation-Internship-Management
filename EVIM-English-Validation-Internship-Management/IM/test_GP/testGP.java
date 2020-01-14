package test_GP;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Test suite che permette di testare l'intero modulo GP

@RunWith(Suite.class)
@SuiteClasses({ CreaPropostaTest.class, ModificaPropostaTest.class, RimuoviPropostaTest.class, 
	VisualizzaCreaPropostaTest.class, VisualizzaModificaPropostaTest.class, VisualizzaPropostaTest.class })
public class testGP {

}
