/**
 * Created by Elize on 17-3-2015.
 */

package user;


import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Database {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public Database() throws SQLException{
        connectToDatabase();
        useDatabase();
    }//end of constructor

    private String connectToDatabase(){

        try {
            
            

           connection = (Connection) DriverManager.getConnection(
                    //The adres of the server
                   
                    "jdbc:mysql://145.24.222.198:8001/team_blij",
                    //username
                    "root",
                    //password
                    "blijdorp"
                    );

           //System.out.println("Connected to database!");
            return "U heeft verbinding";
            
        }catch(SQLException ex){
            System.out.println("Error connecting to database!");
            return "Er kon geen verbinding worden gemaakt.";

        }


    }//end of connectToDatabase()

    private void useDatabase() throws SQLException{
        
        
        statement = connection.createStatement();
        statement.executeUpdate("USE team_blij; ");

    }//end of useDatabase()


    public ResultSet getAllTweets() throws SQLException {
        statement = connection.createStatement();
        String sql = "SELECT * FROM tweet; ";
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }//end of getTable()

    public String insertTweetIntoTable(Long id_Tweet, Date tweet_date, String text, String region, String country, String Area, String user_Name, int followers, Long id_User, String Animal) throws SQLException{
        //System.out.println("Before the connection");
        statement = connection.createStatement();
        //System.out.println("After the connection");
        String sql = "INSERT INTO tweet"
                + "(Tweet_id, date, text, region, country, Area, User_Name, followers, id_User, Animal) "
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
        System.out.println("After the query");
        return "Ingevoert in database.";

    }// end of insertIntoTable()

    public String insertWeatherData(Date date_Today, String rain, float averageTemperature, float minTemperature, float maxTemperature, String snow, String clouds, String wind) throws SQLException{
        statement = connection.createStatement();
        statement.executeUpdate("DELETE FROM `team_blij`.`weather` WHERE `date`="+ date_Today +";");
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
        if(statement.isClosed()){
            System.out.println("Gesloten");
        }
        else{
          statement.execute(sql);
        }
        closeDatabase();
        return null;
    }//end of insertWeatherData()

    public String closeDatabase() throws SQLException{
        if(!connection.isClosed()){
            connection.close();
            return "Database closed.";
        }
        return null;
    }// end of closeDatabase()


    public ResultSet getAllWeatherData() throws SQLException{
        statement = connection.createStatement();
        String sql = "SELECT * FROM weather; ";
        resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    ArrayList<String> arrayListString = new ArrayList();
    
    public ArrayList getArea(){
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select Name From area;");
            while(resultSet.next()){
                arrayListString.add(resultSet.getString(1));
             }
   
            return arrayListString;
        }catch(SQLException ex){
            System.out.println("Failed to connect and give back area data.");
        
            return arrayListString;
        }

    }//end of getAreas();
    ArrayList<String> arrayListStringArea = new ArrayList();
    public ArrayList getAnimal(){
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("Select Animal From Animal;");
            while(resultSet.next()){
            arrayListStringArea.add(resultSet.getString(1));
        }
        return arrayListStringArea;
    }catch(SQLException ex){
        System.out.println("Failed to connect and give back animal data.");
        return arrayListStringArea;
    }
    }//end of getAnimal()
    
    ArrayList<String> arrayListString3 = new ArrayList();
    public ArrayList getAreaByAnimal(String animal){
        
        ResultSet resultset = null;
        try{
        statement = connection.createStatement();
        String sql = "SELECT Area FROM Animal where Animal = '"
                + animal
                + "'; ";
        resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
        arrayListString3.add(resultSet.getString(1));
        }
        return arrayListString3; 
        }catch(SQLException ex){
          return arrayListString3;      
        }
    
    }//end of getAreaByAnimal

}// end of class Database
