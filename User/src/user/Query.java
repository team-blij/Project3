/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;
import java.sql.*;

/**
 *
 * @author School
 */
public class Query {
    String query = null;
    Database database = new Database();
    
    public void countTweets(){
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET"
                +   "GROUP BY TWEET_ID;"
                ;
    }//end of countTweets()
    
    public void executeQuery(){
        database.query(query);
    }//end of executeQuery()
    
    
    public void getBestAsset(){
    
    
    }
    
    public void getWorstAsset(){
    
    }
    
    public void getBestAnimal(){
        
    }
    
    public void getCountry(){
        query =     "SELECT COUNT(*), COUNTRY "
                +   "FROM TWEET"
                +   "GROUP BY COUNTRY;"
                ;
    }//end of getCountry()
    
    public void getRegion(){
        query =     "SELECT COUNT(*), REGION"
                +   "FROM TWEET"
                +   "GROUP BY REGION;"
                ;
    }
    
    public void getRainAndTweets(){
        //TODO
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN =  'true'"
                +   "AND TWEET.DATE = WEATHER.DATE;"
                ;
    }
    
    public void getGoodWeatherAndTweets(){
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN = 'false' "
                +   "AND WEATHER.SNOW = 'false'; "
                +   "AND TWEET.DATE = WEATHER.DATE"

                ;    
    }
    
    public void getLowTemperatureAndTweets(){
         query =    "SELECT COUNT(*)"
                 +  "FROM TWEET, WEATHER"
                 +  "WHERE AVERAGETEMPERATURE <= 10"
                 +  "AND TWEET.DATE = WEATHER.DATE;"
                 ;
    }
    
    public void getHighTemperatureAndTweets(){
         query =    "SELECT COUNT(*)"
                 +  "FROM TWEET, WEATHER"
                 +  "WHERE AVERAGETEMPERATURE >= 10"
                 +  "AND TWEET.DATE = WEATHER.DATE;"
                 ;       
    }
    
    public void getBlijdorpTweetsAndDate(){
                 query =    "SELECT COUNT(*), DATE"
                         +  "FROM TWEET"
                         +  "WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "GROUP BY DATE"
                         ;                                       
    }
    
    public void getBlijdorpTweets(){
                 query =    "SELECT "
                         + "("
                            + "SELECT count(*) "
                            + "FROM Tweet "
                            + "WHERE AREA != ' no area' "
                            + "AND ANIMAL = ' no animal' "
                            + "AND User_name = ' Diergaarde Blijdorp') AS Tweets_About_Areas,"
                         + "("
                            + "SELECT count(*) "
                            + "FROM Tweet "
                            + "WHERE  ANIMAL != ' no animal' "
                            + "AND User_name = ' Diergaarde Blijdorp') "
                            + "AS Tweets_About_Animals,"
                         + "("
                            + "SELECT count(*) "
                             + "FROM Tweet "
                             + "WHERE  ANIMAL = ' no animal' "
                             + "AND  AREA = ' no area' "
                             + "AND User_name = ' Diergaarde Blijdorp') "
                             + "AS 'Other_Tweets'"
                         + ";"
                         ;    
    }
    
    public void getFollowersBlijdorp(){
                 query =    "SELECT FOLLOWERS, DATE"
                         +  "FROM TWEET"
                         +  "WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "GROUP BY DATE;"
                         ;      
    }
}//end of Query
