/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.util.ArrayList;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class WriteFile {

    //File name and type to write object into

    String file = "entries.csv";

    public void writeToFile(ArrayList<Complaint> complains) {
        try {
            try ( //CSVWriter used from opencsv Library
                    CSVWriter writer = new CSVWriter(new FileWriter(file, true), '\n')) {
                String[] entries = new String[complains.size()];
                
                //Put objects from ArrayList in the String Array
                for (int i = 0; i < complains.size(); i++) {
                    entries[i] = complains.get(i).toString();
                }
                
                //Write String[] to file
                writer.writeNext(entries);
                
                //Close writer
                writer.close();
            }

        } catch (Exception e) {
        }
    }
}
