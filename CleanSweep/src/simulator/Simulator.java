/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import utility.InvalidMoveException;
import utility.Direction;
import utility.ObstacleType;
import utility.CarpetType;
import utility.Sensor;
import java.util.ArrayList;
/**
 *
 * @author James Doyle
 */
public interface Simulator extends Sensor {
    public void move(Direction direction) throws InvalidMoveException;
    public void removeDirt() throws NoDirtException;
}
