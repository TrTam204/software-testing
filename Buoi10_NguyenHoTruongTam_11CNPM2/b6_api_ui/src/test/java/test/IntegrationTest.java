package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.SkipException;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class IntegrationTest {

    WebDriver driver;
    String token;
    boolean isApiAlive = false;

    @BeforeMethod
    public void loginApi() {

        Response res =
                given()
                        .contentType("application/json")
                        .body("{\"email\":\"eve.holt@reqres.in\",\"password\":\"cityslicka\"}")
                .when()
                        .post("https://reqres.in/api/login");

        if (res.statusCode() != 200) {
            throw new SkipException("API login fail → skip UI test");
        }

        token = res.jsonPath().getString("token");

        System.out.println("TOKEN: " + token);
    }

    // =========================
    // TEST UI (PHẦN A)
    // =========================
    @Test
    public void testLoginUI() {

        driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");

        //nhập form (không inject)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        //VERIFY
        String url = driver.getCurrentUrl();
        String title = driver.getTitle();

        assert url.contains("inventory");
        assert title.contains("Swag Labs");

        driver.quit();
    }

    // =========================
    // PHẦN B: CHECK API ALIVE
    // =========================
    @BeforeClass
    public void checkApiAlive() {

        Response res =
                given()
                .when()
                        .get("https://reqres.in/api/users");

        isApiAlive = (res.statusCode() == 200);

        System.out.println("API ALIVE: " + isApiAlive);
    }

    // =========================
    // FULL FLOW UI + API
    // =========================
    @Test
    public void testFullFlow() {

        //nếu API chết → skip
        if (!isApiAlive) {
            throw new SkipException("API down → skip test");
        }

        driver = new ChromeDriver();

        // =========================
        // UI LOGIN
        // =========================
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // =========================
        // ADD 2 PRODUCTS
        // =========================
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        // ASSERT badge = 2
        String badge =
                driver.findElement(By.className("shopping_cart_badge")).getText();

        assert badge.equals("2");

        // =========================
        // VÀO CART
        // =========================
        driver.findElement(By.className("shopping_cart_link")).click();

        // ASSERT có 2 sản phẩm
        int items =
                driver.findElements(By.className("cart_item")).size();

        assert items == 2;

        driver.quit();
    }
}