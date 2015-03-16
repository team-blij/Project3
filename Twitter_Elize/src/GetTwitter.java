import twitter4j.*;
import twitter4j.conf.*;

import java.util.ArrayList;


/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    public Twitter twitterInstance;
    public Query query;
    public User Blijdorp = null;
    private ArrayList<String> blijdorpAssets= new ArrayList<String>();
    public void getQuery(){
        query = new Query("rotterdamzoo " + "Blijdorp");
        setup();

    }

    public void setup(){

            cb.setOAuthConsumerKey("hYiZEb7KIq8xRzouCcHMHV8KG");
            cb.setOAuthConsumerSecret("KwBo9KEfb28iqx4MCzJLyfKiIUoKSY68Nw9un5xGKWSjb6MK8m");
            cb.setOAuthAccessToken("543574610-XMJxyBrK7rIpWoSAZwy2je3EXdsIUfiwtW0vvGX2");
            cb.setOAuthAccessTokenSecret("PTfk8qYKw711DQv9jsW6OTjQSZrUr9mw3M6JfP2QNCjIF");
            //All keys are from maventwitter
            twitterInstance = new TwitterFactory(cb.build()).getInstance();

            fetchAndDrawTweets();

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

    private void getBestAsset(){
        for(String p: blijdorpAssets)
        query = new Query("@rotterdamzoo :) " +  p);

    }

}// end of GetTwitter class