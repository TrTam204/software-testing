package dtm;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "Login thanh cong")
    public void testLoginSuccess() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.urlContains("inventory"));

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"),
                "Dang nhap khong chuyen sang trang inventory!");
    }

    @Test(description = "Sai mat khau")
    public void testLoginWrongPassword() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("login-button")).click();

        String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        ).getText();

        Assert.assertTrue(error.contains("Username and password do not match"),
                "Thong bao loi khong xuat hien!");
    }

    @Test(description = "Bo trong username")
    public void testLoginEmptyUsername() {

        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        ).getText();

        Assert.assertTrue(error.contains("Username is required"),
                "Khong hien thong bao Username is required!");
    }

    @Test(description = "Bo trong password")
    public void testLoginEmptyPassword() {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        ).getText();

        Assert.assertTrue(error.contains("Password is required"),
                "Khong hien thong bao Password is required!");
    }

    @Test(description = "Tai khoan bi khoa")
    public void testLoginLockedUser() {

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))
        ).getText();

        Assert.assertTrue(error.contains("Sorry, this user has been locked out"),
                "Khong hien thong bao tai khoan bi khoa!");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}