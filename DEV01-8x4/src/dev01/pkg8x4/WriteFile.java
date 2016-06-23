/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.opencsv.CSVWriter;
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
public class WriteFile {

    //Logger4J
    //File name and type to write object into
    //CSVWRITER
    //LineNumberReader
    //ArrayList with remaining items to write
    //Boolean to set append to true or false
    final static Logger logger = Logger.getLogger(WriteFile.class);
    private final File file = new File("C:/dev/entries.csv");
    private CSVWriter writer;
    private LineNumberReader lnr;
    private List<Complaint> remainingItems;
    public Boolean append;

    public void writeToFile(ArrayList<Complaint> complains) throws IOException {
        try {
            //Size of Complains ArratList
            //begin at index
            //end at index
            int sizeComplain = complains.size();
            int beginIndex = getAmountLines();
            int endIndex = sizeComplain - beginIndex;
            
            if(endIndex > 0){
                append = true;
                remainingItems = complains.subList(beginIndex, endIndex);
            } else if(endIndex == 0){
                append = false;
                remainingItems = complains;
            }
            
            String[] entries = new String[remainingItems.size()];
            
            //Put objects from ArrayList in the String Array
            for (int i = 0; i < remainingItems.size(); i++) {
                entries[i] = remainingItems.get(i).toString();
            }
            
            //CSVWriter used from opencsv Library
            writer = new CSVWriter(new FileWriter(file, append), '\n');

            //Write String[] to file
            writer.writeNext(entries);
            
        } catch (Exception e) {
            logger.info(e);
        } finally {
            //Close writer
            writer.close();
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
