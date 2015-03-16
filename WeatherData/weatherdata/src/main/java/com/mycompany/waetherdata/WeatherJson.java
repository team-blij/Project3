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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherJson {

  private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    } 
  }


  public static void main(String[] args)   {
      WeatherData();
  }
    public static JSONObject getJsondata(String url) throws IOException, JSONException
    {
       JSONObject json = readJsonFromUrl(url);
       
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
          System.out.println(getJsondata(jsonurl).get("name"));
      } catch (IOException ex) {
          Logger.getLogger(WeatherJson.class.getName()).log(Level.SEVERE, null, ex);
      } catch (JSONException ex) {
          System.out.println("Requesting data again!");
          WeatherData();
      } 
    }
}
