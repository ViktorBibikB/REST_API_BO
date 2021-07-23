package Helpers;
import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static Helpers.PropertiesFile.getDBProperty;
import static Helpers.PropertiesFile.propertiesFileInputStream;


public class DataBaseConnection {

    private static final Logger log = LogManager.getLogger(DataBaseConnection.class.getName());

    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static ResultSet resultSet = null;
    protected static String logonName = null;



        public static String selectFromDB(String sqlQuery, String columnName) throws SQLException {
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    logonName = resultSet.getString(columnName);
                }
                log.info("Select statement has been processed successfully.");
            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            return logonName;
        }

        public static void deleteFromDB(String sqlQuery) throws SQLException {
            try {
                statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(sqlQuery);
                log.info("Statement has been deleted from database.");
        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
    }
        public static void connectToDatabase() throws SQLException {
            propertiesFileInputStream();
            connection = DriverManager.getConnection(getDBProperty("dbUrl"), getDBProperty("user"),getDBProperty("pass"));
            log.info("DataBase connection established.");
        }

        public static void closeBDConnection(){
            try {
                if (resultSet != null) {
                    resultSet.close();
                }

                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }
                log.info("DataBase connection closed.");
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

    }

