package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "user-name")
    private WebElement userNameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    public void dangNhap(String user, String pass) {
        userNameField.clear();
        if(user != null) userNameField.sendKeys(user);
        passwordField.clear();
        if(pass != null) passwordField.sendKeys(pass);
        loginButton.click();
    }

    public String layThongBaoLoi() {
        try {
            WebElement error = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-test='error']")
                )
            );
            return error.getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean isDangOTrangSanPham() {
        return driver.getCurrentUrl().contains("inventory.html");
    }
}