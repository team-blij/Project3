import twitter4j.*;
import twitter4j.conf.*;


import java.util.ArrayList;


/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    public Twitter twitterInstance;
    public Query query = new Query("rotterdamzoo :)" + "Blijdorp");

    public User Blijdorp = null;
    String[] blijdorpAssets = {"rotterdamzoo oceanium" , "rotterdamzoo rivierahal"};
    String[] animals = {};



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

    private QueryResult fetchAndDrawTweets(){
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
        }
        return Blijdorp;
    }// end of GetUser()


//  Sentiment analyse:
//  --------------------------------------------------------------------------------------------------------------------
//  Welk onderdeel van diergaarde Blijdorp vinden de bezoekers het leukst?
    private void getBestAsset() {

        try {
            for (String p : blijdorpAssets) {
                query = new Query(p + ":)");
                ArrayList tweets = (ArrayList) fetchAndDrawTweets();

            }
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Geen resultaten gevonden...");

        }
    }// end of getBestAsset
//  Welk onderdeel van diergaarde Blijdorp vinden de bezoekers het minst leukst?
    public void getWorstAspect(){
        try {
            for (String p : blijdorpAssets) {
                query = new Query(p + ":(");
                ArrayList tweets = (ArrayList) fetchAndDrawTweets();

            }
        }catch(IndexOutOfBoundsException ex){
            System.out.println("Geen resultaten gevonden...");

        }

    }//end of getWorstAsset()


//  Wat zijn de populairste dieren in diergaarde Blijdorp?
    public void getBestAnimal(){
        for(String i: animals){
            query = new Query(i);
            ArrayList tweets = (ArrayList) fetchAndDrawTweets();
            //There are no animals in the animals Array yet!

        }//end of for loop

    }//end of getBestAnimal()

//  Geografische analyse:
//  --------------------------------------------------------------------------------------------------------------------
//  Uit welk land buiten Nederland komen de meeste bezoekers?
    public void getCountry(){
            query = new Query("diergaarde Blijdorp" + "@rotterdamzoo");
            ArrayList tweets = (ArrayList) fetchAndDrawTweets();
            for(int i = 0; i < tweets.size(); i++){
                Status tweet = (Status)tweets.get(i);
                String location = null;
            }// end of for loop


    }//end of getCountry() NEEDS EDITING!

//  Uit welke provincie in Nederland komen de meeste bezoekers?
    public void getProvince(){
        query = new Query("diergaarde Blijdorp" + "@rotterdamzoo");
        ArrayList tweets = (ArrayList) fetchAndDrawTweets();
        for(int i = 0; i < tweets.size(); i++) {
            Status tweet = (Status) tweets.get(i);
            String location = null;
        }// end of for loop
    }//end of getProvince() NEEDS EDITING!


//    ++Meteorologische analyse:
//    ------------------------------------------------------------------------------------------------------------------
//    Heeft regenachtig weer een negatief effect op het aantal bezoeker?
    public void getBadWeather(){

    }// end of getBadWeather()

    //            	Heeft zonnig weer een positief effect op het aantal bezoekers?
    public void getGoodWeather(){

    }//end of getGoodWeather()

    //            	Heeft een lage temperatuur een negatief effect op het aantal bezoekers?
    public void getLowTemperature(){

    }// end of getLowTemperature()

    //            	Heeft een hoge temperatuur een negatief effect op het aantal bezoekers?
    public void getHighTemperature(){

    }// end of getHighTemperature()

}// end of GetTwitter class
