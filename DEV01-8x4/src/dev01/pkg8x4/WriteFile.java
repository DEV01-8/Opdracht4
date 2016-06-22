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
    //File name and type to write object into
    //CSVWRITER
    //LineNumberReader
    //ArrayList with remaining items to write
    final static Logger logger = Logger.getLogger(WriteFile.class);
    private final String file = "entries.csv";
    private CSVWriter writer;
    private LineNumberReader lnr;
    private List<Complaint> remainingItems;

    public void writeToFile(ArrayList<Complaint> complains) throws IOException {
        try {
            //Size of Complains ArratList
            //begin at index
            //end at index
            int sizeComplain = complains.size();
            int beginIndex = getAmountLines();
            int endIndex = sizeComplain - beginIndex;
            //CSVWriter used from opencsv Library
            writer = new CSVWriter(new FileWriter(file, true), '\n');
            //Array of strings
            String[] entries = null;
            
            if(endIndex > 0){
                remainingItems = complains.subList(beginIndex, endIndex);
                entries = new String[remainingItems.size()];
            } else if(endIndex == 0){
                remainingItems = complains;
                entries = new String[remainingItems.size()];
            }
            
            //Put objects from ArrayList in the String Array
            for (int i = 0; i < remainingItems.size(); i++) {
                entries[i] = remainingItems.get(i).toString();
            }

            //Write String[] to file
            writer.writeNext(entries);
            
            //Close writer
            writer.close();

        } catch (Exception e) {
            logger.info(e);
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
            amount = lnr.getLineNumber()+1;
            lnr.close();
            
        } catch (FileNotFoundException ex) {
            logger.info(ex);
        }

        //Return total number of lines
        return amount;
    }
}
