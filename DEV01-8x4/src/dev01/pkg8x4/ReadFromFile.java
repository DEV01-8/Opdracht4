/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class ReadFromFile {

    //ArrayList to return
    private final ArrayList<Complaint> complaints = new ArrayList();
    //Logger4J
    private final Logger logger = Logger.getLogger(ReadFromFile.class);
    //SimpleDateFormat
    private final SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
    //Create filepath to csv
    private final File file = new File("C:/dev/entries.csv");
    //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ";";
    //FileReader
    private BufferedReader fileReader;

    public ArrayList<Complaint> readCsvFile() throws IOException {
        try {
            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(file));
            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    //Create a new student object and fill his  data
                    String Ingevoerd = tokens[0];
                    Date Datum = sdf.parse(tokens[1]);
                    int Aantal = Integer.parseInt(tokens[2]);
                    String Postcode = tokens[3];
                    String Plaats = tokens[4];
                    String aardOverlast = tokens[5];
                    String subOverlast = tokens[6];
                    String subSubOverlast = tokens[7];
                    Boolean terugKoppeling = Boolean.parseBoolean(tokens[8]);
                    float Latitude = Float.parseFloat(tokens[9]);
                    float Longitude = Float.parseFloat(tokens[10]);

                    //Create Complaint object and set values from above in it.
                    Complaint complain = new Complaint(Ingevoerd, Datum, Aantal, Postcode,
                            Plaats, aardOverlast, subOverlast, subSubOverlast, terugKoppeling);

                    complain.setLatitude(Latitude);
                    complain.setLongitude(Longitude);

                    complaints.add(complain);
                }
            }

        } catch (IOException | ParseException | NumberFormatException e) {
            logger.error("Error while reading CSV");
            logger.error(e);
        } finally {
            fileReader.close();
        }
        return complaints;

    }

}
