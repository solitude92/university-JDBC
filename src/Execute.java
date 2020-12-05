import sqlFunctions.SqlFunctions;

import java.sql.SQLException;
import java.util.Scanner;

public class Execute {
    private static String tableName;

    public static void main(String[] args) {


        boolean isTable = true;
        while (isTable) {
            System.out.println("Enter password: ");
            String password = scanner().next();
            System.out.println("Enter database name: ");
            String dbName = scanner().next();
            System.out.println("Enter table name: ");
            tableName = scanner().next();
            SqlFunctions sqlFunctions = new SqlFunctions(dbName, password);
            while (true) {
                try {
                    sqlFunctions.showTable(tableName);
                    isTable = false;
                    printMenu();
                    executiveMenu(sqlFunctions, tableName);

                } catch (SQLException e) {
                    System.out.println("Not valid input!!!! Try again.");
                    if (isTable)
                        break;
                }
            }
        }
    }


    public static Scanner scanner() {
        return new Scanner(System.in);
    }

    public static void printMenu() {
        System.out.println();
        System.out.println("=*=*=*=*=*==*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println("*=*=*  |What do you want to do?(Enter the number:)                         *=*=*");
        System.out.println("*=*=*  |1)Show another table at the same time                              *=*=*");
        System.out.println("*=*=*  |2)Add to the table                                                 *=*=*");
        System.out.println("*=*=*  |3)Delete from the table                                            *=*=*");
        System.out.println("*=*=*  |4)Edit in the table                                                *=*=*");
        System.out.println("*=*=*  |5)Select another table                                             *=*=*");
        System.out.println("*=*=*  |6)Select another data base                                         *=*=*");
        System.out.println("*=*=*  |7)Developer option(Run your own code)                              *=*=*");
        System.out.println("=*=*=*=*=*==*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*==*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
        System.out.println();
    }

    public static void executiveMenu(SqlFunctions sqlFunctions, String tableName) throws SQLException {

        int action = scanner().nextInt();
        switch (action) {
            case 1:
                //To show another table at the same time of current table
                //It's help full while changing the middle table
                System.out.println("Enter another table name to show:(NOTE:operations won't" +
                        " affect this table unless you use '5' to change the table) ");
                sqlFunctions.showTable(scanner().next());
                break;
            case 2:
                sqlFunctions.addToTable(tableName);
                break;
            case 3:
                sqlFunctions.deleteFromTable(tableName);
                break;
            case 4:
                sqlFunctions.editInTable(tableName);
                break;
            case 5:
                System.out.println("Enter table name: ");
                Execute.tableName = scanner().next();
                break;
            case 6:
                System.out.println("Enter database name: ");
                sqlFunctions.setDbName(scanner().next());
                System.out.println("Enter table name: ");
                Execute.tableName = scanner().next();
                break;
            case 7:
                try {
                    sqlFunctions.developerOption(tableName);
                } catch (SQLException sqlException) {
                    System.out.println(sqlException.getMessage());
                }

                break;
            default:
                System.out.println("Invalid input!!!!");
        }
    }
}
