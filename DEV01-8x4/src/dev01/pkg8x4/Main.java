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

    final static Logger logger = Logger.getLogger(Main.class);
    private ArrayList<Complaint> complaints = new ArrayList();
    private final ConvertGPS convert = new ConvertGPS();
    private ArrayList complain;
    private WriteFile writer = new WriteFile();

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
        complaints = CSVParser.read();
        
        //Show message about controls
        JOptionPane.showMessageDialog(frame, "Press 'Enter' to start converting and parsing \n"
                + "Press 'Space' to start writing coördinates to a .csv file"
        );
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void draw() {

    }

    @Override
    public void keyPressed() {
        if (keyPressed && (key == ENTER | key == RETURN)) {
            logger.info("Converting and parsing coordinates....");

            try {
                complain = convert.parseAndConvert(complaints);
            } catch (Exception e) {
            }

            logger.info("Done converting and parsing!");
        } else if (keyPressed && key == ' ') {
            logger.info("Writing coordinates to file....");
            writer.writeToFile(complain);
            logger.info("Done writing coordinates to file!");
        }
    }
}
