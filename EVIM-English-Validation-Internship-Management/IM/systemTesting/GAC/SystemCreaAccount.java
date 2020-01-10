package systemTesting.GAC;

// Generated by Selenium IDE

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
public class SystemCreaAccount {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    driver = new FirefoxDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void creaAccount() {
    driver.get("http://localhost:8082/EVIM-English-Validation-Internship-Management/");
    driver.manage().window().setSize(new Dimension(1280, 736));
    driver.findElement(By.id("bott")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("password")).sendKeys("Xboxthebest");
    driver.findElement(By.id("username")).sendKeys("microsoftofficial@tiscali.it");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.findElement(By.cssSelector(".item:nth-child(9)")).click();
    driver.findElement(By.linkText("Crea account tutor aziendale")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).sendKeys("mario");
    driver.findElement(By.id("surname")).sendKeys("rossi");
    driver.findElement(By.id("emaila")).sendKeys("marior@hotmail.it");
    driver.findElement(By.id("telefono")).click();
    driver.findElement(By.id("telefono")).sendKeys("3332233444");
    driver.findElement(By.id("passworda")).click();
    driver.findElement(By.id("passworda")).sendKeys("asdasdasd");
    driver.findElement(By.id("confermaPassword")).click();
    driver.findElement(By.id("confermaPassword")).sendKeys("asdasdasd");
    driver.findElement(By.id("bottone")).click();
  }
}