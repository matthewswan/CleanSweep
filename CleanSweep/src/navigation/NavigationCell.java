/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;


import utility.Direction;
import utility.ObstacleType;
import utility.Path;
import utility.Coords;
import memory.ObservedCell;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class NavigationCell {
    private ObservedCell cell;
    private boolean marked;
    private double distance;
    private Path path;
    
    NavigationCell(ObservedCell cellIn) {
        cell = cellIn;
        marked = false;
        distance = Double.MAX_VALUE;
        path = new Path();
    }
    
    int getCost() {
        switch (cell.getCarpet()) {
            case BARE:
                return 1;
            case LOWPILE:
                return 2;
            case HIGHPILE:
                return 3;
        }
        throw new RuntimeException("CarpetType was set to an unexpected enumeration value.");
    }
    
    boolean isMarked() {
        return marked;
    }
    
    void setMarked(boolean markedIn) {
        marked = markedIn;
    }
    
    boolean canNavigate(Direction d) {
        return cell.getObstacle(d) == ObstacleType.NONE || cell.getObstacle(d) == ObstacleType.OPENDOOR;
    }
    
    boolean hasCharger() {
        return cell.hasCharger();
    }
    
    boolean hasDirt() {
        return cell.hasDirt();
    }
    
    double getDistance() {
        return distance;
    }
    
    void setDistance(double distanceIn) {
        distance = distanceIn;
    }
    
    Path getPath() {
        return new Path(path);
    }

    
    void setPath(Path pathIn) {
        path = new Path(pathIn);
    }
    
    Coords getCoords() {
        return cell.getCoords();
    }
}
