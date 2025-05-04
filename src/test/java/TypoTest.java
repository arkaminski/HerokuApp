import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

public class TypoTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkTypo() {
        driver.get("https://the-internet.herokuapp.com/typos");
        int countOfCorrectText = 0;
        for (int i = 0; i < 5; i++) {
            if (driver.findElement(By.xpath(
                    "//p[2]")).getText().contains("you won't")) {
                countOfCorrectText++;
            }
            driver.navigate().refresh();
        }
        Assert.assertEquals(countOfCorrectText, 5,
                countOfCorrectText + " раза был обнаружен правильный текст.");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
