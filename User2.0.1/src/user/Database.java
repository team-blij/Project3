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
import static javax.swing.JOptionPane.showMessageDialog;
/**
 *
 * @author Elize
 */
public class Database {
    
    public static boolean Connected = false;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    public static String username = null;
    public static String password = null; 

    public Database(String username, String password){
        this.username = username;
        this.password = password;
        connectToDatabase();
    }//end of constructor

    private void connectToDatabase(){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            connection = DriverManager.getConnection(
                    //The adres of the server
                    "jdbc:mysql://145.24.222.198:8001/team_blij",
                    //username
                    username,
                    //password
                    password
                    );
            Connected = true;
        }catch(SQLException ex){
            showMessageDialog(null, "Wrong info!");
        }catch(ClassNotFoundException ex){
                 
        }
    }//end of connectToDatabase()



    public void closeDatabase(){
        try {
            if(!connection.isClosed()){
                connection.close();
            }
        }catch(SQLException ex){
       
        }
    }// end of closeDatabase()



    public ResultSet query(String sql){
        if(Connected){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        }catch(SQLException ex){
            return null;
        }catch(NullPointerException ex){
        
        }
        }
        return resultSet;
    }//get Query();

}
