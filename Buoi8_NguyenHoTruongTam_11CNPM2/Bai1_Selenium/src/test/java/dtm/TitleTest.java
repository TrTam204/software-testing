package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TitleTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @Test(description = "Kiem thu tieu de trang chu")
    public void testTitle() throws InterruptedException {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();

        Thread.sleep(10000);

        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(description = "Kiem thu URL trang chu")
    public void testURL() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("saucedemo"));
    }

    @Test(description = "Kiem thu page source")
    public void testPageSource() {
        String source = driver.getPageSource();
        Assert.assertTrue(source.contains("Swag Labs"));
    }

    @Test(description = "Kiem tra form login hien thi")
    public void testLoginFormDisplayed() {
        boolean username = driver.findElement(By.id("user-name")).isDisplayed();
        boolean password = driver.findElement(By.id("password")).isDisplayed();
        boolean button = driver.findElement(By.id("login-button")).isDisplayed();

        Assert.assertTrue(username && password && button);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}