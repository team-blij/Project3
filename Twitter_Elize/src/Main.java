import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Elize on 10-3-2015.
 */


public class Main {

    public static void main(String[] args) {
        //Twitter call
        GetTwitter getTwitter = new GetTwitter();
        getTwitter.setup();

        //Weather call
//        try {
//            GetWeather getWeather = new GetWeather();
//            getWeather.getWeatherAt();
//        }catch(IOException ex  ){
//            System.exit(1);
//        }catch(JSONException json){
//            System.exit(1);
//        }


    }

}
