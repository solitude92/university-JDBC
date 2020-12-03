package sqlFunctions;

import connect.ConnectionTool;
import printSqlDb.SqlPrint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class SqlFunctions {
    public String dbName = "";
    public String password = "";

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
    public void addToTable(String tableName) {
        StringBuilder sqlCode = new StringBuilder("INSERT INTO " + tableName + " value (");
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> columnNames =  printColumns(tableName);
        System.out.println("Please write the value of columns in a row: ");
        for (int i = 0; i < columnNames.size(); i++) {
            if (i<columnNames.size()-1)
                sqlCode.append("'");
            sqlCode.append(scanner().next());
            if (i<columnNames.size()-1)
                sqlCode.append("', ");
        }
        sqlCode.append(")");
        System.out.println(sqlCode.toString());
        connectToChange(tableName , sqlCode.toString());
        System.out.println("The data added successfully");

    }

    public void deleteFromTable(String tableName) {

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
    public ArrayList<String> printColumns(String tableName){
        ArrayList<String> columnNames = getColumnName(tableName);
        System.out.println(columnNames);
        return columnNames;
    }

    //*******************************************Connect to database and print table******************************************
    private void connectToPrint(String tableName, String sqlCode) {
        try (ResultSet resultSet = ConnectionTool.connectTo(this.dbName, this.password, sqlCode);
        ) {
            ArrayList<String> columnNames = getColumnName(tableName);
            assert resultSet != null;
            SqlPrint.print(resultSet, columnNames);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //***********************************************Changing without printing***********************************************
    private void connectToChange(String tableName, String sqlCode) {
        try (ResultSet resultSet = ConnectionTool.connectTo(this.dbName, this.password, sqlCode);
        ) {
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
