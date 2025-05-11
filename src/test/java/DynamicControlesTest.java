import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class DynamicControlesTest {
    WebDriver driver;
    private final String pathToFile = "src/test/java/Test_file.txt";
    private final String fileName = "Test_file.txt";

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void checkContextMenu() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement contextMenu = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(contextMenu).perform();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        assertEquals(alertText,
                "You selected a context menu",
                "Текст не совпадает с ожидаемым");
        alert.accept();
    }

    @Test
    public void checkDynamicControls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("//*[text()='Remove']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("checkbox")));
    }
    
    @Test
    public void checkDynamicControlsEnabled(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        Assert.assertFalse (driver.findElement(By.xpath( "//input[@type='text']")).isEnabled());
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("It's enabled")));
    }

    @Test
    public void checkFrames() {
        driver.get("https://the-internet.herokuapp.com/frames");
        driver.findElement(By.xpath("//*[text()='iFrame']")).click();
        driver.switchTo().frame("mce_0_ifr");
        WebElement tag = driver.findElement(By.tagName("p"));
        String actualText = tag.getText();
        String expectedText = "Your content goes here.";
        Assert.assertEquals(actualText, expectedText, "Text is not equal with iFrame");
        driver.switchTo().defaultContent();
    }

    @Test
    public void checkDisabledField() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        Boolean fieldIsDisabled = !driver.findElement(By.cssSelector("[type='text']")).isEnabled();
        assertTrue(fieldIsDisabled, "Field is disabled");
        driver.findElement(By.xpath("//*[text()='Enable']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));
        Boolean fieldIsEnabled = driver.findElement(By.cssSelector("[type='text']")).isEnabled();
        assertTrue(fieldIsEnabled, "Field is enabled");
    }

    @Test
    public void checkFileUpload() {
        driver.get("https://the-internet.herokuapp.com/upload");
        WebElement fileInput = driver.findElement(By.id("file-upload"));
        java.io.File file = new java.io.File(pathToFile);
        fileInput.sendKeys(file.getAbsolutePath());
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();
        WebElement uploadedFileName = driver.findElement(By.id("uploaded-files"));
        Assert.assertEquals(uploadedFileName.getText(), fileName, "Name of file is not equal with uploaded");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
}
