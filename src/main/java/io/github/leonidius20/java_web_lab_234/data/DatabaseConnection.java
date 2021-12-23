package io.github.leonidius20.java_web_lab_234.data;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;

public class DatabaseConnection {

    private static Connection connection;

    private static DataSource dataSource;

    private DatabaseConnection() {}

    public static DataSource get() {
        /*if (connection == null) {
            ResourceBundle properties = ResourceBundle.getBundle("database");
            String url = properties.getString("url");
            String password = properties.getString("password");
            String user = properties.getString("user");
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
                // nothing else, return null connection
            }
        }
        return connection;*/
        if (dataSource == null) {
            try {
                InitialContext context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/postgres"); // can be null
            } catch (NamingException e) {
                e.printStackTrace();
            }

        }
        return dataSource;
    }


}
