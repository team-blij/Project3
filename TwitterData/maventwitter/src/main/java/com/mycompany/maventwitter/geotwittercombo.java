/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maventwitter;

/**
 *
 * @author Hans
 */
import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
//import com.twitter.hbc.SitestreamController;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;

import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;

import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.JSONArray;

import org.json.JSONException;
import org.json.JSONObject;

public class geotwittercombo {

    public static Authentication hosebirdAuth = new OAuth1("hYiZEb7KIq8xRzouCcHMHV8KG", "KwBo9KEfb28iqx4MCzJLyfKiIUoKSY68Nw9un5xGKWSjb6MK8m", "543574610-XMJxyBrK7rIpWoSAZwy2je3EXdsIUfiwtW0vvGX2", "PTfk8qYKw711DQv9jsW6OTjQSZrUr9mw3M6JfP2QNCjIF");

    static JSONObject obj = new JSONObject();

    /**
     *
     * @param args
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, JSONException, IOException {

        //twitter4jmodule();
        extraTestModule();
                //JsonReader.readJsons();
        //connectToAndQueryDatabase();

    }

    /**
     *
     * @throws InterruptedException
     * @throws org.json.JSONException
     */
    public static void extraTestModule() throws InterruptedException, JSONException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10000);
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        // add some track terms
        endpoint.trackTerms(Lists.newArrayList("how do you"));

        // Authentication auth = new BasicAuth(username, password);
        // Create a new BasicClient. By default gzip is enabled.
        Client cleint01 = new ClientBuilder()
                .hosts(Constants.STREAM_HOST)
                .endpoint(endpoint)
                .authentication(hosebirdAuth)
                .processor(new StringDelimitedProcessor(queue))
                .build();

        // Establish a connection
        cleint01.connect();
        JSONObject json;
        JSONObject userJson;
        JSONObject userGeo;
        double text = 0;
        double noText = 0;
        int geo = 0;
        int nogeo = 0;
        String city = null;
        // Do whatever needs to be done with messages
        for (int msgRead = 0; msgRead < 10000; msgRead++) {
            String msg = queue.take();
            //System.out.println(msg);

            json = new JSONObject(msg);

//            System.out.println(json.toString());
            try {
                String twitterText = json.getString("text");

                userJson = json.getJSONObject("user");

                

                String twitterName = userJson.getString("name");

                //System.out.println(twitterName + ": " + twitterText);

                String twitterGeo = json.getString("geo");

                if (twitterGeo.equals("null")) {
                    nogeo++;
                } else {

                    userGeo = new JSONObject(twitterGeo);

                    JSONArray coordinates = userGeo.getJSONArray("coordinates");

                    String lat = coordinates.getString(0);
                    String lng = coordinates.getString(1);

//                    System.out.print("coordinaten: ");
//                    
//                    System.out.print(coordinates.get(0));
//                    System.out.print(coordinates.get(1));
                    JSONObject gmaps = JsonReader.readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false");
                    JSONArray results = gmaps.getJSONArray("results");
                    JSONObject results2 = results.getJSONObject(0);
                    JSONArray adresscomp = results2.getJSONArray("address_components");

                    int arrayLenght = adresscomp.length();
                    for (int i = 0; i < arrayLenght - 1; i++) {
                        JSONObject cityObject = adresscomp.getJSONObject(i);
                        JSONArray types = cityObject.getJSONArray("types");
                        String typeString = types.getString(0);
                        if (typeString.equals("locality")) {
                            city = cityObject.getString("long_name");
                            break;
                        }

                    }

                    System.out.println(city);

                    geo++;

                }
                text++;

                //System.out.println(twitterGeo);
            } catch (Exception e) {

                noText++;
            }

        }

        cleint01.stop();
        double totalTweets = text + noText;
        double percentage = text / totalTweets * 100;
        System.out.println("Of the " + totalTweets + " tweets, " + text + " had text,that is " + percentage + "% of the tweets.");
        System.out.println("Of those " + text + " tweets, " + geo + " had geodata. That is "+geo/text*100 +"% of the tweets.");

    }

    public static void connectToAndQueryDatabase() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/twitterdata", "hans", "wachtwoord");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, twittername, twittertext FROM BlijdorpTweets");

        while (rs.next()) {
            int x = rs.getInt("id");
            String s = rs.getString("twittername");
            String f = rs.getString("twittertext");
        }
    }

}
