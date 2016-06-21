/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class ConvertGPS {

    private final static Logger logger = Logger.getLogger(ConvertGPS.class);

    public ArrayList<Complaint> parseAndConvert(ArrayList<Complaint> complains) throws Exception {
        ArrayList<Complaint> complainArray = new ArrayList();
        String country = "The Netherlands";

        try {
            for (Complaint complain : complains) {
                GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAsqd3XkCE6DEiUry-SsJX3E38IZSZtsYY");
                GeocodingResult[] results = GeocodingApi.geocode(context,
                        complain.getPostcode() + ", " + complain.getPlaatsnaam() + ", " + country).await();

                logger.info(results[0].geometry.location.lat + ":" + results[0].geometry.location.lng);

                float lat = (float) results[0].geometry.location.lat;
                float lng = (float) results[0].geometry.location.lng;

                complain.setLatitude(lat);
                complain.setLongitude(lng);

                complainArray.add(complain);

                logger.info("Coordinates Set!");
                
                if(results.length == 2500){
                    break;
                }
                
            }
        } catch (Exception e) {
            return null;
        }
        
        return complainArray;
    }

    public void writeToFile(ArrayList<Complaint> complains) {
        try {
            String file = "entries.csv";
            CSVWriter writer = new CSVWriter(new FileWriter(file, true), '\n');

            String[] entries = new String[complains.size()];
            
            for (int i = 0; i < complains.size(); i++) {
                entries[i] = complains.get(i).toString();
            }
            
            writer.writeNext(entries);
            writer.close();

        } catch (Exception e) {
            logger.info(e);
        }
    }
}
