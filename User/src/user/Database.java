/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Elize
 */
public class Database {
     private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Database(){
        connectToDatabase();
    }//end of constructor

    private void connectToDatabase(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

           connection = DriverManager.getConnection(
                    //The adres of the server
                    "jdbc:mysql://145.24.222.198:8001/team_blij",
                    //username
                    "root",
                    //password
                    "blijdorp"
                    );
        }catch(SQLException ex){
            
        }catch(ClassNotFoundException ex){
                    System.exit(0);
        }


    }//end of connectToDatabase()



    public void closeDatabase(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        }catch(SQLException ex){
        //TODO
        }
    }// end of closeDatabase()



    public ResultSet query(String sql){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        }catch(SQLException ex){
            return null;
        }

    }//get Query();

    
    
}
