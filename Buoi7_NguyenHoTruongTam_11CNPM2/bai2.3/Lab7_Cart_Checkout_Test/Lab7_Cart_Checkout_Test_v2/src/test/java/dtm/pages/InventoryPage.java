package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // ===== LOCATORS =====

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_badge")
    private List<WebElement> cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    // ===== METHODS =====
    public void themSanPhamTheoTen(String tenSanPham) {
        for (WebElement product : products) {
            String name = product.findElement(By.className("inventory_item_name")).getText();
            if (name.equals(tenSanPham)) {
                product.findElement(By.tagName("button")).click();
                break;
            }
        }
    }
    public void themNSanPhamDauTien(int n) {
        for (int i = 0; i < n && i < products.size(); i++) {
            products.get(i).findElement(By.tagName("button")).click();
        }
    }
    public int laySoLuongBadge() {
        if (cartBadge.size() == 0) return 0;
        return Integer.parseInt(cartBadge.get(0).getText());
    }
    public void sortSanPham(String option) {
        Select select = new Select(sortDropdown);
        select.selectByValue(option);
    }
    public List<String> layDanhSachTenSanPham() {
        List<String> names = new ArrayList<>();
        for (WebElement product : products) {
            names.add(product.findElement(By.className("inventory_item_name")).getText());
        }
        return names;
    }

    public List<Double> layDanhSachGiaSanPham() {
        List<Double> prices = new ArrayList<>();
        for (WebElement product : products) {
            String priceText = product.findElement(By.className("inventory_item_price"))
                    .getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public void moGioHang() {
        cartLink.click();
    }
}