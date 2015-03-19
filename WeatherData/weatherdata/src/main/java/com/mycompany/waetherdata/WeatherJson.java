/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.waetherdata;

/**
 *
 * @author Hans
 */

import java.io.IOException;



import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherJson {

  

  


  public static void main(String[] args)   {
      
      WeatherData();
  }
    public static JSONObject getJsondata(String url) throws IOException, JSONException
    {
       JSONObject json = JsonReader.readJsonFromUrl(url);
       
       return json;
    }
    
    public static void WeatherData()
    {
       try {
          //JSONObject json = readJsonFromUrl("http://api.openweathermap.org/data/2.5/weather?q=Rotterdam,nl&APPID=a05c0cf00ca0c2fa9fb79f6aadbde01d");
          String landcode = "nl";
          String stad = "Rotterdam";
          String jsonurl = "http://api.openweathermap.org/data/2.5/weather?q=;"+stad+","+landcode+"&APPID=a05c0cf00ca0c2fa9fb79f6aadbde01d";
          
          
          try {
              System.out.println(getJsondata(jsonurl).toString());
          } catch (IOException ex) {
              Logger.getLogger(WeatherJson.class.getName()).log(Level.SEVERE, null, ex);
          } catch (JSONException ex) {
              Logger.getLogger(WeatherJson.class.getName()).log(Level.SEVERE, null, ex);
          }
           try {
               System.out.println(getJsondata(jsonurl).get("name"));
           } catch (JSONException ex) {
               Logger.getLogger(WeatherJson.class.getName()).log(Level.SEVERE, null, ex);
           }
      } catch (IOException ex) {
          Logger.getLogger(WeatherJson.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }
}
