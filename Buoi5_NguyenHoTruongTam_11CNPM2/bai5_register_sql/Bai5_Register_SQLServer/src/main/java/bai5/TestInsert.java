package bai5;

import java.time.LocalDate;

/**
 * Test: Kết nối CSDL và test các tác vụ
 */
public class TestInsert {
    public static void main(String[] args) throws Exception {
        System.out.println("=== TEST KET NOI SQL SERVER ===\n");

        // Test kết nối
        System.out.println("TEST 0: Kiểm tra kết nối...");
        try {
            DBConnection.testConnection();
            System.out.println("✅ Kết nối thành công!\n");
        } catch (Exception e) {
            System.out.println("❌ Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // TEST 1: existsById
        System.out.println("TEST 1: Kiểm tra khách hàng tồn tại (ID không tồn tại)");
        boolean exists = CustomerDAO.existsById("USER999");
        System.out.println("   USER999 tồn tại: " + (exists ? "có" : "không"));
        System.out.println(exists ? "❌ FAILED" : "✅ PASSED\n");

        // TEST 2: existsByEmail
        System.out.println("TEST 2: Kiểm tra email tồn tại");
        boolean emailExists = CustomerDAO.existsByEmail("nonexistent@example.com");
        System.out.println("   nonexistent@example.com tồn tại: " + (emailExists ? "có" : "không"));
        System.out.println(emailExists ? "❌ FAILED" : "✅ PASSED\n");

        // TEST 3: Insert
        System.out.println("TEST 3: Thêm khách hàng mới");
        Customer newCust = new Customer();
        newCust.setCustomerId("USER0001");
        newCust.setFullName("Nguyễn Hồ Trương Tâm");
        newCust.setEmail("tam.nguyen@example.com");
        newCust.setPhone("0123456789");
        newCust.setAddress("123 Đường 1, Quận 1, TP.HCM");
        newCust.setPasswordHash("HashedPassword123!");
        newCust.setBirthDate(LocalDate.of(2005, 1, 15));
        newCust.setGender("Nam");

        try {
            CustomerDAO.insert(newCust);
            System.out.println("✅ PASSED\n");
        } catch (Exception e) {
            System.out.println("❌ FAILED: " + e.getMessage());
            e.printStackTrace();
            System.out.println();
        }

        // TEST 4: Verify insert - check if ID now exists
        System.out.println("TEST 4: Kiểm tra khách hàng vừa thêm (ID tồn tại)");
        boolean newExists = CustomerDAO.existsById("USER0001");
        System.out.println("   USER0001 tồn tại: " + (newExists ? "có" : "không"));
        System.out.println(newExists ? "✅ PASSED\n" : "❌ FAILED\n");

        // TEST 5: findById
        System.out.println("TEST 5: Tìm kiếm khách hàng theo ID");
        Customer found = CustomerDAO.findById("USER0001");
        if (found != null) {
            System.out.println("✅ FOUND:");
            System.out.println("   " + found);
            System.out.println("✅ PASSED\n");
        } else {
            System.out.println("❌ NOT FOUND");
            System.out.println("❌ FAILED\n");
        }

        System.out.println("=== HOAN THANH TEST ===");
    }
}
