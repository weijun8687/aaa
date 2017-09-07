package util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCUtil {

    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;

    static {
        try {
            InputStream inputStream = JDBCUtil.class.getClassLoader().getResourceAsStream("dbcfg.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            Class.forName(driverClass);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static com.mysql.jdbc.Connection getConnection() throws Exception {
//        Class.forName("com.mysql.jdbc.Driver");
        // 所有与数据库的交互都是基于链接的
//        Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/db?user=root&password=root");

        com.mysql.jdbc.Connection connection = (com.mysql.jdbc.Connection)DriverManager.getConnection(url, user, password);
        return connection;
    }

    public static void release(ResultSet resultSet, Statement statement, java.sql.Connection connection){
        if (resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
