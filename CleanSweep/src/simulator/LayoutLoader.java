/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
class LayoutLoader {
    String filename;
    
    public LayoutLoader(String filenameIn) {
        filename = filenameIn;
    }
    
    public HashMap<Coords, LayoutCell> loadFromFile() throws InvalidLayoutFileException {
        HashMap<Coords, LayoutCell> result = new HashMap<>();
        try(BufferedReader br = new BufferedReader(new FileReader(filename)))
        {
            int lineCount = 0;
            String line = br.readLine();
            while (line != null)
            {
                lineCount++;
                String[] segments = line.split(",");
                if (segments.length != 9)
                    throw new InvalidLayoutFileException("Need nine segments in input line " + lineCount + ".");
                Coords newCellCoords = new Coords(Integer.parseInt(segments[0]), Integer.parseInt(segments[1]));
                if (result.containsKey(newCellCoords))
                    throw new InvalidLayoutFileException("Duplicate cell coordinates in input line " + lineCount + ".");
                CarpetType newCellCarpet = CarpetType.valueOf(segments[2]);
                int newCellDirt = Integer.parseInt(segments[3]);
                boolean newCellCharger = Boolean.valueOf(segments[4]);
                ObstacleType eastObstacle = ObstacleType.valueOf(segments[5]);
                ObstacleType southObstacle = ObstacleType.valueOf(segments[6]);
                ObstacleType westObstacle = ObstacleType.valueOf(segments[7]);
                ObstacleType northObstacle = ObstacleType.valueOf(segments[8]);
                LayoutCell newCell = new LayoutCell(newCellCoords, newCellCarpet, newCellDirt, newCellCharger, eastObstacle, southObstacle, westObstacle, northObstacle);
                result.put(newCellCoords, newCell);
            }
        }
        catch(IOException e)
        {
            throw new InvalidLayoutFileException("An IO error occurred when reading from the layout file: " + e.getMessage());
        }
        catch(NumberFormatException e)
        {
            throw new InvalidLayoutFileException("Expected a numbeer but didn't get one: " + e.getMessage());
        }
        
        return result;
    }
}
