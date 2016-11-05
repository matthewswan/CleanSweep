/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.ArrayList;
import java.util.HashMap;
import navigation.Navigation;
import navigation.NavigationImpl;
import navigation.DuplicateCellException;
import utility.Coords;
import utility.Direction;
import utility.Sensor;
import utility.NullSensorException;
import utility.InvalidCoordinatesException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class MemoryImpl implements Memory {
    Sensor sensor;
    private HashMap<Coords, ObservedCell> grid;
    Navigation navigation;
    
    MemoryImpl(Sensor sensorIn) throws NullSensorException {
        if (sensorIn == null)
            throw new NullSensorException("Cannot link CleanSweep memory to a null sensor.");
        sensor = sensorIn;
        grid = new HashMap<>();
        navigation = new NavigationImpl();
    }
    
    @Override
    public void observeCell(Coords coordsIn) throws UnexpectedChangeException {
        ObservedCell oldCell = grid.get(coordsIn);
        if (oldCell == null) {
            ObservedCell newCell = new ObservedCell(coordsIn, sensor.checkSurfaceAtLocation(), sensor.isDirty(), sensor.hasChargingStation(), sensor.getObstacle(Direction.EAST), sensor.getObstacle(Direction.SOUTH), sensor.getObstacle(Direction.WEST), sensor.getObstacle(Direction.NORTH));
            grid.put(coordsIn, newCell);
            try {
                navigation.addCell(newCell);
            }
            catch (DuplicateCellException e) {
                throw new RuntimeException("Somehow the memory and navigation grids got out of sync; tried to insert a duplicate cell to navigation grid.");
            }
        } else {
            if (oldCell.hasCharger() != sensor.hasChargingStation() || oldCell.getCarpet() != sensor.checkSurfaceAtLocation())
                throw new UnexpectedChangeException("Either the carpet type changed or presence of a charging station changed for existing cell at " + coordsIn.toString() + ".");
            for (Direction d : Direction.values()) {
                oldCell.setObstacle(d, sensor.getObstacle(d));
            }
            if (oldCell.hasDirt() == false && sensor.isDirty() == true)    
                throw new UnexpectedChangeException("Somehow more dirt got added to the carpet at cell " + coordsIn.toString() + ".");
            if (oldCell.hasDirt() == true && sensor.isDirty() == false)
                oldCell.clearDirt();
        }
    }
    
    @Override
    public boolean hasDirt(Coords coordsIn) throws InvalidCoordinatesException {
        if (!grid.containsKey(coordsIn)) 
            throw new InvalidCoordinatesException("Location " + coordsIn.toString() + " has not been explored.");
        return grid.get(coordsIn).hasDirt();
    }
    
    @Override
    public boolean hasCharger(Coords coordsIn) throws InvalidCoordinatesException {
        if (!grid.containsKey(coordsIn)) 
            throw new InvalidCoordinatesException("Location " + coordsIn.toString() + " has not been explored.");
        return grid.get(coordsIn).hasCharger();
    }
    
    @Override
    public String toString() {
        ArrayList<StringBuilder> sbs = new ArrayList<>();
        StringBuilder result = new StringBuilder();
        int miny = Integer.MAX_VALUE;
        int maxy = Integer.MIN_VALUE;
        int minx = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        for (Coords cell : grid.keySet()) {
            miny = Integer.min(miny, cell.y);
            maxy = Integer.max(maxy, cell.y);
            minx = Integer.min(minx, cell.x);
            maxx = Integer.max(maxx, cell.x);
        }
        for (int row = maxy; row >= miny; row--) {
            StringBuilder[] lines = new StringBuilder[5];
            for (int i = 0; i < 5; i++)
                lines[i] = new StringBuilder();
            for (int col = minx; col <= maxx; col++) {
                if (!grid.containsKey(new Coords(col, row)))
                    for (int i = 0; i < 5; i++)
                        lines[i].append("       ");
                else {
                    String[] cellString = grid.get(new Coords(col, row)).toString().split("\n");
                    for (int i = 0; i < 5; i++)
                        lines[i].append(cellString[i]);
                }
            }
            for (int i = 0; i < 5; i++)
                sbs.add(lines[i]);
        }
        for (StringBuilder sb : sbs)
            result.append(sb).append('\n');
        return result.toString();
    }
}
