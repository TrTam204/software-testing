package dtm.data;

import org.testng.annotations.DataProvider;

public class DangNhapData {

    @DataProvider(name = "du_lieu_dang_nhap")
    public Object[][] getData() {

        return new Object[][]{

                // ===== TÀI KHOẢN HỢP LỆ =====
                {"standard_user", "secret_sauce", "THANH_CONG", "Login chuẩn"},
                {"problem_user", "secret_sauce", "THANH_CONG", "Login user lỗi UI"},
                {"performance_glitch_user", "secret_sauce", "THANH_CONG", "Login user chậm"},
                {"error_user", "secret_sauce", "THANH_CONG", "Login user lỗi chức năng"},

                // ===== TÀI KHOẢN BỊ KHÓA =====
                {"locked_out_user", "secret_sauce", "BI_KHOA", "User bị khóa"},

                // ===== TÀI KHOẢN KHÔNG TỒN TẠI =====
                {"abc_user", "abc123", "SAI_THONG_TIN", "User không tồn tại"},
                {"standard_user", "wrong_pass", "SAI_THONG_TIN", "Sai password"},

                // ===== TRƯỜNG TRỐNG =====
                {"", "secret_sauce", "TRUONG_TRONG", "Trống username"},
                {"standard_user", "", "TRUONG_TRONG", "Trống password"},
                {"", "", "TRUONG_TRONG", "Trống cả hai"},

                // ===== KÝ TỰ ĐẶC BIỆT & KHOẢNG TRẮNG =====
                {"standard_user@", "secret_sauce", "SAI_THONG_TIN", "Username có ký tự đặc biệt"},
                {" standard_user", "secret_sauce", "SAI_THONG_TIN", "Khoảng trắng đầu"},
                {"standard_user ", "secret_sauce", "SAI_THONG_TIN", "Khoảng trắng cuối"},

                // ===== NULL =====
                {null, "secret_sauce", "TRUONG_TRONG", "Username null"},
                {"standard_user", null, "TRUONG_TRONG", "Password null"},
                {null, null, "TRUONG_TRONG", "Cả hai null"}
        };
    }
}
