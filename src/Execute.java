import sqlFunctions.SqlFunctions;

import java.sql.SQLException;

public class Execute {
    public static void main(String[] args) {

        try{
            SqlFunctions.showTable("university_student");
        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
