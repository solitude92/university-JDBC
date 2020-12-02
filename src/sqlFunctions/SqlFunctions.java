package sqlFunctions;

import connect.ConnectionTool;
import printSqlDb.SqlPrint;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlFunctions {
    public static void showTable(String tableName) throws SQLException {
        String sqlCode = "SELECT * FROM " + tableName;
        try (Connection connection = ConnectionTool.connectTo("university", "root", "123456");
             ResultSet resultSet = connection.createStatement()
                     .executeQuery(sqlCode)) {
            ArrayList<String> columnNames = getColumnName(tableName, connection);
            SqlPrint.print(resultSet, columnNames);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public static ArrayList<String> getColumnName(String tableName, Connection connection) {
        String sqlCode = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME LIKE '" + tableName + "'";
        ArrayList<String> columnNames = new ArrayList<>();
        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(sqlCode);) {
            while (resultSet.next()) {
                columnNames.add(resultSet.getString("column_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnNames;
    }

}
