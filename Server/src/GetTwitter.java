import twitter4j.*;
import twitter4j.conf.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {
    //The Twitter instances:
    ConfigurationBuilder cb = new ConfigurationBuilder();
    public Twitter twitterInstance = null;
    public Query query = new Query("rotterdamzoo" + "Blijdorp");

    //Variables that are needed for gathering data:
    public User Blijdorp = null;
    Database database = new Database();
    private int area_idArea = 0;
    private int animal_idAnimal = 0;
    Status status = null;
    //Information about date, id, source, if it is favorited, enz.
    String user_Name = null;
    //The name of the user
    String message = null;
    //The tweet
    String geoLocation = null;
    //the location of the user
    java.util.Date dateUtil = null;
    Date date = null;
    //the date
    long user_ID = 0;
    // user ID
    long tweet_ID = 0;
    //tweet ID
    int followers = 0;
    //The amount of followers the user has


    public GetTwitter(){
        try {
            setup();
        }finally {
            database.closeDatabase();
        }

    }
    private void setup(){
            cb.setOAuthConsumerKey("BiTybWWcHtZfNDF6k27XQysZo");
            cb.setOAuthConsumerSecret("SarY78keJf2LRJJS38xFbpjizvkFbKDxg0eULV5NJQ36TxJW07");
            cb.setOAuthAccessToken("3092334623-zofIrzyqSpVlnYHq42ozb2ubz9uU3d6D5CmcMfA");
            cb.setOAuthAccessTokenSecret("dTlH0Qa1kPbwXHCa5xkkRhkIyLeD2JvP2bqd1UhNeioID");
            //All keys are private! >:(
            twitterInstance = new TwitterFactory(cb.build()).getInstance();
            fetchAndDrawTweets();

    }// end of setup()

    private QueryResult fetchAndDrawTweets(){
        try{
            QueryResult result = twitterInstance.search(query);
            ArrayList tweets = (ArrayList) result.getTweets();
            for (int i = 0; i < result.getCount(); i++){
                status = (Status) tweets.get(i);
                user_Name = status.getUser().getName();
                message = status.getText();
                geoLocation = status.getUser().getLocation();
                java.util.Date dateUtil = status.getCreatedAt();
                date = new java.sql.Date(dateUtil.getTime());
                user_ID = status.getUser().getId();
                tweet_ID = status.getId();
                followers = status.getUser().getFollowersCount();

                database.insertTweetIntoTable(tweet_ID, date, message, geoLocation, user_ID, null, user_Name, followers, user_ID, null );
            }
            return result;
        }catch(TwitterException te){
            System.out.println("Couldn't connect!");
            return null;
        }// end of catch
    }// end of fetchAndDrawTweets()

    public User GetUser(){
        try {
            Blijdorp = twitterInstance.showUser("rotterdamzoo");
        }catch(TwitterException t){
        System.out.println("User not found!");
        }// end of catch
        return Blijdorp;
    }// end of GetUser()

}// end of GetTwitter class
