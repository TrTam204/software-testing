package bai5;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

/**
 * Kết nối SQL Server qua sqlcmd (Windows Command Line)
 * Hỗ trợ UTF-8 encoding cho tiếng Việt
 */
public class DBConnection {

    private static final String SERVER = "(LocalDB)\\MSSQLLocalDB";
    private static final String DATABASE = "Bai5_Register";

    public static boolean existsById(String id) throws Exception {
        String sql = String.format(
            "IF EXISTS(SELECT 1 FROM Customers WHERE CustomerID = N'%s') PRINT 'EXISTS' ELSE PRINT 'NOT_EXISTS'",
            id.replace("'", "''")
        );
        
        return executeSqlCommand(sql).contains("EXISTS") && !executeSqlCommand(sql).contains("NOT_EXISTS");
    }

    public static boolean existsByEmail(String email) throws Exception {
        String sql = String.format(
            "IF EXISTS(SELECT 1 FROM Customers WHERE Email = N'%s') PRINT 'EXISTS' ELSE PRINT 'NOT_EXISTS'",
            email.replace("'", "''")
        );
        
        return executeSqlCommand(sql).contains("EXISTS") && !executeSqlCommand(sql).contains("NOT_EXISTS");
    }

    public static void insertCustomer(Customer cus) throws Exception {
        String sql = String.format(
            "INSERT INTO Customers (CustomerID, FullName, Email, Phone, Address, PasswordHash, BirthDate, Gender) " +
            "VALUES (N'%s', N'%s', N'%s', N'%s', N'%s', N'%s', N'%s', N'%s')",
            cus.id.replace("'", "''"),
            cus.name.replace("'", "''"),
            cus.email.replace("'", "''"),
            cus.phone.replace("'", "''"),
            cus.address.replace("'", "''"),
            cus.password.replace("'", "''"),
            cus.birthDate != null ? cus.birthDate.toString() : "NULL",
            cus.gender != null ? cus.gender.replace("'", "''") : "NULL"
        );
        
        String output = executeSqlCommand(sql);
        if (output.toLowerCase().contains("error") || output.toLowerCase().contains("fail")) {
            throw new Exception("Lỗi lưu dữ liệu: " + output);
        }
        
        System.out.println("✅ Dữ liệu đã lưu vào SQL Server!");
    }

    /**
     * Thực thi câu lệnh SQL qua sqlcmd với UTF-8 encoding
     */
    private static String executeSqlCommand(String sql) throws Exception {
        // Tạo file tạm chứa SQL query
        Path tempFile = Files.createTempFile("sql_", ".sql");
        try {
            // Ghi SQL query vào file với UTF-8 encoding
            Files.write(tempFile, sql.getBytes(StandardCharsets.UTF_8));
            
            System.out.println("[DEBUG] SQL File: " + tempFile);
            System.out.println("[DEBUG] SQL Query: " + sql);
            
            // Chạy sqlcmd với file input
            ProcessBuilder pb = new ProcessBuilder(
                "sqlcmd",
                "-S", SERVER,
                "-d", DATABASE,
                "-E",
                "-i", tempFile.toString(),
                "-f", "UTF-8"  // UTF-8 input encoding
            );
            
            // Set environment để hỗ trợ UTF-8
            pb.environment().put("SQLCMDCOLSEP", ",");
            
            pb.redirectErrorStream(true);
            Process process = pb.start();
            
            // Đọc output với UTF-8 encoding
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8)
            );
            
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
            
            int exitCode = process.waitFor();
            System.out.println("[DEBUG] Exit Code: " + exitCode);
            System.out.println("[DEBUG] Output: " + output.toString());
            
            return output.toString();
        } finally {
            // Xóa file tạm
            Files.deleteIfExists(tempFile);
        }
    }
}


