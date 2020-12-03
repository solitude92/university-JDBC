import sqlFunctions.SqlFunctions;

import java.sql.SQLException;
import java.util.Scanner;

public class Execute {
    public static void main(String[] args) {

        String dbName;
        String password;
        String tableName;
        boolean isTable=true;

        while(true) {
            System.out.println("Enter password: ");
            password = scanner().next();
            System.out.println("Enter database name: ");
            dbName = scanner().next();
            while (isTable) {
                System.out.println("Enter table name: ");
                tableName = scanner().next();

                try {
                    SqlFunctions sqlFunctions = new SqlFunctions(dbName, password);
                    sqlFunctions.showTable(tableName);
                    sqlFunctions.addToTable(tableName);
                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        }
    }
    public static Scanner scanner(){
        Scanner scanner = new Scanner(System.in);
        return scanner;
    }
}
