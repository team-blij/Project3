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
    }//end of constructor

    private void connectToDatabase(){

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

           connection = DriverManager.getConnection(
                    //The adres of the server
                    "jdbc:mysql://145.24.222.198:8001",
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

    }//end of useDatabase()


    private void getTable() throws SQLException{
        statement = connection.createStatement();
            String sql = "SELECT FROM Tweets * "
                    ;
        statement.executeUpdate(sql);
    }//end of getTable()

    public void insertTweetIntoTable(Long id_Tweet, Date tweet_date, String text, String location, Long twitterUser_idAccount, Long area_idArea, String user_Name, int followers, Long id_User, Long animal_idAnimal){
        try {
            statement = connection.createStatement();
                String sql = "INSERT INTO tweet"
                        + "(id_Tweet, tweet_date, text, location, twitterUser_idAccount, area_idArea, user_Name, followers, id_User, animal_idAnimal) "
                        + "VALUES (\""
                        + id_Tweet
                        + "\", \""
                        +  tweet_date
                        + "\", \" "
                        + text
                        + "\", \" "
                        + location
                        + "\", \" "
                        + twitterUser_idAccount
                        + "\", \" "
                        +  area_idArea
                        + "\", \" "
                        +  user_Name
                        + "\", \" "
                        +  followers
                        + "\", \" "
                        +  id_User
                        + "\", \" "
                        +  animal_idAnimal
                        + "\");";
            statement.executeUpdate(sql);
        }catch(SQLException ex){

            System.out.println("Niet ingevoerd. Er is een fout opgetreden.");
            System.out.println(ex);
        }

    }// end of insertIntoTable()

    public void insertWeatherData(Date date_Today, String rain, float averageTemperature, float minTemperature, float maxTemperature, String snow, String clouds, String wind){
            try {
                statement = connection.createStatement();
                String sql = "INSERT INTO Weather"
                        + "(date_Today, rain, averageTemperature, minTemperature, maxTemperature, snow, clouds, wind) "
                        + "VALUES ('"
                        + date_Today
                        + "', '"
                        + rain
                        + "', '"
                        + averageTemperature
                        + "', '"
                        + minTemperature
                        + "', '"
                        + maxTemperature
                        + "', ' "
                        + snow
                        + "' , '"
                        + clouds
                        + "', '"
                        + wind
                        + "');";
                statement.execute(sql);
            }catch(SQLException ex){
                //TODO
            }
    }//end of insertWeatherData()

    public void closeDatabase(){
        try {
            if(!connection.isClosed()){
                connection.close();
                System.out.println("Database closed");
            }
        }catch(SQLException ex){
        //TODO
        }
    }// end of closeDatabase()

}// end of class Database
