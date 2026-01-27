package bai5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    private String customerId;         // Khóa chính
    private String fullName;          // Tên đầy đủ
    private String email;             // Email (duy nhất)
    private String phone;             // Số điện thoại
    private String address;           // Địa chỉ
    private String passwordHash;      // Mật khẩu (lưu dạng hash)
    private String gender;            // Giới tính
    private LocalDate birthDate;      // Ngày sinh

    // Constructor không tham số
    public Customer() {
    }

    // Constructor đầy đủ
    public Customer(String customerId, String fullName, String email, String phone, String address, 
                    String passwordHash, String birthDateStr, String gender) {
        this.customerId = customerId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.passwordHash = passwordHash;
        this.gender = gender;
        
        // Parse ngày sinh nếu không rỗng
        if (birthDateStr != null && !birthDateStr.trim().isEmpty()) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                this.birthDate = LocalDate.parse(birthDateStr, fmt);
            } catch (Exception e) {
                this.birthDate = null;
            }
        }
    }

    // Getters - chuẩn theo tên cột trong database
    public String getCustomerId() { return customerId; }
    public String getFullName() { return fullName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getPasswordHash() { return passwordHash; }
    public String getGender() { return gender; }
    public LocalDate getBirthDate() { return birthDate; }

    // Setters - để tiện sử dụng
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setAddress(String address) { this.address = address; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public void setGender(String gender) { this.gender = gender; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }

    @Override
    public String toString() {
        return String.format(
            "Customer{id='%s', name='%s', email='%s', phone='%s', address='%s', gender='%s', birthDate='%s'}",
            customerId, fullName, email, phone, address, gender, birthDate
        );
    }
}


