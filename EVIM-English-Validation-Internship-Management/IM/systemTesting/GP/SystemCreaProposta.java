package systemTesting.GP;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class SystemCreaProposta {
  private WebDriver driver;
  private Map<String, Object> vars;
  private String baseUrl;
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void creaPropostaAzienda() {
    driver.get("http://localhost:8080/EVIM-English-Validation-Internship-Management/");
    driver.manage().window().setSize(new Dimension(974, 1040));
    driver.findElement(By.id("bott")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("microsoftofficial@tiscali.it");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("Xboxthebest");
    driver.findElement(By.cssSelector("#collapse-2:nth-child(2)")).click();
    driver.findElement(By.id("tutoraziendale")).click();
    {
      WebElement dropdown = driver.findElement(By.id("tutoraziendale"));
      dropdown.findElement(By.xpath("//option[. = 'Ugo Ughi']")).click();
    }
    driver.findElement(By.id("tutoraziendale")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).click();
    driver.findElement(By.id("exampleFormControlTextarea1")).sendKeys("sono molto bravo a programmare");
    driver.findElement(By.id("exampleFormControlTextarea2")).click();
    driver.findElement(By.id("exampleFormControlTextarea2")).sendKeys("mi piace programmare tanto");
    driver.findElement(By.id("exampleFormControlTextarea3")).click();
    driver.findElement(By.id("exampleFormControlTextarea3")).sendKeys("obiettivi comuni a tutti gli altri tirocini");
    driver.findElement(By.id("exampleFormControlTextarea4")).click();
    driver.findElement(By.id("exampleFormControlTextarea4")).sendKeys("si svolgerà due volte a settimana per 3 ore");
    driver.findElement(By.id("button")).click();
   assertEquals("It should redirect to home page", "Home", driver.findElement(By.tagName("title")).getText());
  }
}
