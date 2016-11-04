/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import utility.Coords;
import utility.InvalidCoordinatesException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public interface Memory {
    public void observeCell(Coords coordsIn) throws UnexpectedChangeException;
    public boolean hasDirt(Coords coordsIn) throws InvalidCoordinatesException;
    public boolean hasCharger(Coords coordsIn) throws InvalidCoordinatesException;
}
