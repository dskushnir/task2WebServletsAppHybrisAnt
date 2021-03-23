package app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        String dbName = "mysqldb";
        String timeZone = "?useUnicode=trueuseJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbUsername = "root";
        String dbPassword = "root";
        Class.forName(dbDriver);
        Connection connection = DriverManager.getConnection(dbURL + dbName + timeZone,
                dbUsername,
                dbPassword);
        return connection;
    }

    public static void disconnect(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }

    }

}
