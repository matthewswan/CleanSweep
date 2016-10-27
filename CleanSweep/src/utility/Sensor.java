/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public interface Sensor {
    public boolean isDirty();
    public CarpetType checkSurfaceAtLocation();
    public boolean hasChargingStation();
    public ObstacleType getObstacle(Direction direction);
    public CarpetType checkSurfaceAdjacent(Direction direction) throws InvalidMoveException;
    public ArrayList<ArrayList<Direction>> findChargingStations();
}
