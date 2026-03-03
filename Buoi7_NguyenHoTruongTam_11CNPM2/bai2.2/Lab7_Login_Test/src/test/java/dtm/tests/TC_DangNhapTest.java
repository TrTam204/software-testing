package dtm.tests;

import dtm.base.BaseTest;
import dtm.pages.LoginPage;
import dtm.data.DangNhapData;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_DangNhapTest extends BaseTest {

    @Test(dataProvider = "du_lieu_dang_nhap",
          dataProviderClass = DangNhapData.class)
    public void kiemThuDangNhap(String user,
                                String pass,
                                String expected,
                                String description) {

        driver.get("https://www.saucedemo.com/");
        LoginPage loginPage = new LoginPage(driver);

        loginPage.dangNhap(user, pass);

        String actualError = loginPage.layThongBaoLoi();

        if (expected.equals("SUCCESS")) {
            Assert.assertTrue(
                loginPage.isDangOTrangSanPham(),
                "Thất bại: " + description
            );
        }
        else if (expected.equals("LOCKED")) {
            Assert.assertTrue(
                actualError.toLowerCase().contains("locked"),
                "Sai thông báo: " + actualError
            );
        }
        else if (expected.equals("INVALID")) {
            Assert.assertTrue(
                actualError.toLowerCase().contains("do not match"),
                "Sai thông báo: " + actualError
            );
        }
        else if (expected.equals("REQUIRED")) {
            Assert.assertTrue(
                actualError.toLowerCase().contains("required"),
                "Sai thông báo: " + actualError
            );
        }
    }
}