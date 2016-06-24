/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class ReadFile {

    //ArrayList to return
    private final ArrayList<Complaint> complainArray = new ArrayList();
    //Logger4J
    private final Logger logger = Logger.getLogger(ReadFile.class);
    //SimpleDateFormat
    private final SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
    //Separators
    private final char[] separator = {';', ','};
    //Create filepath to csv
    private final File path = new File("C:\\dev\\entries.csv");
    //CSVReader
    private CSVReader reader;

    public ArrayList<Complaint> read() throws IOException {
        logger.info("Reading CSV...");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));

        //read csv using opencsv library
        reader = new CSVReader(new FileReader(path), separator[0], separator[1]);
        String[] nextLine;

        logger.info("Going through CSV...");

        try {
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                String Ingevoerd = nextLine[0];
                Date Datum = sdf.parse(nextLine[1]);
                int Aantal = Integer.parseInt(nextLine[2]);
                String Postcode = nextLine[3];
                String Plaats = nextLine[4];
                String aardOverlast = nextLine[5];
                String subOverlast = nextLine[6];
                String subSubOverlast = nextLine[7];
                Boolean terugKoppeling = Boolean.parseBoolean(nextLine[8]);
                float Latitude = Float.parseFloat(nextLine[9]);
                float Longitude = Float.parseFloat(nextLine[10]);

                //Create Complaint object and set values from above in it.
                Complaint complain = new Complaint(Ingevoerd, Datum, Aantal, Postcode,
                        Plaats, aardOverlast, subOverlast, subSubOverlast, terugKoppeling);

                complain.setLatitude(Latitude);
                complain.setLongitude(Longitude);

                //Add Complaint object to ArrayList
                complainArray.add(complain);
            }

            logger.info("Done going through csv!");
            logger.info("Size Array: " + complainArray.size());

        } catch (IOException | NumberFormatException | ParseException e) {
            logger.info(e);
        } finally {
            //Close reader
            reader.close();
        }

        //Return ArrayList
        return complainArray;
    }
}
