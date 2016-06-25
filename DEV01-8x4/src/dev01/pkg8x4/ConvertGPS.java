/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class ConvertGPS {

    //Logger4J
    private final static Logger logger = Logger.getLogger(ConvertGPS.class);
    //Arraylist which this method will return
    ArrayList<Complaint> complainArray = new ArrayList();
    //Country String
    String country = "The Netherlands";
    //API Keys
    String apiKey1 = "AIzaSyAsqd3XkCE6DEiUry-SsJX3E38IZSZtsYY";
    String apiKey2 = "AIzaSyDUHwj3qaCGQbl7-giMhLld_LMhsSBf2vo";
    String apiKey3 = "AIzaSyCLKZ4WOJqER5kotwWB4UJuqYHDm4TB-A8";
        
    public ArrayList<Complaint> parseAndConvert(ArrayList<Complaint> complaints) throws Exception {
         //Context to put in API Key
        GeoApiContext context = new GeoApiContext().setApiKey(apiKey1);

        try {
            for (Complaint complain : complaints) {
                //Convert address and postal code to get Longitude and Latitude
                GeocodingResult[] results = GeocodingApi.geocode(context,
                        complain.getPostcode() + ", " + complain.getPlaatsnaam() + ", " + country).await();
                
                context.setRetryTimeout(0, TimeUnit.MILLISECONDS);

                //Latitude and Longitude from results Array
                float lat = (float) results[0].geometry.location.lat;
                float lng = (float) results[0].geometry.location.lng;

                //Set longitude and latitde to Complaint object
                complain.setLatitude(lat);
                complain.setLongitude(lng);

                //Add Complaint object to ArrayList
                complainArray.add(complain);

                logger.info("Coordinates Set!");
            }
        } catch (Exception e) {
            return null;
        }

        return complainArray;
    }
}
