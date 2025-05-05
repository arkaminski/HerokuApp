import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;

public class DropdownTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void toggleCheckboxes() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        List<WebElement> options = driver.findElements(By.xpath("//select[@id='dropdown']/option"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(options.size(), 3);
        options.get(1).click();
        softAssert.assertEquals(options.get(1).isSelected(), true, "Option 1 not selected");
        options.get(2).click();
        softAssert.assertEquals(options.get(2).isSelected(), true, "Option 2 not selected");
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
