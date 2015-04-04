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
    //Database database = new Database();
    
    
    public void setMonth(){
        time = " date > NOW() - INTERVAL 1 Month ";
    }
    
    public void setWeek(){
        time = " date > NOW() - INTERVAL 1 Week ";
    }
    
    public void setDay(){
        time = " date > NOW() - INTERVAL 1 Day ";
    }
    
    public String countTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET"
                +   "   WHERE"
                +       time
                +   "   GROUP BY TWEET_ID "
                +   ";"
                ;
        return query;
    }//end of countTweets()
    
    public void executeQuery(){
        //database.query(query);
    }//end of executeQuery()
    
    
    public String getBestAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND "
                    +       time    
                    +   "   group by Area "
                    +   "   order by count(Area) desc;"
                    ;
            return query;
    }
    
    public String getWorstAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND"
                    +       time
                    +   "   group by Area"
                    +   "   order by count(Area); "
                    ;
            return query;
    
    
    
    }
    
    public String getBestAnimal(){
            query =     "   SELECT Animal, count(Animal)"
                    +   "   from tweet"
                    +   "   WHERE Animal NOT LIKE '%animal%'"
                    +   "   AND"
                    +       time
                    +   "   GROUP BY Animal;"
                    ;
 
        return query;
    
    
    }
    
    public String getCountry(){
        query =     "   SELECT COUNT(*), COUNTRY "
                +   "   FROM TWEET"
                +   "   WHERE"
                +       time
                +   "   GROUP BY COUNTRY;"
                ;
        return query;
    }//end of getCountry()
    
    public String getRegion(){
        query =     "   SELECT COUNT(*), REGION"
                +   "   FROM TWEET"
                +   "   WHERE "
                +       time
                +   "   GROUP BY REGION;"
                ;
        return query;
    }
    
    public String getRainAndTweets(){
        //TODO
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN =  'true'"
                +   "   AND "
                +       time
                +   "   AND TWEET.DATE = WEATHER.DATE;"
                ;
        return query;
    }
    
    public String getGoodWeatherAndTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN = 'false' "
                +   "   AND WEATHER.SNOW = 'false' "
                +   "   AND TWEET.DATE = WEATHER.DATE "
                +   "   AND "
                +       time 
                +   ";"
                ;    
        return query;
    }
    
    public String getLowTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE <= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;
         return query;
    }
    
    public String getHighTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE >= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;       
         return query;
    }
    
    public String getBlijdorpTweetsAndDate(){
                 query =    "   SELECT COUNT(*), DATE"
                         +  "   FROM TWEET"
                         +  "   WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "   AND "
                         +      time
                         +  "   GROUP BY DATE"
                         ;      
         return query;
    }
    
    public String getBlijdorpTweets(){
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
         return query;
    }
    
    public String getFollowersBlijdorp(){
                 query =    " SELECT FOLLOWERS, DATE"
                         +  " FROM TWEET"
                         +  " WHERE User_name = ' Diergaarde Blijdorp'"
                         +  " AND"
                         +    time
                         +  " GROUP BY DATE;"
                         ;  
         return query;        
    }
}//end of Query
