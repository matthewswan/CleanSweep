/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.util.HashMap;
import utility.Coords;
import utility.Direction;
import utility.Sensor;
import utility.NullSensorException;
import utility.ObstacleType;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class MemoryImpl implements Memory {
    Sensor sensor;
    private HashMap<Coords, ObservedCell> grid;
    
    MemoryImpl(Sensor sensorIn) throws NullSensorException {
        if (sensorIn == null)
            throw new NullSensorException("Cannot link CleanSweep memory to a null sensor.");
        sensor = sensorIn;
        grid = new HashMap<>();
    }
    
    @Override
    public void observeCell(Coords coordsIn) throws UnexpectedChangeException {
        ObservedCell oldCell = grid.get(coordsIn);
        if (oldCell == null) {
            ObservedCell newCell = new ObservedCell(coordsIn, sensor.checkSurfaceAtLocation(), sensor.isDirty(), sensor.hasChargingStation(), sensor.getObstacle(Direction.EAST), sensor.getObstacle(Direction.SOUTH), sensor.getObstacle(Direction.WEST), sensor.getObstacle(Direction.NORTH));
            grid.put(coordsIn, newCell);
        } else {
            if (oldCell.hasCharger() != sensor.hasChargingStation() || oldCell.getCarpet() != sensor.checkSurfaceAtLocation())
                throw new UnexpectedChangeException("Either the carpet type changed or presence of a charging station changed for existing cell at " + coordsIn.toString() + ".");
            for (Direction d : Direction.values()) {
                boolean validChange = false;
                if (oldCell.getObstacle(d) == sensor.getObstacle(d))
                    validChange = true;
                if (oldCell.getObstacle(d) == ObstacleType.OPENDOOR && sensor.getObstacle(d) == ObstacleType.CLOSEDDOOR) {
                    validChange = true;
                    oldCell.setObstacle(d, ObstacleType.CLOSEDDOOR);
                }
                if (oldCell.getObstacle(d) == ObstacleType.CLOSEDDOOR && sensor.getObstacle(d) == ObstacleType.OPENDOOR) {
                    validChange = true;
                    oldCell.setObstacle(d, ObstacleType.OPENDOOR);
                }
                if (!validChange)
                    throw new UnexpectedChangeException("One of the obstacles changed and it wasn't a toggle of a door's open status in existing cell at " + coordsIn.toString() + ".");
            }
            if (oldCell.hasDirt() == false && sensor.isDirty() == true)    
                throw new UnexpectedChangeException("Somehow more dirt got added to the carpet at cell " + coordsIn.toString() + ".");
            if (oldCell.hasDirt() == true && sensor.isDirty() == false)
                oldCell.clearDirt();
        }
    }
}
