/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import processing.core.PApplet;

/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class Main extends PApplet {

    //Logger4J
    final static Logger logger = Logger.getLogger(Main.class);
    //Arraylist to put in items from CSVParser
    private ArrayList<Complaint> complaints = new ArrayList();
    //New ConvertGPS objetc
    private final ConvertGPS convert = new ConvertGPS();
    //ArrayList to put in converted complaints in
    private ArrayList<Complaint> newComplaints;
    //CSVWriter to write arraylist items to csv file
    private final WriteFile writer = new WriteFile();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //Logger4J
        BasicConfigurator.configure();
        PApplet.main(new String[]{Main.class.getName()});
    }

    @Override
    public void setup() {
        /*
         //Show message about controls
         JOptionPane.showMessageDialog(frame, "Press 'Enter' to start converting and parsing \n"
         + "Press 'Space' to start writing coördinates to a .csv file"
         );
         */
        //Put ArrayList of Complaint object from the parser into this arraylist
        complaints = CSVParser.read();
        ArrayList<Complaint> inputArray = startConvert();
        startWrite(inputArray);

    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void draw() {

    }

    private ArrayList<Complaint> startConvert() {
        try {
            //Convert and parse objects from complaints ArrayList and put them in
            //newComplaints ArrayList
            logger.info("Converting started.");
            newComplaints = convert.parseAndConvert(complaints);
        } catch (Exception ex) {
            logger.info(ex);
        }
        logger.info("Converting ended.");

        return newComplaints;
    }

    private void startWrite(ArrayList<Complaint> complaints) {
        //Write items of newComplaints in a .csv file
        logger.info("Writing started.");
        writer.writeToFile(complaints);
        logger.info("Writing ended.");
    }
}
