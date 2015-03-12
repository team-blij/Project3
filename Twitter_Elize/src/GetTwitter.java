import twitter4j.*;
import twitter4j.conf.*;

import java.util.ArrayList;


/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    Twitter twitterInstance;
    Query query;


    public void setup(){
        cb.setOAuthConsumerKey("hYiZEb7KIq8xRzouCcHMHV8KG");
        cb.setOAuthConsumerSecret("KwBo9KEfb28iqx4MCzJLyfKiIUoKSY68Nw9un5xGKWSjb6MK8m");
        cb.setOAuthAccessToken("543574610-XMJxyBrK7rIpWoSAZwy2je3EXdsIUfiwtW0vvGX2");
        cb.setOAuthAccessTokenSecret("PTfk8qYKw711DQv9jsW6OTjQSZrUr9mw3M6JfP2QNCjIF");
        //All keys are from maventwitter
        twitterInstance = new TwitterFactory(cb.build()).getInstance();
        query = new Query("from:rotterdamzoo" );



        fetchAndDrawTweets();
    }// end of setup()

    public void fetchAndDrawTweets(){
        try{
            QueryResult result = twitterInstance.search(query);
            ArrayList tweets = (ArrayList) result.getTweets();
            for (int i = 0; i < tweets.size(); i++){
                Status status = (Status) tweets.get(i);
                //Information about date, id, source, if it is favorited, enz.
                String user = status.getUser().getName();
                //The name of the user
                String message = status.getText();
                //The tweet
                System.out.println(status + " " + user + " " + message);

            }
        }catch(TwitterException te){
            System.out.println("Couldn't connect!");
        }// end of catch
    }// end of fetchAndDrawTweets()


}
