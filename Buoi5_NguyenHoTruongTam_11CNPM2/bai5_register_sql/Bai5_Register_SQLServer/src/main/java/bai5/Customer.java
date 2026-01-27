package bai5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Customer {
    public String id;
    public String name;
    public String email;
    public String phone;
    public String address;
    public String password;
    public String gender;
    public LocalDate birthDate;

    public Customer() {}

    public Customer(String id, String name, String email, String phone, String address, 
                    String password, String birthDateStr, String gender) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
        this.gender = gender;
        
        // Parse ngày sinh nếu không rỗng
        if (birthDateStr != null && !birthDateStr.trim().isEmpty()) {
            try {
                DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                this.birthDate = LocalDate.parse(birthDateStr, fmt);
            } catch (Exception e) {
                this.birthDate = null;
            }
        } else {
            this.birthDate = null;
        }
    }
}


