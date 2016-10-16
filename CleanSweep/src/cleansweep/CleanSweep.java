/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

/**
 *
 * @author James Doyle
 */
public class CleanSweep {

    public static ArrayList<Point2D.Double> currentPath;

    //Start Position for CleanSweep Device, always starts at (0,0)
    public static Point2D.Double startCoords = new Point2D.Double(0,0);

    //Return Start Coordinates
    public static Point2D.Double getStartCoords() {
        return startCoords;
    }

    //Move in 1 of 4 directions
    public static Point2D.Double moveUp(Point2D.Double coords) {
        Point2D.Double currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y + 1;
        return currentCoords;
    }

    public static Point2D.Double moveDown(Point2D.Double coords) {
        Point2D.Double currentCoords;
        currentCoords = coords;
        currentCoords.y = currentCoords.y - 1;
        return currentCoords;
    }

    public static Point2D.Double moveRight(Point2D.Double coords) {
        Point2D.Double currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x + 1;
        return currentCoords;
    }

    public static Point2D.Double moveLeft(Point2D.Double coords) {
        Point2D.Double currentCoords;
        currentCoords = coords;
        currentCoords.x = currentCoords.x - 1;
        return currentCoords;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
