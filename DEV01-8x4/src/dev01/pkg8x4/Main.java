/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev01.pkg8x4;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.ImmoScout;
import de.fhpotsdam.unfolding.utils.MapUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    //ArrayList to put in converted complaints in
    private ArrayList<Complaint> newComplaints;
    //ArrayList to put in converted complaints in
    private ArrayList<Complaint> markerPoints;
    //CSVParser object
    private final CSVParser parser = new CSVParser();
    //New ConvertGPS object
    private final ConvertGPS convert = new ConvertGPS();
    //CSVWriter to write arraylist items to csv file
    private final WriteToFile writer = new WriteToFile();
    //ReadFile to return ArrayList of new csv
    private final ReadFromFile read = new ReadFromFile();
    //Unfolding map
    private UnfoldingMap map;
    //Marker
    private SimplePointMarker marker;
    
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
        try {
            //Put ArrayList of Complaint object from the parser into this arraylist
            complaints = parser.read();
            ArrayList<Complaint> inputArray = startConvert();
            startWrite(inputArray);
            markerPoints = read.readFile();
            
        } catch (IOException ex) {
            logger.info(ex);
        }

        //New Unfolding map
        map = new UnfoldingMap(this, new Google.GoogleTerrainProvider());

        map.zoomAndPanTo(10, new Location(51.917f, 4.481f));

        MapUtils.createDefaultEventDispatcher(this, map);
        
        //Create markers
        createMarkers(markerPoints);
        createMarkerCrematoria();
       
    }
    
   
    @Override
    public void settings() {
        size(700, 500, FX2D);
    }

    @Override
    public void draw() {
        map.draw();
    }

    //Convert and parse objects from complaints ArrayList and put them in newComplaints ArrayList
    private ArrayList<Complaint> startConvert() {
        try {
            logger.info("Converting started.");
            newComplaints = convert.parseAndConvert(complaints);
            
        } catch (Exception ex) {
            logger.info(ex);
        }
        
        logger.info("Converting ended.");

        return newComplaints;
    }

    //Write items of newComplaints in a .csv file
    private void startWrite(ArrayList<Complaint> complaints) throws IOException {
        logger.info("Writing started.");
        writer.writeCsvFile(complaints);
        logger.info("Writing ended.");
    }
    
    //Create markers with longitude and latitude and put themm in the map
    private void createMarkers(ArrayList<Complaint> complaints){
        logger.info("complaints Size: " + complaints.size());
        for (int i = 0; i < complaints.size(); i++) {
            float longitude = (float)complaints.get(i).getLongitude();
            float latitude = (float)complaints.get(i).getLatitude();
            Location location = new Location(latitude, longitude);
            marker = new SimplePointMarker(location);
            marker.setColor(color(0, 0, 255, 255));
            
            map.addMarker(marker);
        }
    }
    
    //Create markers for crematoria with longitude and latitude and put themm in the map
    private void createMarkerCrematoria(){
        ArrayList<Location> crematoriaLocs = new ArrayList();
        
        crematoriaLocs.add(new Location(51.934116, 4.495517));
        crematoriaLocs.add(new Location(51.956863, 4.401860));
        crematoriaLocs.add(new Location(51.952843, 4.532323));
        crematoriaLocs.add(new Location(51.860692, 4.305141));
        crematoriaLocs.add(new Location(51.952843, 4.532323));
        crematoriaLocs.add(new Location(51.956888, 4.399592));
        crematoriaLocs.add(new Location(51.861627, 4.302023));
        crematoriaLocs.add(new Location(51.844450, 4.146841));
        crematoriaLocs.add(new Location(51.866175, 4.370530));
        crematoriaLocs.add(new Location(51.914115, 4.375251));
        crematoriaLocs.add(new Location(52.058103, 4.222917));
        crematoriaLocs.add(new Location(52.023047, 4.311494));
        crematoriaLocs.add(new Location(51.954123, 4.564180));
        crematoriaLocs.add(new Location(52.021664, 4.325493));
        crematoriaLocs.add(new Location(51.801510, 4.673141));
        crematoriaLocs.add(new Location(51.867154, 4.605017));
        crematoriaLocs.add(new Location(51.940138, 4.557817));
        crematoriaLocs.add(new Location(51.995077, 4.517034));
        crematoriaLocs.add(new Location(52.008895, 4.724828));
        crematoriaLocs.add(new Location(52.008525, 4.733926));
        crematoriaLocs.add(new Location(52.028571, 4.703199));
        crematoriaLocs.add(new Location(51.888695, 4.552438));
        crematoriaLocs.add(new Location(51.941163, 4.569214));
        
        for (int i = 0; i < crematoriaLocs.size(); i++) {
            Location loc = crematoriaLocs.get(i);
            Marker markers = new SimplePointMarker(loc) {};
            markers.setColor(color(255, 0, 0, 255));
            
            map.addMarker(markers);
        }
    }
    
    //Zoom in and out
    @Override
    public void mousePressed(){
        if (mousePressed && (mouseButton == LEFT)) {
            map.zoomIn();
            map.panTo(new Location(map.getLocation(mouseX, mouseY)));
        } else if (mousePressed && (mouseButton == RIGHT)) {
            map.zoomOut();
            map.panTo(new Location(map.getLocation(mouseX, mouseY)));
        }
    }

}
