package webTest;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddGuestWithValidData {
  private WebDriver driver;
  private String baseUrl;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeTest
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8070/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testAddGuestWithValidData() throws Exception {
    driver.get(baseUrl + "/HotelManagement_Old/NewHome.jsp");
    driver.findElement(By.cssSelector("li > a > span")).click();
    driver.findElement(By.xpath("//li[2]/a/span")).click();
    try {
      assertEquals("Add Guest Details", driver.findElement(By.cssSelector("h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("guestName")).clear();
    driver.findElement(By.id("guestName")).sendKeys("Test User");
    driver.findElement(By.id("guestAddress")).clear();
    driver.findElement(By.id("guestAddress")).sendKeys("Canada");
    driver.findElement(By.id("insertGuest")).click();
    try {
      assertEquals("Guest details inserted successfully", driver.findElement(By.id("alertMessage")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.cssSelector("li > a > span")).click();
  }

  @AfterTest
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
