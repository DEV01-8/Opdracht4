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
import java.util.logging.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class CSVParser {

    //ArrayList to return

    private static final ArrayList<Complaint> klachten = new ArrayList();
    //Logger4J
    final static Logger logger = Logger.getLogger(CSVParser.class);

    public static ArrayList<Complaint> read() {
        try {
            logger.info("Reading CSV...");

            //Create filepath to csv
            File path = new File("C:\\dev\\klachten.csv");

            //SimpleDateFormat to format date in csv to appriopriate date
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));
            //Separators
            char[] separator = {';', ','};
            //Skip first x amount of lines.
            int skipLine = 1;

            //read csv using opencsv library
            CSVReader reader = new CSVReader(new FileReader(path), separator[0], separator[1], skipLine);
            String[] nextLine;

            logger.info("Going through CSV...");

            //start time to see how long it takes
            long startTime = System.currentTimeMillis();

            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                String Ingevoerd = nextLine[0];
                Date Datum = sdf.parse(nextLine[1]);
                int Aantal = Integer.parseInt(nextLine[2]);
                String Straat = nextLine[3];
                String Postcode = nextLine[4];
                String Plaats = nextLine[5];
                String aardOverlast = nextLine[6];
                String subOverlast = nextLine[7];
                String subSubOverlast = nextLine[8];
                Boolean terugKoppeling = Boolean.parseBoolean(nextLine[9]);

                //Create Complaint object and set values from above in it.
                Complaint klacht = new Complaint(Ingevoerd, Datum, Aantal, Straat, Postcode,
                        Plaats, aardOverlast, subOverlast, subSubOverlast, terugKoppeling);

                //Add Complaint object to ArrayList
                klachten.add(klacht);
            }

            //Close reader
            reader.close();

            //stop timer
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;

            logger.info("Done going through csv!");
            logger.info("Time elapsed: " + (elapsedTime / 1000) + " sec");
            logger.info("Size Array: " + klachten.size());

        } catch (IOException | NumberFormatException e) {
            logger.info(e);
        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CSVParser.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Return ArrayList
        return klachten;
    }
}
