import twitter4j.Query;
import twitter4j.User;

/**
 * Created by Elize on 13-3-2015.
 */
public class Queries {
                GetTwitter getTwitter = null;

//              Sentiment analyse:
//              --------------------------------------------------------------------------------------------------------
//            	Welk onderdeel van diergaarde Blijdorp vinden de bezoekers het leukst?
                private String getBestAspect(){
                    String query = "@rotterdamzoo :)" ;
                    return query;
                }

//            	Welk onderdeel van diergaarde Blijdorp vinden de bezoekers het minst leukst?
                public String getWorstAspect(){
                    String query = "@rotterdamzoo :(" ;
                    return query;
                }

//            	Wat zijn de populairste dieren in diergaarde Blijdorp?
                public String getBestAnimal(){
                    String query = "@rotterdamzoo :)" ;
                    return query;
                }
//
//              Geografische analyse:
//              --------------------------------------------------------------------------------------------------------
//            	Uit welk land buiten Nederland komen de meeste bezoekers?
                public String getCountry(){
                    String query = "@rotterdamzoo"; //needs editing!
                    return query;
                }
//            	Uit welke provincie in Nederland komen de meeste bezoekers?
                public String getProvince(){
                    String query = "@rotterdamzoo"; //needs editing!
                    return query;
                }


//              ++Meteorologische analyse:
//              --------------------------------------------------------------------------------------------------------
//            	Heeft regenachtig weer een negatief effect op het aantal bezoeker?
                public String getBadWeather(){
                    String query = "@rotterdamzoo :)";
                    String weatherQuery = ""; //needs editing
                    return query;
                }

//            	Heeft zonnig weer een positief effect op het aantal bezoekers?
                public String getGoodWeather(){
                    String query = "@rotterdamzoo :)";
                    String weatherQuery = ""; //needs editing
                    return query;
                }

//            	Heeft een lage temperatuur een negatief effect op het aantal bezoekers?
                public String getLowTemperature(){
                    String query = "@rotterdamzoo :(";
                    String weatherQuery = ""; //needs editing
                    return query;
                }

//            	Heeft een hoge temperatuur een negatief effect op het aantal bezoekers?
                public String getHighTemperature(){
                    String query = "@rotterdamzoo :(";
                    String weatherQuery = ""; //needs editing
                    return query;
                }

//
//              ++Response analyse
//              --------------------------------------------------------------------------------------------------------
//    	        Hoe vaak is Diergaarde Blijdorp actief of twitter?
                public String getResponseBlijdorp(){
                    String query = "from:rotterdamzoo"; //needs editing
                    return query;
                }

//            	Waar tweet Diergaarde Blijdorp het meest over?
                public String getTweetsBlijdorp(){
                    String query = "from:rotterdamzoo "; //needs editing
                    return query;
                }

//            	Stijgt of daalt het aantal followers van Blijdorp (per week)?
                public Integer getFollowersBlijdorp(){
                    User user = getTwitter.GetUser();
                    int amount_of_followers = user.getFollowersCount();
                    return amount_of_followers;
                }
//

}
