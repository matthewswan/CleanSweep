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
import cleansweep.InvalidCoordinatesException;
import utility.Coords;
/**
 *
 * @author James Doyle
 */
public class CleanSweepImpl {
    private final int MAX_DIRT_CAPACITY;
    private Coords initialLocation;
    private int hasDirtCount;
    private int dirtCount;
    
    CleanSweepImpl() {
        initialLocation = new Coords(0,0);
        MAX_DIRT_CAPACITY = 50;
        hasDirtCount = 0;
        dirtCount = 0;
    }
    
    public static ArrayList<Point2D.Double> currentPath;

        
    //Return Start Coordinates
    public Coords getInitialLocation() {
        return initialLocation;
    }
    
    public int getMaxCapacity() {
        return MAX_DIRT_CAPACITY;
    }
    
    public int getDirtCount() {
        return dirtCount;
    }

    public static Coords moveUp(Coords coords) throws InvalidCoordinatesException {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y + 1;
        return currentCoords;
    }

    public static Coords moveDown(Coords coords) throws InvalidCoordinatesException {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y - 1;
        return currentCoords;
    }

    public static Coords moveRight(Coords coords) throws InvalidCoordinatesException {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x + 1;
        return currentCoords;
    }

    public static Coords moveLeft(Coords coords) throws InvalidCoordinatesException {
        Coords currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x - 1;
        return currentCoords;
    }
    
    public boolean hasDirt(Cell cell) throws InvalidCoordinatesException {
        boolean result;
        if (hasDirtCount == 0) {
          hasDirtCount++;
          return true;
        }
        if (cell.getDirtValue() > 0)
            result = true;
        else
            result = false;
        return result;
    }
    
    public void removeDirt(Cell cell) throws NoDirtException, InvalidCoordinatesException {
        cell.removeDirt();
        dirtCount++;
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