/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import memory.ObservedCell;
import utility.Coords;
import utility.Path;
import utility.InvalidCoordinatesException;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public interface Navigation {
    public void addCell(ObservedCell cellIn) throws DuplicateCellException;
    public Path findPath(Coords locFrom, Coords locTo) throws NoPathException, InvalidCoordinatesException;
    public Path findNearestCharger(Coords location) throws NoPathException, InvalidCoordinatesException;
    public Path findNearestDirt(Coords location) throws NoPathException, InvalidCoordinatesException;
    public Path findNearestUnexplored(Coords location) throws NoPathException, InvalidCoordinatesException;
}
