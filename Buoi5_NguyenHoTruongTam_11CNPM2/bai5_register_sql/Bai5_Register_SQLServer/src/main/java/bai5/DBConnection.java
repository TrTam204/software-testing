package bai5;

import java.sql.*;

/**
 * Ket noi SQL Server LocalDB bang JDBC
 */
public class DBConnection {

    // For LocalDB, the best approach is to use ODBC driver via java.sql
    // But since we want pure JDBC, we'll use instanceName with localhost
    // The instance name for LocalDB is typically just "MSSQLLocalDB"
    private static final String CONNECTION_URL = 
        "jdbc:sqlserver://localhost;instance=MSSQLLocalDB;databaseName=Bai5_Register;integratedSecurity=true;encrypt=false;trustServerCertificate=true;loginTimeout=10;";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("LOI: JDBC Driver khong tim thay!");
            System.err.println("Can them mssql-jdbc-12.2.0.jre11.jar vao lib/");
            throw new SQLException("JDBC Driver not found", e);
        }
        
        try {
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (SQLException e) {
            System.err.println("LOI ket noi SQL Server: " + e.getMessage());
            throw e;
        }
    }

    public static boolean testConnection() {
        try {
            // Test sqlcmd connection
            ProcessBuilder pb = new ProcessBuilder(
                "sqlcmd",
                "-S", "(LocalDB)\\MSSQLLocalDB",
                "-d", "Bai5_Register",
                "-E",
                "-Q", "SELECT 1"
            );
            pb.redirectErrorStream(true);
            Process p = pb.start();
            
            // Wait for process and check exit code
            int exitCode = p.waitFor();
            if (exitCode == 0) {
                System.out.println("OK: Ket noi SQL Server thanh cong!");
                return true;
            } else {
                System.err.println("LOI ket noi: sqlcmd exit code=" + exitCode);
                return false;
            }
        } catch (Exception e) {
            System.err.println("LOI ket noi: " + e.getMessage());
            return false;
        }
    }
}


