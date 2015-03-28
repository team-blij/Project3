import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Elize on 10-3-2015.
 */


public class GetWeather {

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
    private java.sql.Date date = null;
    private Database database = new Database();

    public GetWeather(){
        try {
            getWeatherAt();
        }catch(IOException ex  ){
            System.exit(1);
        }catch(JSONException json){
            System.exit(1);
        }finally {
            database.closeDatabase();
        }
    }//end of constructor


    public void getWeatherAt() throws IOException, MalformedURLException, JSONException {

                openWeatherMap = new OpenWeatherMap("");
                //Getting weather at Rotterdam
                currentWeather = openWeatherMap.currentWeatherByCityName("Rotterdam, NL");
                minTemperature = currentWeather.getMainInstance().getMinTemperature();
                maxTemperature = currentWeather.getMainInstance().getMaxTemperature();

                minTemperature = toCelcius(minTemperature);
                maxTemperature = toCelcius(maxTemperature);

                city = currentWeather.getCityName();
                long time = System.currentTimeMillis();
                date = new java.sql.Date(time);
                    if(currentWeather.hasRainInstance()){
                        rain = "Yes";
                    }
                    if(currentWeather.hasCloudsInstance()){
                        clouds = "Yes";
                    }
                    if(currentWeather.hasSnowInstance()){
                        snow = "Yes";
                    }
                    if(currentWeather.hasWindInstance()){
                        wind = "Yes";
                    }
                averageTemperature = Math.round(((minTemperature + maxTemperature) / 2));

                database.insertWeatherData(date, rain, averageTemperature, minTemperature, maxTemperature, snow, clouds, wind);


    }//end of getWeatherAt

    private float toCelcius(Float temp){
        float temperature = ((temp - 32) * 5)/ 9;
        return temperature;
    }
}//end of getWeather class
