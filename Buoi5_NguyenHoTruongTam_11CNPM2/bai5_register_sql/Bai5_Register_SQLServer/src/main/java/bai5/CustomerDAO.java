package bai5;

/**
 * DAO (Data Access Object) cho Customers
 * Kết nối SQL Server qua sqlcmd (không cần JDBC driver)
 */
public class CustomerDAO {

    public static boolean existsById(String id) throws Exception {
        return DBConnection.existsById(id);
    }

    public static boolean existsByEmail(String email) throws Exception {
        return DBConnection.existsByEmail(email);
    }

    public static void insert(Customer cus) throws Exception {
        DBConnection.insertCustomer(cus);
    }
}


