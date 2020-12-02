package printSqlDb;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlPrint {

    public static void print(ResultSet resultSet, ArrayList<String> columnNames) throws SQLException {
        while (resultSet.next() && columnNames != null) {
            int i = 0;
            while (i < columnNames.size()) {
                printRow(columnNames, resultSet, i++);
            }
            System.out.println();
        }


    }

    public static void printColumns(ArrayList<String> columnNames) throws SQLException {
        for (String str: columnNames) {
            System.out.println(str);
        }
    }

    public static void printRow(ArrayList<String> columnNames, ResultSet resultSet, int i) throws SQLException {
        System.out.printf("%s : %s-70s" ,columnNames.get(i) ,resultSet.getString(columnNames.get(i)));
        System.out.print("\t\t|\t\t");
    }

}
