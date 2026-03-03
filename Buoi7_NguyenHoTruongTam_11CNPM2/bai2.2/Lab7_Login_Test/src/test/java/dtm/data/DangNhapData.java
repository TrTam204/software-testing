package dtm.data;
import org.testng.annotations.DataProvider;
public class DangNhapData {
    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {
        return new Object[][] {
            {"standard_user", "secret_sauce", "SUCCESS", "Tài khoản hợp lệ"},
            {"locked_out_user", "secret_sauce", "LOCKED", "Tài khoản bị khóa"},
            {"invalid_user", "secret_sauce", "INVALID", "Tài khoản không tồn tại"},
            {"", "secret_sauce", "REQUIRED", "Trống username"},
            {"standard_user", "", "REQUIRED", "Trống password"},
            {"", "", "REQUIRED", "Trống cả hai"}
        };
    }
}