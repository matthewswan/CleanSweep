/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.ArrayList;
/**
 *
 * @author James Doyle
 */
public interface Simulator {
    public void move(Direction direction) throws InvalidMoveException;
    public boolean isDirty();
    public void removeDirt() throws NoDirtException;
    public CarpetType checkSurfaceAtLocation();
    public boolean hasChargingStation();
    public ObstacleType getObstacle(Direction direction);
    public CarpetType checkSurfaceAdjacent(Direction direction) throws InvalidMoveException;
    public ArrayList<ArrayList<Direction>> findChargingStations();
}
