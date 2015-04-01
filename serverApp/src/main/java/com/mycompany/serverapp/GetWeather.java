
package com.mycompany.serverapp;
import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;


/**
 * Created by Elize on 10-3-2015.
 */


public final class GetWeather {

    private OpenWeatherMap openWeatherMap = null;
    private String rain = "No";
    private String wind = "No";
    private String clouds = "No";
    private String snow = "No";
    private CurrentWeather currentWeather = null;
    private float minTemperature = 0f;
    private float maxTemperature = 0f;
    private String city = null;
    private float averageTemperature = 0;
    private java.sql.Date date =  null;
    private Database database = null;

    public GetWeather() {
        try {
            System.out.println("Weer updaten");
            getWeatherAt();
            
        }catch(IOException | JSONException ex  ){
            System.exit(1);
        } catch (SQLException ex) {
            Logger.getLogger(GetWeather.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            //database.closeDatabase();
        }
    }//end of constructor


    public void getWeatherAt() throws IOException, MalformedURLException, JSONException, SQLException {

                openWeatherMap = new OpenWeatherMap("");
                //Getting weather at Rotterdam
                currentWeather = openWeatherMap.currentWeatherByCityName("Rotterdam, NL");
                minTemperature = currentWeather.getMainInstance().getMinTemperature();
                maxTemperature = currentWeather.getMainInstance().getMaxTemperature();

                minTemperature = toCelcius(minTemperature);
                maxTemperature = toCelcius(maxTemperature);

                city = currentWeather.getCityName();
                date = getDateToday();
                    if(currentWeather.hasRainInstance()){
                        rain = "Yes";
                    }
                    if(currentWeather.hasCloudsInstance()){
                        clouds = "Yes";
                    }
                    if(currentWeather.hasRainInstance() && maxTemperature <= 0){
                        snow = "Yes";
                    }
                    if(currentWeather.hasWindInstance()){
                        wind = "Yes";
                    } 
                averageTemperature = Math.round(((minTemperature + maxTemperature) / 2));
                database = new Database();
                try{
                database.insertWeatherData(date, rain, averageTemperature, minTemperature, maxTemperature, snow, clouds, wind);
                }
                catch(Exception e)
                {
                    System.out.println("Weer van vandaag staat al in de database");
                }
                database = null;

    }//end of getWeatherAt

    private float toCelcius(Float temp){
        float temperature = ((temp - 32) * 5)/ 9;
        return temperature;
    }

    private java.sql.Date getDateToday(){
        java.util.Date dateTodayUtil = new java.util.Date();
        java.sql.Date dateToday = new java.sql.Date(dateTodayUtil.getTime());
        return dateToday;
    }// end of getDateToday()
}//end of getWeather class
