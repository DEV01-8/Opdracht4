/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class WriteToFile {

    //Logger4J
    final Logger logger = Logger.getLogger(WriteToFile.class);
    //File name and type to write object into
    private final File file = new File("C:/dev/entries.csv");
    //Delimiter used in CSV file
    private final String COMMA_DELIMITER = ";";
    private final String NEW_LINE_SEPARATOR = "\n";
    //ArrayList
    private List<Complaint> remainingItems = new ArrayList();
    //LineNumberReader
    private LineNumberReader lnr;
    //CSV file header
    private final String FILE_HEADER = "Ingevoerd;Datum;Aantal;Postcode;Plaatsnaam;Aard Overlast;"
            + "Sub Overlast;Sub SubAard Overlast;Terugkoppeling;Latitude;Longitude";
    //FileWriter
    private FileWriter fileWriter;
    //Boolean to set append to true or false
    private Boolean append = false;

    public void writeCsvFile(ArrayList<Complaint> complaints) throws IOException {
        int arraySize = complaints.size();
        //Index start
        int beginIndex = getAmountLines();
        //Index end
        int endIndex = arraySize - beginIndex;

        try {

            if (beginIndex > 0) {
                append = true;
                remainingItems = complaints.subList(beginIndex, endIndex);
            } else if (beginIndex == 0) {
                append = false;
                remainingItems = complaints;
            }

            //FileWriter
            fileWriter = new FileWriter(file, append);
            //Write the CSV file header
            fileWriter.append(FILE_HEADER);
            //Add a new line separator after the header
            fileWriter.append(NEW_LINE_SEPARATOR);

            //Write a new student object list to the CSV file
            for (Complaint complaint : remainingItems) {
                fileWriter.append(String.valueOf(complaint.getIngevoerd()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(complaint.getDatum()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(complaint.getAantal()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(complaint.getPostcode());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(complaint.getPlaatsnaam());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(complaint.getAardOverlast());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(complaint.getSubOverlast());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(complaint.getSubSubaardOverlast());
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(complaint.getTerugkoppeling()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(complaint.getLatitude()));
                fileWriter.append(COMMA_DELIMITER);
                fileWriter.append(String.valueOf(complaint.getLongitude()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

            logger.info("CSV created!");

        } catch (Exception e) {
            logger.error("Error in FileWriter");
            logger.error(e);
        } finally {
            fileWriter.close();
        }
    }

    //Get total amount of lines in csv file
    private int getAmountLines() throws IOException {
        //Amount of lines in csv file
        int amount = 0;
        lnr = new LineNumberReader(new FileReader(file));

        try {
            logger.info("Starting reading lines...");
            //Get linenumber + 1 because it starts at zero
            amount = lnr.getLineNumber();
            lnr.close();

        } catch (FileNotFoundException ex) {
            logger.info(ex);
        }

        //Return total number of lines
        return amount;
    }
}
