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
    //Database database = new Database();
    
    public String countTweets(){
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET"
                +   "GROUP BY TWEET_ID;"
                ;
        return query;
    }//end of countTweets()
    
    public void executeQuery(){
        //database.query(query);
    }//end of executeQuery()
    
    
    public void getBestAsset(){
    
    
    }
    
    public void getWorstAsset(){
    
    }
    
    public void getBestAnimal(){
        
    }
    
    public String getCountry(){
        query =     "SELECT COUNT(*), COUNTRY "
                +   "FROM TWEET"
                +   "GROUP BY COUNTRY;"
                ;
        return query;
    }//end of getCountry()
    
    public String getRegion(){
        query =     "SELECT COUNT(*), REGION"
                +   "FROM TWEET"
                +   "GROUP BY REGION;"
                ;
        return query;
    }
    
    public String getRainAndTweets(){
        //TODO
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN =  'true'"
                +   "AND TWEET.DATE = WEATHER.DATE;"
                ;
        return query;
    }
    
    public String getGoodWeatherAndTweets(){
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN = 'false' "
                +   "AND WEATHER.SNOW = 'false'; "
                +   "AND TWEET.DATE = WEATHER.DATE"
                ;    
        return query;
    }
    
    public String getLowTemperatureAndTweets(){
         query =    "SELECT COUNT(*)"
                 +  "FROM TWEET, WEATHER"
                 +  "WHERE AVERAGETEMPERATURE <= 10"
                 +  "AND TWEET.DATE = WEATHER.DATE;"
                 ;
         return query;
    }
    
    public String getHighTemperatureAndTweets(){
         query =    "SELECT COUNT(*)"
                 +  "FROM TWEET, WEATHER"
                 +  "WHERE AVERAGETEMPERATURE >= 10"
                 +  "AND TWEET.DATE = WEATHER.DATE;"
                 ;       
         return query;
    }
    
    public String getBlijdorpTweetsAndDate(){
                 query =    "SELECT COUNT(*), DATE"
                         +  "FROM TWEET"
                         +  "WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "GROUP BY DATE"
                         ;      
         return query;
    }
    
    public String getBlijdorpTweets(){
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
         return query;
    }
    
    public String getFollowersBlijdorp(){
                 query =    "SELECT FOLLOWERS, DATE"
                         +  "FROM TWEET"
                         +  "WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "GROUP BY DATE;"
                         ;  
         return query;        
    }
}//end of Query
