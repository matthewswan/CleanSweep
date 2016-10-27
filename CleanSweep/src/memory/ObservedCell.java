/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import utility.Coords;
import utility.CarpetType;
import utility.ObstacleType;
import utility.Direction;
import java.util.Map;
import java.util.EnumMap;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class ObservedCell {
    private Coords cellCoords;
    private CarpetType carpet;
    private boolean dirt;
    private Map<Direction, ObstacleType> borders;
    private boolean charger;
    
    ObservedCell(Coords coordsIn, CarpetType carpetIn, boolean dirtIn, boolean chargerIn, ObstacleType eastObstacle, ObstacleType southObstacle, ObstacleType westObstacle, ObstacleType northObstacle) {
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
    
    boolean hasDirt() {
        return dirt;
    }
    
    void clearDirt() {
        dirt = false;
    }
        
    ObstacleType getObstacle(Direction d) {
        return borders.get(d);
    }
    
    void setObstacle(Direction d, ObstacleType o) {
        borders.put(d, o);
    }
    
    boolean hasCharger() {
        return charger;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('o').append(obstacleNorthSouth(borders.get(Direction.NORTH))).append("o\n");
        sb.append(obstacleEastWest(borders.get(Direction.WEST),1)).append("     ").append(obstacleEastWest(borders.get(Direction.EAST),1)).append('\n');
        sb.append(obstacleEastWest(borders.get(Direction.WEST),2)).append(' ').append(carpet.toString().charAt(0)).append(dirt ? 'D' : ' ').append(charger ? 'C' : ' ').append(' ').append(obstacleEastWest(borders.get(Direction.EAST),2)).append('\n');
        sb.append(obstacleEastWest(borders.get(Direction.WEST),3)).append("     ").append(obstacleEastWest(borders.get(Direction.EAST),3)).append('\n');
        sb.append('o').append(obstacleNorthSouth(borders.get(Direction.SOUTH))).append("o\n");
        return sb.toString();
    }
            
    private String obstacleNorthSouth(ObstacleType obstacle) {
        String result = "     ";
        switch (obstacle) {
            case NONE:
                result = "     ";
                break;
            case OPENDOOR:
                result = "(   )";
                break;
            case WALL:
                result = "-----";
                break;
            case CLOSEDDOOR:
                result = "(---)";
                break;
            case STAIRSUP:
                result = "^^^^^";
                break;
            case STAIRSDOWN:
                result = "vvvvv";
                break;
        }
        return result;
    }
    
    private String obstacleEastWest (ObstacleType obstacle, int row) {
        String result = " ";
        switch (obstacle) {
            case NONE:
                result = " ";
                break;
            case OPENDOOR:
                if (row == 1) 
                    result = "^";
                if (row == 2)
                    result = " ";
                if (row == 3)
                    result = "v";
                break;
            case WALL:
                result = "|";
                break;
            case CLOSEDDOOR:
                if (row == 1) 
                    result = "^";
                if (row == 2)
                    result = "|";
                if (row == 3)
                    result = "v";
                break;
            case STAIRSUP:
                result = "<";
                break;
            case STAIRSDOWN:
                result = ">";
                break;
        }
        return result;
    }
    
    
}
