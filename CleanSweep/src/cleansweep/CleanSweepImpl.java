/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import java.net.URISyntaxException;
import java.net.URL;
import simulator.SimulatorFactory;
import simulator.Simulator;
import simulator.InvalidLayoutFileException;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import utility.Coords;
/**
 *
 * @author James Doyle
 */
public class CleanSweepImpl {
    private final int MAX_DIRT_CAPACITY;
    private Coords initialLocation;
    
    CleanSweepImpl() {
        initialLocation = new Coords(0,0);
        MAX_DIRT_CAPACITY = 50;
    }
    
    public static ArrayList<Point2D.Double> currentPath;

        
    //Return Start Coordinates
    public Coords getInitialLocation() {
        return initialLocation;
    }
    
    public int getMaxCapacity() {
        return MAX_DIRT_CAPACITY;
    }

    //Move in 1 of 4 directions
    //public static Point2D.Double moveUp(Point2D.Double coords) {
      //  Point2D.Double currentCoords;
        //currentCoords = coords;
        //currentCoords.y = currentCoords.y + 1;
        //return currentCoords;
    //}
    public static Coords moveUp(Coords coords) {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y + 1;
        return currentCoords;
    }

    public static Coords moveDown(Coords coords) {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y - 1;
        return currentCoords;
    }

    public static Coords moveRight(Coords coords) {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x + 1;
        return currentCoords;
    }

    public static Coords moveLeft(Coords coords) {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x - 1;
        return currentCoords;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
            String path = fileUrl.toURI().getPath();
            Simulator sim = SimulatorFactory.createSimulator(path, 0, 0);
            System.out.println(sim.toString());
        }
        catch (InvalidLayoutFileException | URISyntaxException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
}