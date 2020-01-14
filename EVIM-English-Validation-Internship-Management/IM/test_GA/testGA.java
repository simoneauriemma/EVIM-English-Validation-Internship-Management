package test_GA;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

//Test suite che permette di testare l'intero modulo GA

@RunWith(Suite.class)
@SuiteClasses({ LoginTest.class, LogoutTest.class, RegistrazioneTest.class, RedirectToRegistrationTest.class })
public class testGA {

}
