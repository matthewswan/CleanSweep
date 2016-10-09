/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.HashMap;
import java.util.EnumMap;
import java.util.Map;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class LayoutImpl implements Layout {
    private HashMap<Coords, LayoutCell> grid;
    
    @Override
    public CarpetType getCarpet(Coords cell) throws InvalidCoordinatesException {
        CarpetType result = null;
        if (grid.containsKey(cell))
            result = grid.get(cell).getCarpet();
        else
            throw new InvalidCoordinatesException("Could not find a cell at " + cell.toString() + " in LayoutImpl.getCarpet.");
        return result;
    }
    
    @Override
    public boolean hasDirt(Coords cell) throws InvalidCoordinatesException {
        boolean result;
        if (grid.containsKey(cell))
            result = grid.get(cell).getDirtValue() > 0;
        else
            throw new InvalidCoordinatesException("Could not find a cell at " + cell.toString() + " in LayoutImpl.hasDirt.");
        return result;
    }
    
    @Override
    public void removeDirt(Coords cell) throws NoDirtException, InvalidCoordinatesException {
        if (!grid.containsKey(cell))
            throw new InvalidCoordinatesException("Could not find a cell at " + cell.toString() + " in LayoutImpl.removeDirt.");
        grid.get(cell).removeDirt();
    }
    
    @Override
    public Map<Direction, ObstacleType> getBorders(Coords cell) throws InvalidCoordinatesException {
        EnumMap<Direction, ObstacleType> result = new EnumMap<>(Direction.class);
        if (!grid.containsKey(cell))
            throw new InvalidCoordinatesException("Could not find a cell at " + cell.toString() + " in LayoutImpl.getBorders.");
        result.put(Direction.EAST, grid.get(cell).getObstacle(Direction.EAST));
        result.put(Direction.SOUTH, grid.get(cell).getObstacle(Direction.SOUTH));
        result.put(Direction.WEST, grid.get(cell).getObstacle(Direction.WEST));
        result.put(Direction.NORTH, grid.get(cell).getObstacle(Direction.NORTH));
        return result;
    }
    
    @Override
    public boolean hasCharger(Coords cell) throws InvalidCoordinatesException {
        boolean result;
        if (!grid.containsKey(cell))
            throw new InvalidCoordinatesException("Could not find a cell at " + cell.toString() + " in LayoutImpl.hasCharger.");
        result = grid.get(cell).hasCharger();
        return result;
    }
}
