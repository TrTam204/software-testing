package bai5;
    public static void main(String[] args) {
        try {
            System.out.println("=== TEST INSERT ===");
            
            // Test 1: Check ID exists
            System.out.println("\n[TEST 1] Kiểm tra ID 'KH001' tồn tại?");
            boolean idExists = CustomerDAO.existsById("KH001");
            System.out.println("Result: " + idExists);
            
            // Test 2: Check Email exists
            System.out.println("\n[TEST 2] Kiểm tra Email 'test@example.com' tồn tại?");
            boolean emailExists = CustomerDAO.existsByEmail("test@example.com");
            System.out.println("Result: " + emailExists);
            
            // Test 3: Insert customer
            System.out.println("\n[TEST 3] Thử insert khách hàng mới");
            Customer cus = new Customer("KH001", "Nguyễn Văn A", "test@example.com", 
                                       "0123456789", "Hà Nội", "password123", "2000-01-01", "Nam");
            CustomerDAO.insert(cus);
            System.out.println("✅ Insert thành công!");
            
            // Test 4: Check again
            System.out.println("\n[TEST 4] Kiểm tra lại ID 'KH001'");
            idExists = CustomerDAO.existsById("KH001");
            System.out.println("Result: " + idExists);
            
        } catch (Exception e) {
            System.err.println("❌ LỖI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
