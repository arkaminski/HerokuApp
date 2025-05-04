import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class CheckBoxTest {
    WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = new ChromeDriver();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkCheckbox() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        SoftAssert softAssert = new SoftAssert();
        boolean isFirstCheckBoxSelected =
                driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(1)")).isSelected();
        softAssert.assertEquals(isFirstCheckBoxSelected, false, "Checkbox 1 is selected");
        driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(1)")).click();
        driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(1)")).isSelected();
        softAssert.assertEquals(isFirstCheckBoxSelected, true, "Checkbox 1 is NOT selected");
        boolean isSecondCheckboxSelected =
                driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)")).isSelected();
        softAssert.assertEquals(isSecondCheckboxSelected, true, "Checkbox 2 is NOT selected");
        driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("[type=checkbox]:nth-of-type(2)")).isSelected();
        softAssert.assertEquals(isSecondCheckboxSelected, false, "Checkbox 2 is selected");
    }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {
        driver.quit();
        }
}
