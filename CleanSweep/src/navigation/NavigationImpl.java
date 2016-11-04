/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import java.util.HashMap;
import memory.ObservedCell;
import utility.Coords;
import utility.Path;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class NavigationImpl implements Navigation {
    HashMap<Coords, NavigationCell> grid;
    
    public NavigationImpl() {
        grid = new HashMap<>();
    }
    
    public void addCell(ObservedCell cellIn) throws DuplicateCellException {
        Coords cellCoords = cellIn.getCoords();
        if (grid.containsKey(cellCoords))
            throw new DuplicateCellException("Tried to insert a cell at " + cellCoords.toString() + " but one was already there.");
        grid.put(cellCoords, new NavigationCell(cellIn));
    }
    
    @Override
    public Path findPath(Coords loc1, Coords loc2) throws NoPathException {
        clearMarks();
        // not yet implemented
        throw new NoPathException("Not yet implemented");
    }
    
    @Override
    public Path findNearestCharger(Coords location) throws NoPathException {
        // not yet implemented
        throw new NoPathException("Not yet implemented");
    }
    
    @Override
    public Path findNearestDirt(Coords location) throws NoPathException {
        // not yet implemented
        throw new NoPathException("Not yet implemented");
    }
    
    @Override
    public Path findNearestUnexplored(Coords location) throws NoPathException {
        // not yet implemented
        throw new NoPathException("Not yet implemented");
    }
    
    private void clearMarks() {
        grid.values().forEach(cell -> cell.setMarked(false));
    }
}
