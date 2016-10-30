/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import java.awt.geom.Point2D;
import utility.Direction;
import utility.InvalidMoveException;

/**
 *
 * @author MatthewSwan
 */
public interface CleanSweep {
    public void moveUp(Direction direction) throws InvalidMoveException;
    public void moveDown(Direction direction) throws InvalidMoveException;
    public void moveRight(Direction direction) throws InvalidMoveException;
    public void moveLeft(Direction direction) throws InvalidMoveException;
}
