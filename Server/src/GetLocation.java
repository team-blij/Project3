import twitter4j.JSONArray;
import twitter4j.JSONException;
import twitter4j.JSONObject;
import twitter4j.TwitterException;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Hans on 27-3-2015.
 */
public class GetLocation {
    private JSONObject gmaps = null;
    private JSONArray results = null;
    private JSONObject results2 = null;
    private JSONArray adresscomp = null;
    private String region = null;
    private String country = null;

    //@autor Hans
    public GetLocation(double lat, double lng){
        try{
            gmaps = readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false");
            results = gmaps.getJSONArray("results");
            results2 = results.getJSONObject(0);
            adresscomp = results2.getJSONArray("address_components");
        }catch(JSONException ex){

        }catch(IOException ex){


        }
    }// end of constructor


    //@autor Hans
    public String getRegion() {
        try {
            int arrayLenght = adresscomp.length();
            for (int i = 0; i < arrayLenght - 1; i++) {
                JSONObject cityObject = adresscomp.getJSONObject(i);
                JSONArray types = cityObject.getJSONArray("types");
                String typeString = types.getString(0);
                if (typeString.equals("administrative_area_level_1")) {
                    region = cityObject.getString("long_name");
                    return region;
                }
            }
        }catch(JSONException ex){

        }
        return null;
    }

    //@author Hans
    public String getCountry() {
        try {
            int arrayLenght = adresscomp.length();
            for (int i = 0; i < arrayLenght - 1; i++) {
                JSONObject cityObject = adresscomp.getJSONObject(i);
                JSONArray types = cityObject.getJSONArray("types");
                String typeString = types.getString(0);
                if (typeString.equals("country")) {
                    country = cityObject.getString("long_name");
                    return country;
                }
            }
        }catch(JSONException ex){

        }
        return null;
    }
    //@author Hans
    private static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }//end of readJsonFromUrl()

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }//end of readAll();


}
