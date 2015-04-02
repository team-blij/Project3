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
                +   "GROUP BY COUNTRY;"
                ;
    }
    
    public void getRainAndTweets(){
        //TODO
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN =  'true'"
                +   "GROUP BY TWEET_ID;"
                ;
    }
    
    public void getGoodWeatherAndTweets(){
        query =     "SELECT COUNT(*)"
                +   "FROM TWEET, WEATHER"
                +   "WHERE WEATHER.RAIN = 'false' "
                +   "AND WEATHER.SNOW = 'false';   "
                ;    
    }
    
    public void getLowTemperatureAndTweets(){
        
    }
    
    public void getHighTemperatureAndTweets(){
    
    }
    
    public void getBlijdorpTweetsAndDate(){
    
    }
    
    public void getBlijdorpTweets(){
    
    }
    
    public void getFollowersBlijdorp(){
    
    }
}//end of Query
