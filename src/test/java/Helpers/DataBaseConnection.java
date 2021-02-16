package Helpers;
import java.sql.*;

import static Helpers.PropertiesFile.getDBProperty;
import static Helpers.PropertiesFile.propertiesFileInputStream;


public class DataBaseConnection {


    protected static Connection connection = null;
    protected static Statement statement = null;
    protected static ResultSet resultSet = null;
    protected static String logonName = null;



        public static String selectFromDB(String sqlQuery, String columnName) throws SQLException {
            try {
                propertiesFileInputStream();
                connection = DriverManager.getConnection(getDBProperty("dbUrl"), getDBProperty("user"),getDBProperty("pass"));
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    logonName = resultSet.getString(columnName);
                }

            }
            catch (Exception exc) {
                exc.printStackTrace();
            }

            return logonName;
        }

        public static void deleteFromDB(String sqlQuery) throws SQLException {
            try {
                propertiesFileInputStream();
                connection = DriverManager.getConnection(getDBProperty("dbUrl"), getDBProperty("user"),getDBProperty("pass"));
                statement = connection.createStatement();
                int rowsAffected = statement.executeUpdate(sqlQuery);

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
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
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }

    }

