package test_GR;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Test suite che permette di testare l'intero modulo GR

@RunWith(Suite.class)
@SuiteClasses({ CreaRichiestaTirocinioTest.class, ValutareRichiesteTest.class, VisualizzaProgettoFormativoTest.class,
	VisualizzaRichiestaTest.class })
public class testGR {

}
