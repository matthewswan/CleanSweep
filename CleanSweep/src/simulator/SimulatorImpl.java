/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import utility.InvalidCoordinatesException;
import utility.InvalidMoveException;
import utility.Direction;
import utility.ObstacleType;
import utility.CarpetType;
import utility.Coords;
import java.util.ArrayList;

/**
 *
 * @author JamesDoyle
 */
public class SimulatorImpl implements Simulator {
    private Coords currentLocation;
    private Layout layout;
   
    SimulatorImpl(String layoutFile, Coords initialLocation) throws InvalidLayoutFileException {
        currentLocation = new Coords(initialLocation.x, initialLocation.y);
        layout = new LayoutImpl(layoutFile);
    }

    public Coords getCurrentLocation(){return new Coords(currentLocation.x,currentLocation.y);}

    @Override
    public void move(Direction direction) throws InvalidMoveException {
        ObstacleType obstacle = getObstacle(direction);
        if (obstacle != ObstacleType.NONE && obstacle != ObstacleType.OPENDOOR)
            throw new InvalidMoveException("Tried to cross obstacle: " + obstacle.toString());
        currentLocation.addOffset(directionOffset(direction));
    }
    
    @Override
    public boolean isDirty() {
        boolean result = false;
        try
        {
            result = layout.hasDirt(currentLocation);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
        return result;
    }
    
    @Override
    public void removeDirt() throws NoDirtException {
        try
        {
            layout.removeDirt(currentLocation);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
    }
    
    @Override
    public CarpetType checkSurfaceAtLocation() {
        CarpetType result;
        try
        {
            result = layout.getCarpet(currentLocation);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
        return result;
    }
    
    @Override
    public boolean hasChargingStation() {
        boolean result;
        try
        {
            result = layout.hasCharger(currentLocation);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
        return result;
    }
    
    @Override
    public ObstacleType getObstacle(Direction direction) {
        ObstacleType result;
        try
        {
            result = layout.getBorders(currentLocation).get(direction);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
        return result;
    }
    
    @Override
    public CarpetType checkSurfaceAdjacent(Direction direction) throws InvalidMoveException {
        CarpetType result;
        ObstacleType obstacle = getObstacle(direction);
        if (obstacle != ObstacleType.NONE && obstacle != ObstacleType.OPENDOOR)
            throw new InvalidMoveException("Tried to look through obstacle: " + obstacle.toString());
        Coords adjCoords = new Coords(currentLocation.x, currentLocation.y);
        adjCoords.addOffset(directionOffset(direction));
        try
        {
            result = layout.getCarpet(adjCoords);
        }
        catch(InvalidCoordinatesException e)
        {
            System.err.println("Simulation is in invalid state: " + e.getMessage());
            throw new InvalidSimulationException("Simulation error, see error console for details.");
        }
        return result;
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
    
    @Override
    public String toString() {
        return "CleanSweep at location " + currentLocation.toString() + ".\n" + layout.toString();
        // ### Add state here
    }
            
}
