/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.Map;
import java.util.EnumMap;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class LayoutCell {
    private Coords cellCoords;
    private CarpetType carpet;
    private int dirt;
    private Map<Direction, ObstacleType> borders;
    private boolean charger;
    
    LayoutCell(Coords coordsIn, CarpetType carpetIn, int dirtIn, boolean chargerIn, ObstacleType eastObstacle, ObstacleType southObstacle, ObstacleType westObstacle, ObstacleType northObstacle) {
        cellCoords = new Coords(coordsIn.x, coordsIn.y);
        carpet = carpetIn;
        dirt = dirtIn;
        charger = chargerIn;
        borders = new EnumMap<>(Direction.class);
        borders.put(Direction.EAST, eastObstacle);
        borders.put(Direction.SOUTH, southObstacle);
        borders.put(Direction.WEST, westObstacle);
        borders.put(Direction.NORTH, northObstacle);
    }
    
    Coords getCoords() {
        return new Coords(cellCoords.x, cellCoords.y);
    }
    
    CarpetType getCarpet() {
        return carpet;
    }
    
    int getDirtValue() {
        return dirt;
    }
    
    void removeDirt() throws NoDirtException {
        if(dirt < 1)
            throw new NoDirtException("Tried to remove dirt from cell " + cellCoords.toString() + " but it had no dirt.");
        dirt--;
    }
    
    ObstacleType getObstacle(Direction d) {
        return borders.get(d);
    }
    
    boolean hasCharger() {
        return charger;
    }
}
