package webTest;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddGuestPageValidation {
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
  public void testAddGuestPageValidation() throws Exception {
    driver.get(baseUrl + "/HotelManagement_Old/NewHome.jsp");
    driver.findElement(By.cssSelector("li > a > span")).click();
    driver.findElement(By.xpath("//li[2]/a/span")).click();
    try {
      assertEquals("Add Guest Details", driver.findElement(By.cssSelector("h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("insertGuest")).click();
    try {
      assertEquals("Please Enter Guest Name", driver.findElement(By.id("name_error")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Please Enter Address", driver.findElement(By.id("address_error")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("guestName")).clear();
    driver.findElement(By.id("guestName")).sendKeys("#$%");
    driver.findElement(By.id("insertGuest")).click();
    try {
      assertEquals("Only Alphabatic Characters Are Allowed", driver.findElement(By.id("name_error")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("guestAddress")).clear();
    driver.findElement(By.id("guestAddress")).sendKeys("^%&");
    driver.findElement(By.id("insertGuest")).click();
    try {
      assertEquals("Only AlphaNumeric Characters Are Allowed", driver.findElement(By.id("address_error")).getText());
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
