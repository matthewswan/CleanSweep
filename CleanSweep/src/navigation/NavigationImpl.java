/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Optional;
import memory.ObservedCell;
import utility.Coords;
import utility.Path;
import utility.Direction;
import utility.InvalidCoordinatesException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class NavigationImpl implements Navigation {
    HashMap<Coords, NavigationCell> grid;
    
    public NavigationImpl() {
        grid = new HashMap<>();
    }
    
    @Override
    public void addCell(ObservedCell cellIn) throws DuplicateCellException {
        Coords cellCoords = cellIn.getCoords();
        if (grid.containsKey(cellCoords))
            throw new DuplicateCellException("Tried to insert a cell at " + cellCoords.toString() + " but one was already there.");
        grid.put(cellCoords, new NavigationCell(cellIn));
    }
    
    @Override
    public Path findPath(Coords locFrom, Coords locTo) throws NoPathException, InvalidCoordinatesException {
        clearMarks();
        if(!(grid.containsKey(locFrom) && grid.containsKey(locTo)))
            throw new InvalidCoordinatesException("Unable to find path.  No cell in memory at provided coordinates.");
        NavigationCell activeCell = grid.get(locFrom);
        activeCell.setDistance(0.0);
        while(!grid.get(locTo).isMarked()) {
            double currentDistance = activeCell.getDistance();
            Path currentPath = activeCell.getPath();
            int activeX = activeCell.getCoords().x;
            int activeY = activeCell.getCoords().y;
            NavigationCell neighborCell;
            // Check east
            if (activeCell.canNavigate(Direction.EAST) && grid.containsKey(new Coords(activeX + 1, activeY))) {
                neighborCell = grid.get(new Coords(activeX + 1, activeY));
                updateNeighbor(currentDistance, currentPath, Direction.EAST, activeCell, neighborCell);
            }
            // Check south
            if (activeCell.canNavigate(Direction.SOUTH) && grid.containsKey(new Coords(activeX, activeY - 1))) {
                neighborCell = grid.get(new Coords(activeX, activeY - 1));
                updateNeighbor(currentDistance, currentPath, Direction.SOUTH, activeCell, neighborCell);
            }
            // Check west
            if (activeCell.canNavigate(Direction.WEST) && grid.containsKey(new Coords(activeX - 1, activeY))) {
                neighborCell = grid.get(new Coords(activeX - 1, activeY));
                updateNeighbor(currentDistance, currentPath, Direction.WEST, activeCell, neighborCell);
            }
            // Check north
            if (activeCell.canNavigate(Direction.NORTH) && grid.containsKey(new Coords(activeX, activeY + 1))) {
                neighborCell = grid.get(new Coords(activeX, activeY + 1));
                updateNeighbor(currentDistance, currentPath, Direction.NORTH, activeCell, neighborCell);
            }
            activeCell.setMarked(true);
            if (!grid.get(locTo).isMarked())
            {
                Optional<NavigationCell> newCell = grid.values().stream()
                        .filter(cell -> !cell.isMarked())
                        .sorted((cell1, cell2) -> Double.compare(cell1.getDistance(), cell2.getDistance()))
                        .findFirst();
                if (newCell.isPresent())
                    activeCell = newCell.get();
                else
                    throw new NoPathException("No path was found between " + locFrom.toString() + " and " + locTo.toString() + ".");
                if (activeCell.getDistance() == Double.MAX_VALUE)
                    throw new NoPathException("No path was found between " + locFrom.toString() + " and " + locTo.toString() + ".");
            }
        } 
        return activeCell.getPath();
    }
    
    private void updateNeighbor(double currentDistance, Path currentPath, Direction d, NavigationCell activeCell, NavigationCell neighborCell) {
        double distanceToNeighbor = currentDistance + 0.5 * activeCell.getCost() + 0.5 * neighborCell.getCost();
        if (!neighborCell.isMarked() && distanceToNeighbor < neighborCell.getDistance()) {
            Path neighborPath = new Path(currentPath);
            neighborPath.add(d, 0.5 * activeCell.getCost() + 0.5 * neighborCell.getCost());
            neighborCell.setPath(neighborPath);
            neighborCell.setDistance(distanceToNeighbor);
        }
    }
    
    @Override
    public Path findNearestCharger(Coords location) throws NoPathException, InvalidCoordinatesException {
        NavigationCell[] allChargerCells = grid.values().stream()
                .filter(cell -> cell.hasCharger())
                .toArray(NavigationCell[]::new);
        ArrayList<Path> pathsToChargers = new ArrayList<>();
        ArrayList<ArrayList<Object>> endCoordsList = new ArrayList<>();
        ArrayList<Object> collection = new ArrayList<Object>();
        Path p=null;
        Coords coord = null;
        for(NavigationCell cell : allChargerCells) {
            coord = cell.getCoords();
            p = findPath(location, coord);
            pathsToChargers.add(p);
            collection.add(p);
            collection.add(coord);
            endCoordsList.add(collection);
        }
        if(pathsToChargers.isEmpty())
            throw new NoPathException("No path found to any charger.");
        Path charger = pathsToChargers.stream()
                .sorted((path1, path2) -> Double.compare(path1.remainingCost(), path2.remainingCost()))
                .findFirst()
                .get();
        Coords endCoords = null;
        for(int i =0; i<endCoordsList.size();i++){
            if(((Path)(endCoordsList.get(i).get(0))).equals(charger)){
                endCoords=(Coords) (endCoordsList.get(i).get(1));
            }
        }
        charger.setEndCoords(endCoords);

        return charger;
    }

    @Override
    public Path findNearestDirt(Coords location) throws NoPathException, InvalidCoordinatesException {
        NavigationCell[] allDirtyCells = grid.values().stream()
                .filter(cell -> cell.hasDirt())
                .toArray(NavigationCell[]::new);
        ArrayList<Path> pathsToDirty = new ArrayList<>();
        ArrayList<Coords> coordsToDirty = new ArrayList<>();
        for(NavigationCell cell : allDirtyCells) {
            Coords cellCoords=cell.getCoords();
            Path p = findPath(location, cellCoords);
            if(p.getCosts().size()!=0) {
                coordsToDirty.add(cellCoords);
                pathsToDirty.add(p);
            }
        }

        if(coordsToDirty.isEmpty()||(coordsToDirty.get(coordsToDirty.size()-1)).equals(location)){
            return null;
        }
        if(pathsToDirty.isEmpty())
            throw new NoPathException("No path found to any dirty cell.");
        return pathsToDirty.stream()
                .sorted((path1, path2) -> Double.compare(path1.remainingCost(), path2.remainingCost()))
                .findFirst()
                .get();
    }
    
    @Override
    public Path findNearestUnexplored(Coords location) throws NoPathException, InvalidCoordinatesException {
        ArrayList<Path> pathsToUnexplored = new ArrayList<>();
        ArrayList<Coords> pathsToUnexploredCoords = new ArrayList<>();
        for (NavigationCell cell : grid.values()) 
            if ((cell.canNavigate(Direction.EAST) && !grid.containsKey(new Coords(cell.getCoords().x + 1, cell.getCoords().y)))
                    || (cell.canNavigate(Direction.SOUTH) && !grid.containsKey(new Coords(cell.getCoords().x, cell.getCoords().y - 1)))
                    || (cell.canNavigate(Direction.WEST) && !grid.containsKey(new Coords(cell.getCoords().x - 1, cell.getCoords().y)))
                    || (cell.canNavigate(Direction.NORTH) && !grid.containsKey(new Coords(cell.getCoords().x, cell.getCoords().y + 1)))) {
                Coords coordsUnexplored=cell.getCoords();
                Path p = findPath(location, coordsUnexplored);
                if(p.getCosts().size()!=0) {
                    pathsToUnexplored.add(p);
                    pathsToUnexploredCoords.add(coordsUnexplored);
                }
            }
        if (pathsToUnexplored.isEmpty())
            throw new NoPathException("No path found to any unexplored cell.");
        if(pathsToUnexploredCoords.isEmpty()||(pathsToUnexploredCoords.get(pathsToUnexploredCoords.size()-1)).equals(location)){
            return null;
        }
        return pathsToUnexplored.stream()
                .sorted((path1, path2) -> Double.compare(path1.remainingCost(), path2.remainingCost()))
                .findFirst()
                .get();
    }
    
    private void clearMarks() {
        grid.values().forEach(cell -> { cell.setMarked(false); cell.setDistance(Double.MAX_VALUE); cell.setPath(new Path()); });
    }
}
