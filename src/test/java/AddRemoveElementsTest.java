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

public class AddRemoveElementsTest {
    WebDriver driver;
    
    @BeforeMethod
public void setup() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.manage().window().maximize();
}

    @Test
    public void test() {
    driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
    driver.findElement(By.xpath("//button[text() = 'Add Element']")).click();
    driver.findElement(By.xpath("//button[text() = 'Add Element']")).click();
    List<WebElement> button = driver.findElements(By.xpath("//button[text() = 'Delete']"));
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertEquals(button.size(), 2);
    button.get(1).click();
    List<WebElement> buttonAfterDelete = driver.findElements(By.xpath("//button[text() = 'Delete']"));
    softAssert.assertEquals(buttonAfterDelete.size(), 1);
    softAssert.assertAll();

    }
    @AfterMethod (alwaysRun = true)
    public void tearDown() {
    driver.quit();
    }
}
