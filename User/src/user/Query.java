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
    String time = " date > NOW() - INTERVAL 1 Month ";
    Database database = new Database();
    
    
    public void setMonth(){
        time = " date > NOW() - INTERVAL 1 Month ";
    }
    
    public void setWeek(){
        time = " date > NOW() - INTERVAL 1 Week ";
    }
    
    public void setDay(){
        time = " date > NOW() - INTERVAL 1 Day ";
    }
    
    public ResultSet countTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET"
                +   "   WHERE"
                +       time
                +   "   GROUP BY TWEET_ID "
                +   ";"
                ;
        return executeQuery(query);
    }//end of countTweets()
    
    public ResultSet executeQuery(String query){
        return database.query(query);
    }//end of executeQuery()
    
    
    public ResultSet getBestAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND "
                    +       time    
                    +   "   group by Area "
                    +   "   order by count(Area) desc;"
                    ;
            return executeQuery(query);
    }
    
    public ResultSet getWorstAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND"
                    +       time
                    +   "   group by Area"
                    +   "   order by count(Area); "
                    ;
            return executeQuery(query);
    
    
    
    }
    
    public ResultSet getBestAnimal(){
            query =     "   SELECT Animal, count(Animal)"
                    +   "   from tweet"
                    +   "   WHERE Animal NOT LIKE '%animal%'"
                    +   "   AND"
                    +       time
                    +   "   GROUP BY Animal;"
                    ;
 
        return executeQuery(query);
    
    
    }
    
    public ResultSet getCountry(){
        query =     "   SELECT COUNT(*), COUNTRY "
                +   "   FROM TWEET"
                +   "   WHERE"
                +       time
                +   "   GROUP BY COUNTRY;"
                ;
        return executeQuery(query);
    }//end of getCountry()
    
    public ResultSet getRegion(){
        query =     "   SELECT COUNT(*), REGION"
                +   "   FROM TWEET"
                +   "   WHERE "
                +       time
                +   "   GROUP BY REGION;"
                ;
        return executeQuery(query);
    }
    
    public ResultSet getRainAndTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN =  'true'"
                +   "   AND "
                +       time
                +   "   AND TWEET.DATE = WEATHER.DATE;"
                ;
        return executeQuery(query);
    }
    
    public ResultSet getGoodWeatherAndTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN = 'false' "
                +   "   AND WEATHER.SNOW = 'false' "
                +   "   AND TWEET.DATE = WEATHER.DATE "
                +   "   AND "
                +       time 
                +   ";"
                ;    
        return executeQuery(query);
    }
    
    public ResultSet getLowTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE <= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;
         return executeQuery(query);
    }
    
    public ResultSet getHighTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE >= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;       
         return executeQuery(query);
    }
    
    public ResultSet getBlijdorpTweetsAndDate(){
                 query =    "   SELECT COUNT(*), DATE"
                         +  "   FROM TWEET"
                         +  "   WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "   AND "
                         +      time
                         +  "   GROUP BY DATE"
                         ;      
         return executeQuery(query);
    }
    
    public ResultSet getBlijdorpTweets(){
                 query =         "   SELECT "
                            +    "    ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE AREA != ' no area' "
                            +    " AND ANIMAL = ' no animal' "
                            +    " AND User_name = ' Diergaarde Blijdorp') AS Tweets_About_Areas,"
                            +    " AND "
                            +      time    
                            +    " ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE  ANIMAL != ' no animal' "
                            +    " AND User_name = ' Diergaarde Blijdorp') "
                            +    " AND "
                            +      time    
                            +    " AS Tweets_About_Animals,"
                            +    " ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE  ANIMAL = ' no animal' "
                            +    " AND  AREA = ' no area' "
                            +    " AND User_name = ' Diergaarde Blijdorp') "
                            +    " AND "
                            +      time
                            +    " AS 'Other_Tweets'"
                            +    " ;"
                         ;  
         return executeQuery(query);
    }
    
    public ResultSet getFollowersBlijdorp(){
                 query =    " SELECT FOLLOWERS, DATE"
                         +  " FROM TWEET"
                         +  " WHERE User_name = ' Diergaarde Blijdorp'"
                         +  " AND"
                         +    time
                         +  " GROUP BY DATE;"
                         ;  
         return executeQuery(query);        
    }
}//end of Query
