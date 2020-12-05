package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTool {

    //*************************************************Connecting to database*************************************************
    public static ResultSet connectTo(String dbName, String password, String sqlQuery) throws SQLException {
        String url = "jdbc:mysql://localhost/" + dbName;
        Connection connection = DriverManager.getConnection(url, "root", password);

        ResultSet resultSet = query(connection, sqlQuery);
        if (connection != null)
            return resultSet;

        return null;
    }

    //******************************************Running the query return the result*******************************************
    public static ResultSet query(Connection connection, String sqlQuery) throws SQLException {
        try {
            return connection.createStatement()
                    .executeQuery(sqlQuery);
        } catch (Exception e) {
            connection.createStatement()
                    .executeUpdate(sqlQuery);
        }


        return null;
    }
}
