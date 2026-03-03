package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.InventoryPage;
import dtm.pages.CartPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TC_GioHangTest extends BaseTest {

    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    public void chuanBi() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(org.openqa.selenium.By.id("user-name")).sendKeys("standard_user");
        driver.findElement(org.openqa.selenium.By.id("password")).sendKeys("secret_sauce");
        driver.findElement(org.openqa.selenium.By.id("login-button")).click();

        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test(groups={"smoke"})
    public void themMotSanPham() {
        inventoryPage.themNSanPhamDauTien(1);
        Assert.assertEquals(inventoryPage.laySoLuongBadge(),1);
    }

    @Test(groups={"smoke"})
    public void them3SanPham() {
        inventoryPage.themNSanPhamDauTien(3);
        Assert.assertEquals(inventoryPage.laySoLuongBadge(),3);
    }

    @Test(groups={"regression"})
    public void xoaHetSanPham() {
        inventoryPage.themNSanPhamDauTien(2);
        inventoryPage.moGioHang();
        Assert.assertTrue(cartPage.soLuongItemTrongGio() > 0);
    }

    @Test(groups={"regression"})
    public void sortGiaTangDan() {
        inventoryPage.sortSanPham("lohi");
        List<Double> prices = inventoryPage.layDanhSachGiaSanPham();
        Assert.assertTrue(prices.get(0) <= prices.get(prices.size()-1));
    }
}