package sqlFunctions;

import connect.ConnectionTool;
import printSqlDb.SqlPrint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlFunctions {
    private String dbName = "";
    private String password = "";

    public SqlFunctions(String dbName, String password) {
        this.dbName = dbName;
        this.password = password;
    }

    //******************************************Show column of the selected table*********************************************

    public void showTable(String tableName) throws SQLException {
        String sqlCode = "SELECT * FROM " + tableName;
        connectToPrint(tableName, sqlCode);
    }
    //***********************************************Add data to chosen table************************************************

    public void addToTable(String tableName) throws SQLException {
        ArrayList<String> columnNames = printColumns(tableName);
        StringBuilder sqlCode = new StringBuilder("INSERT INTO " + tableName + " (");
        for (int j = 0; j < columnNames.size(); j++) {
            sqlCode.append(columnNames.get(j));
            if (j < columnNames.size() - 1)
                sqlCode.append(", ");
        }
        sqlCode.append(" ) value( ");
        System.out.println("Please write the value of columns in a row: ");
        for (int i = 0; i < columnNames.size(); i++) {

            sqlCode.append("'");
            sqlCode.append(scanner().next());
            sqlCode.append("'");
            if (i < columnNames.size() - 1)
                sqlCode.append(", ");
        }
        sqlCode.append(")");
        System.out.println(sqlCode.toString());
        connectToChange(tableName, sqlCode.toString());
        System.out.println("The data added successfully");
    }
    //*******************************************Delete data from the table*******************************************

    public void deleteFromTable(String tableName) throws SQLException {
        String sqlCode = "delete from " + tableName + " where ";
        System.out.println("Enter the condition, for example 'student_id=10'. Make sure its a " +
                "\nvalid condition, otherwise nothing will be affected.");
        sqlCode = sqlCode + scanner().next();
        System.out.println(sqlCode);
        connectToChange(tableName, sqlCode);
        System.out.println("The data deleted successfully");


    }
    //***********************************************Edit data in chosen table************************************************

    public void editInTable(String tableName) throws SQLException {
        ArrayList<String> columnNames = printColumns(tableName);
        StringBuilder sqlCode = new StringBuilder("UPDATE " + tableName + "\nSET ");
        System.out.println("Enter the new data in order to change the row.NOTE:Enter the data without any change to" +
                " keep the data unchanged in a column.");
        for (int j = 0; j < columnNames.size(); j++) {
            sqlCode.append(columnNames.get(j)).append("='").append(scanner().next()).append("'");
            if (j < columnNames.size() - 1)
                sqlCode.append(", ");
        }
        System.out.println("Enter the condition, for example 'student_id=10'. Make sure its a " +
                "\nvalid condition, otherwise nothing will be affected.");
        sqlCode.append(" WHERE ").append(scanner().next());
        System.out.println(sqlCode.toString());
        connectToChange(tableName, sqlCode.toString());
        System.out.println("The data added successfully");
    }
    //*******************************************get columns name of selected table*******************************************

    public ArrayList<String> getColumnName(String tableName) {
        String sqlCode = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME LIKE '" + tableName + "'";
        ArrayList<String> columnNames = new ArrayList<>();
        try (ResultSet resultSet = ConnectionTool.connectTo(this.dbName, this.password, sqlCode);
        ) {
            while (resultSet.next()) {
                columnNames.add(resultSet.getString("column_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnNames;
    }
    //*******************************************Help full scanner method ******************************************

    public static Scanner scanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
    //*******************************************Print selected table's column names******************************************

    public ArrayList<String> printColumns(String tableName) {
        ArrayList<String> columnNames = getColumnName(tableName);
        System.out.println(columnNames);
        return columnNames;
    }
    //*******************************************Connect to database and print table******************************************

    private void connectToPrint(String tableName, String sqlCode) throws SQLException {
        try (ResultSet resultSet = ConnectionTool.connectTo(this.dbName, this.password, sqlCode);
        ) {
            ArrayList<String> columnNames = getColumnName(tableName);
            SqlPrint.print(resultSet, columnNames);
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new SQLException(throwables);
        }
    }
    //***********************************************Changing without printing***********************************************

    private void connectToChange(String tableName, String sqlCode) throws SQLException {
        try (ResultSet resultSet = ConnectionTool.connectTo(this.dbName, this.password, sqlCode);
        ) {
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            throw new SQLException(throwables);
        }
    }


    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
