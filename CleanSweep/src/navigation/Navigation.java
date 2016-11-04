/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import utility.Coords;
import utility.Path;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public interface Navigation {
    public Path findPath(Coords loc1, Coords loc2) throws NoPathException;
    public Path findNearestCharger(Coords location) throws NoPathException;
    public Path findNearestDirt(Coords location) throws NoPathException;
    public Path findNearestUnexplored(Coords location) throws NoPathException;
}
