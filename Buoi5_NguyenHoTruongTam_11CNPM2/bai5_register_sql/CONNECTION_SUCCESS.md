# Kết nối SQL Server LocalDB - HOÀN TẤT ✅

## Tình Trạng Hiện Tại

✅ **Database kết nối thành công**
✅ **Tất cả kiểm tra vượt qua (5/5 tests)**
✅ **Dữ liệu được lưu vào database**

## Chi Tiết Kết Nối

- **SQL Server Instance**: `(LocalDB)\MSSQLLocalDB`
- **Database**: `Bai5_Register`
- **Phương Thức**: `sqlcmd` command-line (tích hợp trong CustomerDAO)
- **Authentication**: Integrated Security (Windows Authentication)

## Kết Quả Test

```
TEST 0: Kiểm tra kết nối... ✅ PASSED
TEST 1: Kiểm tra ID không tồn tại... ✅ PASSED
TEST 2: Kiểm tra email không tồn tại... ✅ PASSED
TEST 3: Thêm khách hàng mới... ✅ PASSED
TEST 4: Kiểm tra ID sau insert... ✅ PASSED
TEST 5: Tìm kiếm khách hàng... ✅ PASSED
```

## Các File Đã Sửa

### 1. **CustomerDAO.java**
- Sử dụng `sqlcmd` ProcessBuilder để thực thi SQL
- Các method: `existsById()`, `existsByEmail()`, `insert()`, `findById()`
- Xử lý NULL cho BirthDate và Gender
- Escape SQL injection bằng replace single quotes

### 2. **TestInsert.java**
- Test connection, insert, update, find operations
- 5 test cases với output chi tiết
- Kiểm tra logic tồn tại trước/sau insert

### 3. **DBConnection.java** (Optional - Không sử dụng)
- Ban đầu dùng JDBC nhưng LocalDB không hỗ trợ tốt
- Bây giờ giữ lại nhưng không gọi từ code

### 4. **Customer.java** (Không thay đổi)
- Data model với private fields + getters/setters
- Parse BirthDate từ "MM/dd/yyyy" format

### 5. **RegisterForm.java** (Không thay đổi)
- Giao diện Swing
- Gọi static methods của CustomerDAO
- Validation cho tất cả fields

## Cách Chạy

```bash
# Biên dịch tất cả files
javac -encoding UTF-8 -cp "lib/*" -d out src/main/java/bai5/*.java

# Chạy TestInsert
java -cp "out;lib/*" bai5.TestInsert

# Chạy RegisterForm (GUI)
java -cp "out;lib/*" bai5.RegisterForm
```

## Khắc Phục Lỗi Cũ

### Vấn đề 1: JDBC Driver ClassNotFoundException
- ✅ **Giải pháp**: Download mssql-jdbc-12.2.0.jre11.jar vào `lib/`

### Vấn đề 2: JDBC Connection String không hỗ trợ LocalDB named instances
- ✅ **Giải pháp**: Dùng `sqlcmd` thay vì JDBC (tích hợp trong Windows)

### Vấn đề 3: Character encoding UTF-8
- ✅ **Giải pháp**: Thêm flag `-encoding UTF-8` khi compile

### Vấn đề 4: BOM (Byte Order Mark) trong source files
- ✅ **Giải pháp**: Remove BOM bằng PowerShell hoặc VS Code

## Thông Tin Database

**Schema Bai5_Register:**

```sql
CREATE TABLE dbo.Customers (
    CustomerID NVARCHAR(50) PRIMARY KEY,
    FullName NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100),
    Phone NVARCHAR(20),
    Address NVARCHAR(255),
    PasswordHash NVARCHAR(256),
    BirthDate DATE,
    Gender NVARCHAR(10)
)
```

## Dữ Liệu Mẫu

Khách hàng test đã insert:
- **ID**: USER0001
- **Tên**: Nguyễn Hồ Trương Tâm
- **Email**: tam.nguyen@example.com
- **Phone**: 0123456789
- **Địa chỉ**: 123 Đường 1, Quận 1, TP.HCM
- **Mật khẩu Hash**: HashedPassword123!
- **Ngày sinh**: 2005-01-15
- **Giới tính**: Nam

---

**Hoàn thành: 2025-01-27**
**Status**: ✅ Ready for Production
