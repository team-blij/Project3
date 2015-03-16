import twitter4j.*;
import twitter4j.conf.*;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    public Twitter twitterInstance;
    public Query query = new Query("rotterdamzoo :)" + "Blijdorp");

    public User Blijdorp = null;
    String[] blijdorpAssets = {"rotterdamzoo oceanium " , "@rotterdamzoo rivierahal :)"};



    public void setup(){
            cb.setOAuthConsumerKey("BiTybWWcHtZfNDF6k27XQysZo");
            cb.setOAuthConsumerSecret("SarY78keJf2LRJJS38xFbpjizvkFbKDxg0eULV5NJQ36TxJW07");
            cb.setOAuthAccessToken("3092334623-zofIrzyqSpVlnYHq42ozb2ubz9uU3d6D5CmcMfA");
            cb.setOAuthAccessTokenSecret("dTlH0Qa1kPbwXHCa5xkkRhkIyLeD2JvP2bqd1UhNeioID");
            //All keys are private! >:(
            twitterInstance = new TwitterFactory(cb.build()).getInstance();
            getBestAsset();
            //fetchAndDrawTweets();

    }// end of setup()

    private void fetchAndDrawTweets(){
        try{
            QueryResult result = twitterInstance.search(query);
            ArrayList tweets = (ArrayList) result.getTweets();
            for (int i = 0; i < result.getCount(); i++){
                Status status = (Status) tweets.get(i);
                //Information about date, id, source, if it is favorited, enz.
                String user = status.getUser().getName();
                //The name of the user
                String message = status.getText();
                //The tweet
                String geoLocation = status.getUser().getLocation();
                //the location of the user
                System.out.println(status + " " + user + " " + message);
            }
        }catch(TwitterException te){
            System.out.println("Couldn't connect!");
        }// end of catch
    }// end of fetchAndDrawTweets()

    public User GetUser(){
        try {
            Blijdorp = twitterInstance.showUser("rotterdamzoo");

        }catch(TwitterException t){
        System.out.println("User not found!");
        }
        return Blijdorp;
    }// end of GetUser()


//  Sentiment analyse:
//  --------------------------------------------------------------------------------------------------------------------
//  Welk onderdeel van diergaarde Blijdorp vinden de bezoekers het leukst?
    private void getBestAsset() {

        try {
            for (String p : blijdorpAssets) {
                query = new Query(p);
                fetchAndDrawTweets();
            }

        }catch(IndexOutOfBoundsException ex){
            System.out.println("Geen resultaten gevonden...");

        }
    }// end of getBestAsset

}// end of GetTwitter class
