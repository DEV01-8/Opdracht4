/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.opencsv.CSVWriter;
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
public class WriteFile {

    //Logger4J

    final static Logger logger = Logger.getLogger(WriteFile.class);
    //File name and type to write object into
    private final String file = "entries.csv";
    //CSVWRITER
    private CSVWriter writer;
    //LineNumberReader
    private LineNumberReader lnr;
    //ArrayList with remaining items to write
    List<Complaint> remainingItems;

    public void writeToFile(ArrayList<Complaint> complains) throws IOException {
        try {
            //Size of Complains ArratList
            int sizeComplain = complains.size();
            //begin at index
            int beginIndex = getAmountLines();
            //end at index
            int endIndex = sizeComplain - beginIndex;
            //CSVWriter used from opencsv Library
            writer = new CSVWriter(new FileWriter(file, true), '\n');
            //Array of strings
            String[] entries = null;
            
            if(endIndex != 0){
                remainingItems = complains.subList(beginIndex, endIndex);
                entries = new String[remainingItems.size()];
            } else{
                remainingItems = complains;
                entries = new String[remainingItems.size()];
            }
            
            //Put objects from ArrayList in the String Array
            for (int i = 0; i < remainingItems.size(); i++) {
                entries[i] = remainingItems.get(i).toString();
            }

            //Write String[] to file
            writer.writeNext(entries);

        } catch (Exception e) {
            logger.info(e);
        } finally {
            //Close writer
            writer.close();
        }
    }

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
        } finally {
            lnr.close();
            logger.info("Done reading lines...");
        }

        //Return total number of lines
        return amount;
    }
}
