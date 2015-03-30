/**
 * Created by Elize on 17-3-2015.
 */

import java.sql.*;
import java.util.ArrayList;


public class Database {
    private Connection connection = null;
    private Statement statement = null;
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
            statement.executeUpdate("USE data_twitter; ");
        }catch (SQLException ex){
        }

    }//end of useDatabase()


    public ResultSet getAllTweets() {
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM tweet; ";
            resultSet = statement.executeQuery(sql);
            return resultSet;
        }catch(SQLException ex){
            return null;
        }
    }//end of getTable()

    public void insertTweetIntoTable(Long id_Tweet, Date tweet_date, String text, String region, String country, String Area, String user_Name, int followers, Long id_User, String Animal){
        try {
            statement = connection.createStatement();
                String sql = "INSERT INTO tweet"
                        + "(id_Tweet, date, text, region, country, Area, user_Name, followers, id_User, Animal) "
                        + "VALUES (\""
                        + id_Tweet
                        + "\", \""
                        +  tweet_date
                        + "\", \" "
                        + text
                        + "\", \" "
                        + region
                        + "\", \" "
                        + country
                        + "\", \" "
                        +  Area
                        + "\", \" "
                        +  user_Name
                        + "\", \" "
                        +  followers
                        + "\", \" "
                        +  id_User
                        + "\", \" "
                        +  Animal
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
                        + "(date, rain, averageTemperature, lowTemperature, highTemperature, snow, clouds, wind) "
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
                System.out.println("Niet ingevoerd.");
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


    public ResultSet getAllWeatherData(){
        try {
            statement = connection.createStatement();
            String sql = "SELECT * FROM weather; ";
            resultSet = statement.executeQuery(sql);
            return resultSet;
        }catch(SQLException ex){
            return null;
        }

    }

    public ResultSet UserQuery(String sql){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            return resultSet;
        }catch(SQLException ex){
            System.out.println("Geen geldige invoer!");
            return null;
        }

    }//get UserQuery();

    public ArrayList getArea(){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select Area From area;");
            Array resultStringArray = (Array) resultSet.getArray("Area");
            ArrayList<String> arrayListString = null;
            int count = 0;
            while(resultSet.next()){
                arrayListString.add(resultSet.getString(count));
                count++;
            }
            return arrayListString;
        }catch(SQLException ex){
            System.out.println("Geen geldige invoer!");
            return null;
        }

    }//end of getAreas();

    public ArrayList getAnimal(){
        try{
        statement = connection.createStatement();
        resultSet = statement.executeQuery("Select Animal From Animal;");
        Array resultStringArray = (Array) resultSet.getArray("Animal");
        ArrayList<String> arrayListString = null;
        int count = 0;
        while(resultSet.next()){
            arrayListString.add(resultSet.getString(count));
            count++;
        }
        return arrayListString;
    }catch(SQLException ex){
        System.out.println("Geen geldige invoer!");
        return null;
    }
    }//end of getAnimal()

}// end of class Database
