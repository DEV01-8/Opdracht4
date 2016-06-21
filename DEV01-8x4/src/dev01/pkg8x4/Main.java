/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import java.util.ArrayList;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import processing.core.PApplet;


/**
 *
 * @author Johan Bos <Johan Bos at jhnbos.nl>
 */
public class Main extends PApplet {

    final static Logger logger = Logger.getLogger(Main.class);
    ArrayList<Complaint> complaints = new ArrayList();
    ConvertGPS convert = new ConvertGPS();
    ArrayList complain;

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
    }

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void draw() {
        /*
         for (int i = 0; i < 200; i++) {
         logger.info(klachten.get(i));
         }
         */
    }
    
    @Override
    public void keyPressed(){
        try{
            complain = convert.parseAndConvert(complaints);
            logger.info("Ready to write to file...");
            logger.info("Writing file");
            convert.writeToFile(complain);

        } catch(Exception e){
            logger.info(e);
        }
    }
}
