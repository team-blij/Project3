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
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.Location;
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

import org.json.JSONException;
import org.json.JSONObject;

public class twitterjsonparse {

    public static Authentication hosebirdAuth = new OAuth1("hYiZEb7KIq8xRzouCcHMHV8KG", "KwBo9KEfb28iqx4MCzJLyfKiIUoKSY68Nw9un5xGKWSjb6MK8m", "543574610-XMJxyBrK7rIpWoSAZwy2je3EXdsIUfiwtW0vvGX2", "PTfk8qYKw711DQv9jsW6OTjQSZrUr9mw3M6JfP2QNCjIF");

    static JSONObject obj = new JSONObject();
    private static Object client;

    /**
     *
     * @param args
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException, JSONException, IOException {
        //twitter4jmodule();
        extraTestModule();
        //JsonReader.readJsons();
    }

    public static void waetherdata() {

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
        endpoint.trackTerms(Lists.newArrayList("blijdorp","@rotterdamzoo","#blijdorp"));

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
        double text = 0;
        double noText = 0;
        // Do whatever needs to be done with messages
        for (int msgRead = 0; msgRead < 10; msgRead++) {
            String msg = queue.take();
            //System.out.println(msg);

            json = new JSONObject(msg);
            
            //System.out.println(json.toString());
            
            try{
            String twitterText = json.getString("text");
            
            String twitterUser = json.getString("user");
            
            
            userJson = new JSONObject(twitterUser);
            
            String twitterName = userJson.getString("name");
            
            System.out.println(twitterName+": " + twitterText);
            text++;
            }
            catch(Exception e)
            {
                
                noText++;
            }
            
        }

        cleint01.stop();
        double totalTweets = text + noText;
        double percentage = text / totalTweets * 100;
        System.out.println("Of the " + totalTweets + " tweets, " + text + " had text,that is " + percentage + "% of the tweets.");
    }
}
