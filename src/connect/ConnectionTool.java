package connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTool {

    public static Connection connectTo(String dbName, String userName, String password) throws SQLException {
        String url = "jdbc:mysql://localhost/"+dbName;
        Connection connection = DriverManager.getConnection(url , userName , password);

            if (connection!=null)
                return connection;

        return null;
    }

    public static ResultSet query (Connection connection , String sqlQuery) throws SQLException{
         ResultSet resultSet = connection.createStatement()
                .executeQuery(sqlQuery);

            if(resultSet!=null)
                return resultSet;

        return null;
    }
}
