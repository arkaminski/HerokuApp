import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class InputTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkInputLetters() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        driver.findElement(By.tagName("input")).sendKeys("qwerty");
        Assert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "");
    }

    @Test
    public void checkInputNumbers() {
        driver.get("https://the-internet.herokuapp.com/inputs");
        SoftAssert softAssert = new SoftAssert();
        driver.findElement(By.tagName("input")).sendKeys("123");
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "123");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_UP);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "124");
        driver.findElement(By.tagName("input")).sendKeys(Keys.ARROW_DOWN);
        softAssert.assertEquals(driver.findElement(By.tagName("input")).getAttribute("value"), "123");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
