/**
 * Created by Elize on 17-3-2015.
 */

import net.sourceforge.jtds.jdbc.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;


public class Database {
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public Database(){

        connectToDatabase();
        useDatabase();
//        try {
//            statement = connection.createStatement();
//            statement.executeUpdate("DROP TABLE tweets");
//        }catch (SQLException ex){
//        }
//        createTable();



    }//end of constructor

    private void connectToDatabase(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

           connection = DriverManager.getConnection(
                    //The adres of the server
                    "jdbc:mysql://145.24.222.198:3306/twitter",
                    //"jdbc:sqlserver://169.254.119.142:1433",
                    //username
                    "root",
                    //password
                    "blijdorp"
                    );

            System.out.println("U heeft verbinding");
        }catch(SQLException ex){
            System.out.println("Er kon geen verbinding worden gemaakt.");
            ex.printStackTrace();
        }catch(ClassNotFoundException ex){
                    System.exit(0);
        }


    }//end of connectToDatabase()

    private void useDatabase(){
        try {
            statement = connection.createStatement();
            statement.executeUpdate("USE projectdata; ");
        }catch (SQLException ex){
        }

    }

    private void createTable() {
        try{
        statement = connection.createStatement();
            String sql = "CREATE TABLE Tweets"
                    +   "(  message    LONGTEXT      NOT NULL "
                    +   ",  user_id    VARCHAR(255)        PRIMARY KEY"
                    +   ",  username   VARCHAR(255)"
                    +   ",  location   VARCHAR(255)"
                    +   ",  create_date DATE "
                    +   ");"
                    ;
           // database.insertWeatherData(city, minTemperature, maxTemperature, averageTemperature, date, rain, wind, snow, clouds);
//                String sql =        "CREATE TABLE WEATHER"
//                                +   "( city VARCHAR(255) NOT NULL"
//                                +   ", min_Temperature FLOAT "
//                                +   ", max_Temperature FLOAT"
//                                +   ", average_Temperature FLOAT"
//                                +   ", date DATE "
//                                +   ", rain FLOAT"
//                                +   ", wind_Gust FLOAT"
//                                +   ", snow FLOAT "
//                                +   ", cloud_Percentage FLOAT ); "
//
//                        ;
        statement.executeUpdate(sql);
        }catch(SQLException ex)
        {
            System.out.println("Tabel niet gemaakt!");
            System.out.println(ex);
        }
    }//end of createTable()

    private void getTable() throws SQLException{
        statement = connection.createStatement();
            String sql = "SELECT FROM Tweets * "
                    ;
        statement.executeUpdate(sql);
    }//end of getTable()

    public void insertTweetIntoTable(String tableName, String message, Long user_id, String username, String location, Date date){
        try {
            statement = connection.createStatement();
            String sql = "INSERT INTO "
                    + tableName
                    + "(message, user_id, username, location, create_date) "
                    + "VALUES ("
                    + message
                    + ", "
                    + user_id
                    + ", "
                    + username
                    + ", "
                    + location
                    + ", "
                    + date
                    + ");";
            statement.executeUpdate(sql);
        }catch(SQLException ex){

            System.out.println("Niet ingevoerd. Er is een fout opgetreden.");
            System.out.println(ex);
        }

    }// end of insertIntoTable()

    public void insertWeatherData(String city, float minTemperature, float maxTemperature, float averageTemperature,
                                  Date date, float rain, float wind, float snow, float clouds) throws SQLException{
                    statement = connection.createStatement();
                    String sql =        "INSERT INTO Weather VALUES ("
                                  +     city
                                  +     ", "
                                  +     minTemperature
                                  +     ", "
                                  +     maxTemperature
                                  +     ", "
                                  +     averageTemperature
                                  +     ", "
                                  +     date
                                  +     ", "
                                  +     rain
                                  +     ", "
                                  +     wind
                                  +     ", "
                                  +     snow
                                  +     ", "
                                  +     clouds
                                  +     ");"
                            ;
                    statement.execute(sql);
    }//end of insertWeatherData()

    public void closeDatabase(){
        try {
            if(!connection.isClosed()){
                connection.close();
                System.out.println("Database closed");
            }
        }catch(SQLException ex){

        }
    }// end of closeDatabase()

}// end of class Database
