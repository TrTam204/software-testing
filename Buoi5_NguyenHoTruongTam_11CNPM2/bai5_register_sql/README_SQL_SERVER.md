# Hướng Dẫn Kết Nối SQL Server cho Ứng Dụng Đăng Ký Tài Khoản

## 📋 Thay Đổi Được Thực Hiện

### 1. **DBConnection.java** - Sử dụng JDBC chuẩn
   - ✅ Thay thế `sqlcmd` bằng JDBC Driver (Microsoft SQL Server JDBC Driver)
   - ✅ Thêm phương thức `getConnection()` trả về `Connection` object
   - ✅ Thêm phương thức `testConnection()` để kiểm tra kết nối
   - 📝 Cấu hình kết nối: `localhost:1433`, Windows Authentication

### 2. **Customer.java** - Chuẩn hóa properties theo database
   - ✅ Đổi `public fields` thành `private` với getters/setters
   - ✅ Đổi tên properties: 
     - `id` → `customerId`
     - `name` → `fullName`
     - `password` → `passwordHash`
   - ✅ Thêm phương thức `toString()` để debug
   - ✅ Khớp 100% với cấu trúc bảng SQL Server:
     ```
     CustomerID (varchar 10)
     FullName (nvarchar 50)
     Email (varchar 100)
     Phone (varchar 12)
     Address (nvarchar 255)
     PasswordHash (varchar 255)
     BirthDate (date)
     Gender (nvarchar 10)
     ```

### 3. **CustomerDAO.java** - Sử dụng static methods
   - ✅ Chuyển sang static methods (không cần `new CustomerDAO()`)
   - ✅ `existsById(String customerId)` - kiểm tra ID trùng
   - ✅ `existsByEmail(String email)` - kiểm tra Email trùng
   - ✅ `insert(Customer c)` - thêm khách hàng
   - ✅ `findById(String customerId)` - tìm khách hàng theo ID
   - ✅ Xử lý đúng NULL values cho BirthDate và Gender
   - ✅ Sử dụng `PreparedStatement` để tránh SQL Injection

### 4. **RegisterForm.java** - Cập nhật gọi DAO
   - ✅ Xóa dòng `new CustomerDAO()`
   - ✅ Thay `dao.insert()` bằng `CustomerDAO.insert()` (static call)
   - ✅ Sửa kỹ lưỡng việc tạo object `Customer` với getter/setter mới

### 5. **TestInsert.java** - Hoàn chỉnh test cases
   - ✅ Thêm test kết nối: `DBConnection.testConnection()`
   - ✅ Thêm test tìm kiếm: `CustomerDAO.findById()`
   - ✅ Sửa định dạng ngày: `01/15/2000` (MM/dd/yyyy)

### 6. **insert_test.sql** - Sửa tên cột SQL
   - ✅ Đổi `CustomerName` → `FullName`
   - ✅ Đổi `Password` → `PasswordHash`
   - ✅ Thêm `dbo.` prefix cho table name
   - ✅ Thêm `N'...'` prefix cho Unicode (tiếng Việt)

---

## 🔧 Yêu Cầu Cài Đặt

### 1. **SQL Server** (đã cài sẵn)
   - Server: `localhost` hoặc `(LocalDB)\MSSQLLocalDB`
   - Database: `Bai5_Register`
   - Authentication: Windows (Integrated Security)

### 2. **JDBC Driver** - Rất Quan Trọng ⚠️
   Bạn **PHẢI** thêm Microsoft SQL Server JDBC Driver vào project:

   **Option A: Maven** (nếu sử dụng Maven)
   ```xml
   <dependency>
       <groupId>com.microsoft.sqlserver</groupId>
       <artifactId>mssql-jdbc</artifactId>
       <version>12.2.0.jre11</version>
   </dependency>
   ```

   **Option B: Thêm JAR trực tiếp**
   - Tải file `.jar` từ: https://github.com/Microsoft/mssql-jdbc/releases
   - Copy vào thư mục: `Bai5_Register_SQLServer/lib/`
   - Cấu hình trong IDE (Eclipse/IntelliJ) để add vào classpath

### 3. **Cấu Hình Connection String** (nếu cần thay đổi)
   Mở `DBConnection.java` và sửa:
   ```java
   private static final String SERVER = "localhost";  // Hoặc (LocalDB)\MSSQLLocalDB
   private static final String PORT = "1433";
   private static final String DATABASE = "Bai5_Register";
   ```

---

## ▶️ Cách Chạy

### **Test Kết Nối**
```bash
java -cp "./lib/*:./target/classes" bai5.TestInsert
```

### **Chạy Form Đăng Ký**
```bash
java -cp "./lib/*:./target/classes" bai5.RegisterForm
```

### **Chạy SQL Script (Optional)**
```sql
-- Trong SQL Server Management Studio hoặc sqlcmd
USE Bai5_Register;
GO
:r insert_test.sql
```

---

## ✅ Kiểm Tra Lỗi Thường Gặp

| Lỗi | Nguyên Nhân | Cách Sửa |
|-----|-----------|---------|
| `ClassNotFoundException: com.microsoft.sqlserver...` | Thiếu JDBC Driver | Thêm `mssql-jdbc-*.jar` vào `lib/` |
| `Unable to connect to server` | SQL Server không chạy | Bật SQL Server hoặc LocalDB |
| `Connection refused` | Port sai | Kiểm tra port trong SQL Server |
| `Database not found` | Database không tồn tại | Tạo database `Bai5_Register` |
| `Column 'CustomerName' not found` | Tên cột sai | SQL Server tên cột là `FullName` |

---

## 📊 Cấu Trúc Bảng SQL Server

```sql
CREATE TABLE dbo.Customers (
    CustomerID varchar(10) PRIMARY KEY,
    FullName nvarchar(50) NOT NULL,
    Email varchar(100) UNIQUE NOT NULL,
    Phone varchar(12) NOT NULL,
    Address nvarchar(255) NOT NULL,
    PasswordHash varchar(255) NOT NULL,
    BirthDate date NULL,
    Gender nvarchar(10) NULL
);
```

---

## 🔐 Lưu Ý Bảo Mật

⚠️ **Mật khẩu:**
- Hiện tại lưu plain text (MẤT AN TOÀN)
- **Nên** sử dụng hashing (SHA-256, bcrypt)
- Sửa trong `CustomerDAO.insert()`:
  ```java
  String hashedPassword = hashPassword(c.getPasswordHash());
  ps.setString(6, hashedPassword);
  ```

---

## 📝 Tóm Tắt Thay Đổi

| File | Thay Đổi |
|------|----------|
| `DBConnection.java` | JDBC + `getConnection()` method |
| `Customer.java` | Properties chuẩn + getters/setters |
| `CustomerDAO.java` | Static methods + `findById()` |
| `RegisterForm.java` | Gọi static methods |
| `TestInsert.java` | Thêm test connection |
| `insert_test.sql` | Sửa tên cột |

**✨ Tất cả file đã được sửa và sẵn sàng kết nối SQL Server!**
