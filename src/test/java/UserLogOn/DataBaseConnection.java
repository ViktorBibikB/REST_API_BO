package UserLogOn;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DataBaseConnection {
    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;


        try {
            Properties props = new Properties();
            props.load(new FileInputStream("C:/Users/User/IdeaProjects/LegalUsers/src/SQL_connection_data.properties"));
            
            // 1. Get a connection to database
            myConn = DriverManager.getConnection(props.getProperty("dbUrl"), props.getProperty("user"), props.getProperty("pass"));

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Execute SQL query
            myRs = myStmt.executeQuery("SELECT * " +
                                          "FROM web02_billing.gas_user_legal " +
                                          "WHERE edrpou = '03342573'" +
                                          "AND role = 'admin'");

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
