package webTest;

import static org.testng.AssertJUnit.assertEquals;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CheckGuestWithValidData {
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
  public void testCheckGuestWithValidData() throws Exception {
    driver.get(baseUrl + "/HotelManagement_Old/NewHome.jsp");
    driver.findElement(By.cssSelector("li > a > span")).click();
    driver.findElement(By.xpath("//li[3]/a/span")).click();
    try {
      assertEquals("Check Guest", driver.findElement(By.cssSelector("h2")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.id("guestName")).clear();
    driver.findElement(By.id("guestName")).sendKeys("James Bond");
    driver.findElement(By.id("checkGuest")).click();
    try {
      assertEquals("Guest details are available", driver.findElement(By.id("alertMessage")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("12", driver.findElement(By.xpath("//div/table/tbody/tr[2]/td")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("James Bond", driver.findElement(By.xpath("//tr[2]/td[2]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("Canada", driver.findElement(By.xpath("//tr[2]/td[3]")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    try {
      assertEquals("VIP", driver.findElement(By.xpath("//tr[2]/td[4]")).getText());
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
