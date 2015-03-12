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
//import com.mycompany.maventwitter.WeatherData.WeatherCondition;
import com.twitter.hbc.ClientBuilder;
//import com.twitter.hbc.SitestreamController;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.event.Event;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import java.io.IOException;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;







public class twitterjsonparse {

    public static Authentication hosebirdAuth = new OAuth1("hYiZEb7KIq8xRzouCcHMHV8KG", "KwBo9KEfb28iqx4MCzJLyfKiIUoKSY68Nw9un5xGKWSjb6MK8m", "543574610-XMJxyBrK7rIpWoSAZwy2je3EXdsIUfiwtW0vvGX2", "PTfk8qYKw711DQv9jsW6OTjQSZrUr9mw3M6JfP2QNCjIF");

    static JSONObject obj = new JSONObject();
    private static Object client;

    /**
     *
     * @param args
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, IOException{
        //twitter4jmodule();
        extraTestModule();

//        OwmClient owm = new OwmClient();
//        WeatherStatusResponse currentWeather = owm.currentWeatherAtCity("Rotterdam", "NL");
//        if (currentWeather.hasWeatherStatus()) {
//            WeatherData weather = currentWeather.getWeatherStatus().get(0);
//            if (weather.getPrecipitation() == Integer.MIN_VALUE) {
//                WeatherCondition weatherCondition = weather.getWeatherConditions().get(0);
//                String description = weatherCondition.getDescription();
//                if (description.contains("rain") || description.contains("shower")) {
//                    System.out.println("No rain measures in Tokyo but reports of " + description);
//                } else {
//                    System.out.println("No rain measures in Tokyo: " + description);
//                }
//            } else {
//                System.out.println("It's raining in Tokyo: " + weather.getPrecipitation() + " mm/h");
//            }
//        }
    }

    public static void twitter4jmodule() {
        /**
         * Set up your blocking queues: Be sure to size these properly based on
         * expected TPS of your stream
         */
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(100000);
        BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<>(1000);

        /**
         * Declare the host you want to connect to, the endpoint, and
         * authentication (basic auth or oauth)
         */
        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();
// Optional: set up some followings and track terms
        List<Long> followings = Lists.newArrayList(1234L, 566788L);
        List<String> terms = Lists.newArrayList("twitter", "api");
        hosebirdEndpoint.followings(followings);
        hosebirdEndpoint.trackTerms(terms);

// These secrets should be read from a config file
        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01") // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue);                          // optional: use this if you want to process client events

        Client hosebirdClient = builder.build();
// Attempts to establish a connection.
        hosebirdClient.connect();
        int i = 0;
        while (i < 10) {
            String msg = null;
            try {
                i++;
                msg = msgQueue.take();
            } catch (InterruptedException ex) {
                Logger.getLogger(twitterjsonparse.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(msg);

        }
        hosebirdClient.stop();
    }

    public static void newParser() {

    }

    public static void newObject(){
        ArrayList<JSONObject> clients = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            clients.add(obj);

            obj = new JSONObject();
            obj.put("name", "foo");
            obj.put("ID", i);
            obj.put("balance", 1000.21);
            obj.put("is_vip", true);
            obj.put("nickname", "client " + i);

        }
        System.out.print(clients);
    }

    /**
     *
     * @throws InterruptedException
     */
    public static void extraTestModule() throws InterruptedException{
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(10000);
        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
        // add some track terms
        endpoint.trackTerms(Lists.newArrayList("blijdorp", "blij dorp", "@rotterdamzoo", "rotterdamzoo","twitter"));

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
       
        // Do whatever needs to be done with messages
        for (int msgRead = 0; msgRead < 1000; msgRead++) {
            String msg = queue.take();
            System.out.println(msg);
            Object obj01=JSONValue.parse(msg);
            JSONArray array=(JSONArray)obj01;
            System.out.println(array.get(1));
             
            
        }

        cleint01.stop();
    }

}
