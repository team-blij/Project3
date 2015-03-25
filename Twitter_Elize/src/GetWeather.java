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
    private float rain = 0f;
    private float wind = 0f;
    private float clouds = 0f;
    private float snow = 0f;
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

    }


    public void getWeatherAt() throws IOException, MalformedURLException, JSONException {

                openWeatherMap = new OpenWeatherMap("");
                //Getting weather at Rotterdam
                currentWeather = openWeatherMap.currentWeatherByCityName("Rotterdam, NL");
                minTemperature = currentWeather.getMainInstance().getMinTemperature();
                maxTemperature = currentWeather.getMainInstance().getMaxTemperature();
                city = currentWeather.getCityName();
                long time = System.currentTimeMillis();
                date = new java.sql.Date(time);
                    if(currentWeather.getRainInstance() != null){
                        rain = currentWeather.getRainInstance().getRain3h();
                    }
                    if(currentWeather.getCloudsInstance() != null){
                        clouds = currentWeather.getCloudsInstance().getPercentageOfClouds();
                    }
                    if(currentWeather.getSnowInstance() != null){
                        snow = currentWeather.getSnowInstance().getSnow3h();
                    }
                    if(currentWeather.getWindInstance() != null){
                        wind = currentWeather.getWindInstance().getWindGust();
                    }
                averageTemperature = Math.round(((minTemperature + maxTemperature) / 2));

                try{
                database.insertWeatherData(city, minTemperature, maxTemperature, averageTemperature, date, rain, wind, snow, clouds);
                }catch(SQLException ex){
                    //TODO
                }
    }
}
