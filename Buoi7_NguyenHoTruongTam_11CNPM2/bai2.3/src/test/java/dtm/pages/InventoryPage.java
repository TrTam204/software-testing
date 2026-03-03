package dtm.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.*;
import java.util.stream.Collectors;

public class InventoryPage {

    private WebDriver driver;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(css = ".inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_badge")
    private List<WebElement> cartBadge;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void themSanPhamTheoTen(String tenSanPham) {
        for (WebElement p : products) {
            String name = p.findElement(By.className("inventory_item_name")).getText();
            if (name.equalsIgnoreCase(tenSanPham)) {
                p.findElement(By.tagName("button")).click();
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
        sortDropdown.sendKeys(option);
    }

    public List<String> layDanhSachTenSanPham() {
        return products.stream()
                .map(p -> p.findElement(By.className("inventory_item_name")).getText())
                .collect(Collectors.toList());
    }

    public List<Double> layDanhSachGiaSanPham() {
        return products.stream()
                .map(p -> p.findElement(By.className("inventory_item_price"))
                .getText().replace("$",""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public void moGioHang() {
        cartLink.click();
    }
}