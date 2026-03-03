package dtm.tests;

import dtm.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TC_GioHangTest extends BaseTest {
    @Test(groups={"regression"}, description="TC_CART_010: Kiểm tra tổng tiền chính xác")
    public void kiemTraTongTien() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        List<WebElement> pricesElements = driver.findElements(By.className("inventory_item_price"));
        double expectedItemTotal = 0;

        for (int i = 0; i < 3; i++) {
            expectedItemTotal += Double.parseDouble(
                    pricesElements.get(i).getText().replace("$","")
            );
        }
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        int added = 0;
        for (WebElement btn : buttons) {
            if (btn.getText().contains("Add to cart") && added < 3) {
                btn.click();
                added++;
            }
        }
        driver.findElement(By.className("shopping_cart_link")).click();
        driver.findElement(By.id("checkout")).click();
        driver.findElement(By.id("first-name")).sendKeys("Tam");
        driver.findElement(By.id("last-name")).sendKeys("Nguyen");
        driver.findElement(By.id("postal-code")).sendKeys("70000");
        driver.findElement(By.id("continue")).click();
        double itemTotal = Double.parseDouble(
                driver.findElement(By.className("summary_subtotal_label"))
                        .getText().replace("Item total: $","")
        );

        double tax = Double.parseDouble(
                driver.findElement(By.className("summary_tax_label"))
                        .getText().replace("Tax: $","")
        );

        double total = Double.parseDouble(
                driver.findElement(By.className("summary_total_label"))
                        .getText().replace("Total: $","")
        );

        // ASSERT
        Assert.assertEquals(itemTotal, expectedItemTotal, 0.01);
        Assert.assertTrue(Math.abs(tax - itemTotal * 0.08) < 0.01);
        Assert.assertTrue(Math.abs(total - (itemTotal + tax)) < 0.01);
    }
}