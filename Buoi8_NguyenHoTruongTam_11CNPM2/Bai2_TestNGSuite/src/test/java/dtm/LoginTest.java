package dtm;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {

        DriverFactory.initDriver(browser);
        DriverFactory.getDriver().get("https://www.saucedemo.com");
    }

    @Test
    public void loginTest() {

        System.out.println("Login Test - Thread: " + Thread.currentThread().getId());
    }

    @AfterMethod
    public void tearDown() {

        DriverFactory.quitDriver();
    }
}