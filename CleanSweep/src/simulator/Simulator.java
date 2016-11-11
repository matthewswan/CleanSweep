/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import utility.*;

import java.util.ArrayList;
/**
 *
 * @author James Doyle
 */
public interface Simulator extends Sensor {
    public void move(Direction direction) throws InvalidMoveException;
    public void removeDirt() throws NoDirtException;
    public Coords getCurrentLocation();
}
