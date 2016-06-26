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
public class CSVParser {

    //ArrayList to return
    //Logger4J
    //SimpleDateFormat
    //Separators
    //Skip first x amount of lines.
    //Create filepath to csv
    //CSVReader
    private final ArrayList<Complaint> complaints = new ArrayList();
    private final Logger logger = Logger.getLogger(CSVParser.class);
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    private final char[] separator = {';', '\n'};
    private final int skipLine = 1;
    private final File path = new File("C:/dev/klachten.csv");
    private CSVReader reader;

    public ArrayList<Complaint> read() throws IOException {
        logger.info("Reading CSV...");
        sdf.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));

        //read csv using opencsv library
        reader = new CSVReader(new FileReader(path), separator[0], separator[1], skipLine);
        String[] nextLine;

        logger.info("Going through CSV...");
        int lines = 0;

        try {
            //if lines is higher than 2500, the max amount of queries a day is reached
            while ((nextLine = reader.readNext()) != null && lines < 2501) {
                // nextLine[] is an array of values from the line
                String Ingevoerd = nextLine[0];
                Date Datum = sdf.parse(nextLine[1]);
                int Aantal = Integer.parseInt(nextLine[2]);
                String Postcode = nextLine[4];
                String Plaats = nextLine[5];
                String aardOverlast = nextLine[6];
                String subOverlast = nextLine[7];
                String subSubOverlast = nextLine[8];
                Boolean terugKoppeling = Boolean.parseBoolean(nextLine[9]);

                //Create Complaint object and set values from above in it.
                Complaint complaint = new Complaint(Ingevoerd, Datum, Aantal, Postcode,
                        Plaats, aardOverlast, subOverlast, subSubOverlast, terugKoppeling);

                if (complaint.getAardOverlast().equals("Stank")) {
                    //Add Complaint object to ArrayList
                    complaints.add(complaint);
                }
                
                lines++;
            }

            logger.info("Done going through csv!");
            logger.info("Size Array: " + complaints.size());

        } catch (IOException | NumberFormatException | ParseException e) {
            logger.info(e);
        } finally{
            //Close reader
            reader.close();
        }

        //Return ArrayList
        return complaints;
    }
}
