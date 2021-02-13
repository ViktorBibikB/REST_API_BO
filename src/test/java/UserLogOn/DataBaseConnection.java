package UserLogOn;
import java.sql.*;

public class DataBaseConnection {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        String dbUrl = "jdbc:mysql://sho-mysql03-t:3306/web02_billing";
        String user = "bibik";
        String pass = "KuVkqyAg2FVku4yK";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(dbUrl, user, pass);
            System.out.println("Connection successful");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("SELECT * " +
                                          "FROM web02_billing.gas_user_legal " +
                                          "WHERE edrpou = '03342573'");

            // 4. Process the result set
            while (myRs.next()) {
                System.out.println(myRs.getString("logon_name") + ", " + myRs.getString("password_hash"));
            }
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            if (myRs != null) {
                myRs.close();
            }

            if (myStmt != null) {
                myStmt.close();
            }

            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
