import org.omg.CORBA.*;

import javax.swing.*;
import java.lang.Object;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by Elize on 25-3-2015.
 */
public class GetData {
    private Database database = null;
    Scanner scanner = new Scanner(System.in);


    public GetData(){
        try {
            database = new Database();
            getQuery();


        }finally {
            database.closeDatabase();
        }


    }

    private void getQuery(){
        System.out.println("Voer een geldige SQL Query in:");
        String sql = scanner.nextLine();
        printResult(database.UserQuery(sql));
    }

    private void printResult(ResultSet resultSet){
        try{
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                String columnValue = resultSet.getString(i);
                System.out.print(rsmd.getColumnName(i) + ": " +  columnValue + "\n");
            }
        }
        }catch(SQLException ex){

        }
    }





}// end of GetData class
