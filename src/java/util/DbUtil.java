
package util;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.io.InputStream;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.Properties;



public class DbUtil {



    private static Connection connection = null;



    public static Connection getConnection() {

        if (connection != null)

            return connection;

        else {

            try {

                String driver = "org.apache.derby.jdbc.ClientDriver";

                String url = "jdbc:derby://localhost:1527/surveys";

                String user = "surveys";

                String password = "password";

                Class.forName(driver);

                connection = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            } catch (SQLException e) {

                e.printStackTrace();
            }

            return connection;
        }
    }
}



