package bai5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * CustomerDAO sử dụng `sqlcmd` để truy vấn SQL Server LocalDB
 */
public class CustomerDAO {

    private static final String SERVER = "(LocalDB)\\MSSQLLocalDB";
    private static final String DATABASE = "Bai5_Register";

    private static String runSql(String sql) throws Exception {
        // Retry logic for timeout issues
        int maxRetries = 3;
        int retryDelay = 500; // milliseconds
        Exception lastException = null;

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                return runSqlOnce(sql);
            } catch (Exception e) {
                lastException = e;
                if (e.getMessage().contains("Login timeout") && attempt < maxRetries) {
                    System.err.println("RETRY " + attempt + "/" + (maxRetries - 1) + ": Timeout, waiting " + retryDelay + "ms...");
                    Thread.sleep(retryDelay);
                    retryDelay *= 2; // exponential backoff
                } else {
                    throw e;
                }
            }
        }
        throw lastException;
    }

    private static String runSqlOnce(String sql) throws Exception {
        Path tmpFile = Files.createTempFile("query_", ".sql");
        try (BufferedWriter w = Files.newBufferedWriter(tmpFile, StandardCharsets.UTF_8)) {
            w.write("SET NOCOUNT ON;\n");
            w.write(sql);
        }

        ProcessBuilder pb = new ProcessBuilder(
            "sqlcmd",
            "-S", SERVER,
            "-d", DATABASE,
            "-E",
            "-l", "30",  // login timeout 30 seconds
            "-i", tmpFile.toString()
        );
        pb.redirectErrorStream(true);
        
        Process p = pb.start();
        StringBuilder output = new StringBuilder();
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }

        int exitCode = p.waitFor();
        tmpFile.toFile().delete();

        if (exitCode != 0) {
            String errorMsg = output.toString();
            System.err.println("DEBUG: sqlcmd exit code=" + exitCode);
            System.err.println("DEBUG: output=" + errorMsg);
            // Try to provide more helpful error message
            if (errorMsg.contains("Login timeout")) {
                throw new Exception("Login timeout expired - DB might be busy");
            }
            throw new Exception("sqlcmd failed (exit=" + exitCode + ")\n" + errorMsg);
        }

        return output.toString();
    }

    private static String escapeSql(String s) {
        if (s == null) return null;
        return s.replace("'", "''");
    }

    public static boolean existsById(String customerId) throws Exception {
        String sql = "SELECT COUNT(*) AS cnt FROM dbo.Customers WHERE CustomerID = N'" + escapeSql(customerId) + "'";
        String result = runSql(sql);
        return result.contains("1");
    }

    public static boolean existsByEmail(String email) throws Exception {
        String sql = "SELECT COUNT(*) AS cnt FROM dbo.Customers WHERE Email = N'" + escapeSql(email) + "'";
        String result = runSql(sql);
        return result.contains("1");
    }

    public static void insert(Customer c) throws Exception {
        String birthDate = "NULL";
        if (c.getBirthDate() != null) {
            birthDate = "'" + c.getBirthDate().toString() + "'";
        }

        String gender = "NULL";
        if (c.getGender() != null && !c.getGender().trim().isEmpty()) {
            gender = "N'" + escapeSql(c.getGender()) + "'";
        }

        String sql = "INSERT INTO dbo.Customers (CustomerID, FullName, Email, Phone, Address, PasswordHash, BirthDate, Gender) " +
                "VALUES (N'" + escapeSql(c.getCustomerId()) + "', " +
                "N'" + escapeSql(c.getFullName()) + "', " +
                "N'" + escapeSql(c.getEmail()) + "', " +
                "N'" + escapeSql(c.getPhone()) + "', " +
                "N'" + escapeSql(c.getAddress()) + "', " +
                "N'" + escapeSql(c.getPasswordHash()) + "', " +
                birthDate + ", " +
                gender + ")";

        String result = runSql(sql);
        if (result.toLowerCase().contains("error") || result.toLowerCase().contains("msg")) {
            throw new Exception("Insert failed: " + result);
        }

        System.out.println("OK: Them khach hang: " + c.getFullName());
    }

    public static Customer findById(String customerId) throws Exception {
        String sql = "SELECT * FROM dbo.Customers WHERE CustomerID = N'" + escapeSql(customerId) + "'";
        String result = runSql(sql);

        String[] lines = result.split("\r?\n");
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty() || line.length() < customerId.length()) continue;

            if (line.startsWith(customerId)) {
                // Parse the output row
                String[] parts = line.split("\\s{2,}");
                if (parts.length >= 2) {
                    Customer c = new Customer();
                    c.setCustomerId(parts.length > 0 ? parts[0].trim() : null);
                    c.setFullName(parts.length > 1 ? parts[1].trim() : null);
                    c.setEmail(parts.length > 2 ? parts[2].trim() : null);
                    c.setPhone(parts.length > 3 ? parts[3].trim() : null);
                    c.setAddress(parts.length > 4 ? parts[4].trim() : null);
                    c.setPasswordHash(parts.length > 5 ? parts[5].trim() : null);

                    if (parts.length > 6 && !parts[6].trim().isEmpty() && !parts[6].trim().equals("NULL")) {
                        try {
                            c.setBirthDate(LocalDate.parse(parts[6].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        } catch (Exception e) {
                            // ignore parse errors
                        }
                    }

                    if (parts.length > 7) {
                        String g = parts[7].trim();
                        if (!g.isEmpty() && !g.equals("NULL")) {
                            c.setGender(g);
                        }
                    }

                    return c;
                }
            }
        }

        return null;
    }
}
