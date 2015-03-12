import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

/**
 * Created by Elize on 10-3-2015.
 */


public class GetWeather {

    public void getWeatherAt() throws IOException, MalformedURLException, JSONException {

                OpenWeatherMap openWeatherMap = new OpenWeatherMap("");
                //Getting weather at Rotterdam
                CurrentWeather currentWeather = openWeatherMap.currentWeatherByCityName("London");
                float minTemperature = currentWeather.getMainInstance().getMinTemperature();
                float maxTemperature = currentWeather.getMainInstance().getMaxTemperature();
                int averageTemperature = Math.round(((minTemperature + maxTemperature) / 2));
                System.out.println("De gemiddelde temperatuur in Rotterdam is: " + averageTemperature);
    }
}
