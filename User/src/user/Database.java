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
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author Elize
 */
public class Database {
    
   
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Database(){
        getConnection();
    }//end of constructor
    
    public final void getConnection()
    {
        connection =  NewJPanel2.getConnection();
        
    }

    


    



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
