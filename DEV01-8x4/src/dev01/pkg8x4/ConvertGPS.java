/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.GeocodingResult;
import java.util.ArrayList;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class ConvertGPS {

    GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAsqd3XkCE6DEiUry-SsJX3E38IZSZtsYY");

    public ArrayList<double[]> getDegree(ArrayList<Klacht> klachten) {
        ArrayList<double[]> longlat = new ArrayList();
        try {
            for (int i = 0; i < klachten.size(); i++) {
                GeocodingResult[] results = GeocodingApi.geocode(context,
                        klachten.get(i).getStraatnaam() + "," + klachten.get(i).getPostcode() + "," + klachten.get(i).getPlaatsnaam()).await();

                double[] codes = {results[0].geometry.location.lng,
                    results[0].geometry.location.lat
                };

                longlat.add(codes);
            }
        } catch (Exception e) {
            System.out.println("Oooopppss");
        }
        return longlat;
    }
}
