/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.ArrayList;

/**
 *
 * @author JamesDoyle
 */
public class SimulatorImpl implements Simulator {
    private Coords currentLocation;
   
    @Override
    public void move(Direction direction) throws InvalidMoveException {
        ObstacleType obstacle = getObstacle(direction);
        if (obstacle != ObstacleType.NONE)
            throw new InvalidMoveException("Tried to cross obstacle: " + obstacle.toString());
        currentLocation.addOffset(directionOffset(direction));
    }
    
    @Override
    public boolean isDirty() {
        return true; // ### To Implement
    }
    
    @Override
    public void removeDirt() throws NoDirtException {
        
    }
    
    @Override
    public CarpetType checkSurfaceAtLocation() {
        return CarpetType.BARE;  // ### To Implement
    }
    
    @Override
    public boolean hasChargingStation() {
        return true; // ### To Implement
    }
    
    @Override
    public ObstacleType getObstacle(Direction direction) {
        return ObstacleType.NONE;  // ### To Implement
    }
    
    @Override
    public CarpetType checkSurfaceAdjacent(Direction direction) throws InvalidMoveException {
        return CarpetType.BARE;  // ### To Implement
    }
    
    @Override
    public ArrayList<ArrayList<Direction>> findChargingStations() {
        return null;  // ### To Implement
    }
    
    private Coords directionOffset(Direction direction) {
        switch(direction) {
            case EAST:
                return new Coords(1, 0);
            case SOUTH:
                return new Coords(0, -1);
            case WEST:
                return new Coords(-1, 0);
            case NORTH:
                return new Coords(0, 1);       
        }
        return null;
    }
            
}
