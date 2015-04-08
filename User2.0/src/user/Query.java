/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author School
 */
public class Query {
    String query = null;
    String time = " date > NOW() - INTERVAL 1 Month ";
    Database database = new Database();
    
    
    public void setMonth(){
        time = " date > NOW() - INTERVAL 1 Month ";
    }
    
    public void setWeek(){
        time = " date > NOW() - INTERVAL 1 Week ";
    }
    
    public void setDay(){
        time = " date > NOW() - INTERVAL 1 Day ";
    }
    
    public ResultSet countTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET"
                +   "   WHERE"
                +       time
                +   "   GROUP BY TWEET_ID "
                +   ";"
                ;
        return executeQuery(query);
    }//end of countTweets()
    
    public ResultSet executeQuery(String query){
        return database.query(query);
    }//end of executeQuery()
    
    
    private DefaultPieDataset getPieDataSet(ResultSet resultSet, String name, String value){
      DefaultPieDataset dataset = new DefaultPieDataset();
      try{
        while( resultSet.next() ) 
          {
            dataset.setValue( 
            resultSet.getString(name) ,
            Double.parseDouble( resultSet.getString(value)));
         }
        }catch(SQLException ex){}
      return dataset;
    }
    
    private XYSeriesCollection getXYSeries(ResultSet resultSet, String value){
        XYSeries series = new XYSeries("Random Data");
        try{
        while( resultSet.next() ) 
          {
           series.add(5,
           Double.parseDouble( resultSet.getString(value)));
         }
        }catch(SQLException ex){}
        XYSeriesCollection data = new XYSeriesCollection(series);
        return data;
    }
    
    private CategoryDataset getCategoryDataset(ResultSet resultSet, String value, String date ){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Calendar c = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        c.add(Calendar.DATE, -4);

        final String category1 = dateFormat.format(c.getTime());

        c.add(Calendar.DATE, 1);
        final String category2 = dateFormat.format(c.getTime());

        c.add(Calendar.DATE, 1);
        final String category3 = dateFormat.format(c.getTime());
        final String category4 = "Yesterday";
        final String category5 = "Today";
        
        try{
        while( resultSet.next()) 
          {
           dataset.addValue(Double.parseDouble(resultSet.getString(value)), value , resultSet.getString(date));
         }
        }catch(SQLException ex){}

        return dataset;
    }
    
    
    public DefaultPieDataset getBestAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND "
                    +       time    
                    +   "   group by Area "
                    +   "   order by count(Area) desc;"
                    ;
            return  getPieDataSet(executeQuery(query), "Area", "AreaCount" );
    }
    
    public DefaultPieDataset getWorstAsset(){
            query =     "   select Area, count(Area) as AreaCount "
                    +   "   from tweet "
                    +   "   where Area not like \"%area%\" "
                    +   "   AND"
                    +       time
                    +   "   group by Area"
                    +   "   order by count(Area); "
                    ;
            return getPieDataSet(executeQuery(query), "Area", "AreaCount" );
    
    
    
    }
    
    public ResultSet getBestAnimal(){
            query =     "   SELECT Animal, count(Animal)"
                    +   "   from tweet"
                    +   "   WHERE Animal NOT LIKE '%animal%'"
                    +   "   AND"
                    +       time
                    +   "   GROUP BY Animal;"
                    ;
 
        return executeQuery(query);
    
    
    }
    
    public DefaultPieDataset getCountry(){
        query =     "   SELECT COUNTRY, COUNT(*) AS AMOUNT"
                +   "   FROM TWEET"
                +   "   WHERE "
                +       time
                +   "   GROUP BY COUNTRY;"
                ;
        return getPieDataSet(executeQuery(query), "COUNTRY", "AMOUNT" );
    }//end of getCountry()
    
    public DefaultPieDataset getRegion(){
        query =     "   SELECT REGION, COUNT(*) AS AMOUNT"
                +   "   FROM TWEET"
                +   "   WHERE "
                +       time
                +   "   GROUP BY REGION;"
                ;
        return getPieDataSet(executeQuery(query), "REGION", "AMOUNT" );
    }
    
    public ResultSet getRainAndTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN =  'true'"
                +   "   AND "
                +       time
                +   "   AND TWEET.DATE = WEATHER.DATE;"
                ;
        return executeQuery(query);
    }
    
    public ResultSet getGoodWeatherAndTweets(){
        query =     "   SELECT COUNT(*)"
                +   "   FROM TWEET, WEATHER"
                +   "   WHERE WEATHER.RAIN = 'false' "
                +   "   AND WEATHER.SNOW = 'false' "
                +   "   AND TWEET.DATE = WEATHER.DATE "
                +   "   AND "
                +       time 
                +   ";"
                ;    
        return executeQuery(query);
    }
    
    public ResultSet getLowTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE <= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;
         return executeQuery(query);
    }
    
    public ResultSet getHighTemperatureAndTweets(){
         query =    "   SELECT COUNT(*)"
                 +  "   FROM TWEET, WEATHER"
                 +  "   WHERE AVERAGETEMPERATURE >= 10"
                 +  "   AND TWEET.DATE = WEATHER.DATE"
                 +  "   AND "
                 +      time
                 +  ";"
                 ;       
         return executeQuery(query);
    }
    
    public XYSeriesCollection getBlijdorpTweetsAndDate(){
                 query =    "   SELECT  DATE, COUNT(*) As amount"
                         +  "   FROM TWEET"
                         +  "   WHERE User_name = ' Diergaarde Blijdorp'"
                         +  "   AND "
                         +      time
                         +  "   GROUP BY DATE"
                         ;      
         return getXYSeries(executeQuery(query), "amount");      
    }
    
    public DefaultPieDataset getBlijdorpTweets(){
                 query =         "   SELECT "
                            +    "    ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE AREA != ' no area' "
                            +    " AND ANIMAL = ' no animal' "
                            +    " AND User_name = ' Diergaarde Blijdorp'"
                            +    " AND "
                            +      time    
                            +    " ) AS Tweets_About_Areas, ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE  ANIMAL != ' no animal' "
                            +    " AND User_name = ' Diergaarde Blijdorp' "
                            +    " AND "
                            +      time    
                            +    " ) AS Tweets_About_Animals,"
                            +    " ("
                            +    " SELECT count(*) "
                            +    " FROM Tweet "
                            +    " WHERE  ANIMAL = ' no animal' "
                            +    " AND  AREA = ' no area' "
                            +    " AND User_name = ' Diergaarde Blijdorp' "
                            +    "  AND "
                            +      time
                            +    " ) AS 'Other_Tweets'"
                            +    " ;"
                         ;  
                    DefaultPieDataset dataset = new DefaultPieDataset( );
                      try{
                       ResultSet resultSet = executeQuery(query);
                       ResultSetMetaData metaData = resultSet.getMetaData();
                          while( resultSet.next() ) 
                            {
                              int count = metaData.getColumnCount();
                                    for (int i = 1; i <= count; i++)
                                    {
                                        dataset.setValue( 
                                        metaData.getColumnName(i) ,
                                        Integer.parseInt( resultSet.getString(i)));
                                    }
                                }
                          }catch(SQLException ex){
                          }
                     return dataset;      
    }
    
    public CategoryDataset getFollowersBlijdorp(){
                 query =    " SELECT DATE, FOLLOWERS"
                         +  " FROM TWEET"
                         +  " WHERE User_name = ' Diergaarde Blijdorp'"
                         +  " AND"
                         +    time
                         +  " GROUP BY DATE;"
                         ;  
         return getCategoryDataset(executeQuery(query), "Followers", "DATE");        
    }
}//end of Query
