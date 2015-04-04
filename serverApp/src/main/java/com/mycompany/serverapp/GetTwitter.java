package com.mycompany.serverapp;

import twitter4j.*;

import java.sql.Date;
import java.sql.SQLException;

import java.util.ArrayList;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Elize on 10-3-2015.
 */
public class GetTwitter {

    //The Twitter instances:
    private ConfigurationBuilder cb = new ConfigurationBuilder();
    private Twitter twitterInstance = null;

    private String blijdorpQuery = "rotterdamzoo until:" + getDateToday();
    private Query query = new Query("rotterdamzoo , blijdorp");

    //Variables that are needed for gathering data:
    public User Blijdorp = null;
    Database database = new Database();
    private int area_idArea = 0;
    private int animal_idAnimal = 0;
    Status status = null;

    public GetTwitter() throws SQLException {

        setup();

    }

    public void setup() throws SQLException {
        cb.setOAuthConsumerKey("BiTybWWcHtZfNDF6k27XQysZo");
        cb.setOAuthConsumerSecret("SarY78keJf2LRJJS38xFbpjizvkFbKDxg0eULV5NJQ36TxJW07");
        cb.setOAuthAccessToken("3092334623-zofIrzyqSpVlnYHq42ozb2ubz9uU3d6D5CmcMfA");
        cb.setOAuthAccessTokenSecret("dTlH0Qa1kPbwXHCa5xkkRhkIyLeD2JvP2bqd1UhNeioID");
        //All keys are private! >:(
        twitterInstance = new TwitterFactory(cb.build()).getInstance();
        fetchAndDrawTweets();

    }// end of setup()

    private QueryResult fetchAndDrawTweets() throws SQLException {
        try {
            String user_Name;

            String message;

            Date date;

            long user_ID;

            long tweet_ID;

            int followers;

            String region = null;
            String country = null;
            QueryResult result = twitterInstance.search(query);
            ArrayList tweets = (ArrayList) result.getTweets();
            //System.out.println("Starting for loop tweets");
            if (tweets.isEmpty()) {
                System.out.println("Geen tweets");
                return null;
            }

            for (int i = 0; i < result.getCount(); i++) {
                //System.out.println(tweets.get(i));
                status = (Status) tweets.get(i);

                user_Name = status.getUser().getName();
                message = status.getText();

                //System.out.println(status.getGeoLocation() + "= geo data" );
                if (status.getGeoLocation() != null) {
                    double latitude = status.getGeoLocation().getLatitude();
                    double longitude = status.getGeoLocation().getLongitude();
                    GetLocation getLocation = new GetLocation(latitude, longitude);
                    region = getLocation.getRegion();
                    country = getLocation.getCountry();
                }

                java.util.Date dateUtil = status.getCreatedAt();
                date = new java.sql.Date(dateUtil.getTime());
                user_ID = status.getUser().getId();
                tweet_ID = status.getId();
                followers = status.getUser().getFollowersCount();
                String Animal = getAnimal(message);
                //System.out.println("Animal: " + Animal);
                String Area = getArea(message);
                        if(!Animal.equals("no animal") && Area.equals("no area")){
                        Area = getAreaByAnimal(Animal);
                        }

                try {
                    database.insertTweetIntoTable(tweet_ID, date, message, region, country, Area, user_Name, followers, user_ID, Animal);

                    ServerGUI.tweetUpdateCount++;
                } catch (Exception e) {
                    //System.out.println(e);
                }
            }
            return result;
        } catch (TwitterException te) {

            return null;
        }// end of catch
    }// end of fetchAndDrawTweets()

    public User GetUser() {
        try {
            Blijdorp = twitterInstance.showUser("rotterdamzoo");
        } catch (TwitterException t) {

        }// end of catch
        return Blijdorp;
    }// end of GetUser()

    private String getArea(String text){

        ArrayList<String> areasInDatabase = database.getArea();
            for (String s : areasInDatabase) {
                if (text.contains(s.toLowerCase()) || text.contains(s)){
                    return s;
                }
            }
       return "no area";
    }//end of getArea()


    private String getAnimal(String text){
        ArrayList<String> AnimalsInDatabase = database.getAnimal();
            for (String s : AnimalsInDatabase) {
                if (text.contains(s.toLowerCase()) || text.contains(s)){
                    return s;
                }
            }
        return "no animal";
    }//end of getAnimal()

    private Date getDateToday(){
        java.util.Date dateTodayUtil = new java.util.Date();
        Date dateToday = new java.sql.Date(dateTodayUtil.getTime());
        return dateToday;
    }// end of getDateToday()
    
    private String getAreaByAnimal(String animal){
        
        ArrayList<String> AreasInDatabase = database.getAreaByAnimal(animal);
            for (String s : AreasInDatabase) {
                return s;
            }
        return "no Area";
    }//end of getAreaByAnimal()

}// end of GetTwitter class
