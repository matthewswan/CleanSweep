/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.Map;
/**
 *
 * @author James Doyle
 */
interface Layout {
    public CarpetType getCarpet(Coords cell) throws InvalidCoordinatesException;
    public boolean hasDirt(Coords cell) throws InvalidCoordinatesException;
    public void removeDirt(Coords cell) throws NoDirtException, InvalidCoordinatesException;
    public Map<Direction, ObstacleType> getBorders(Coords cell) throws InvalidCoordinatesException;
    public boolean hasCharger(Coords cell) throws InvalidCoordinatesException;
}
