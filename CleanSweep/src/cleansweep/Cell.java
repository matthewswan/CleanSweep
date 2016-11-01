/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import utility.Coords;

/**
 *
 * @author MatthewSwan
 */
public class Cell {
    private Coords cellCoords;
    private int dirt;
    
    public Cell(Coords coordsIn, int dirtIn){
        cellCoords = new Coords(coordsIn.x, coordsIn.y);
        dirt = dirtIn;
    }
    
    Coords getCoords() {
        return new Coords(cellCoords.x, cellCoords.y);
    }
    
    int getDirtValue() {
        return dirt;
    }
    
    void removeDirt() throws NoDirtException {
        if(dirt < 1)
            throw new NoDirtException("Tried to remove dirt from cell " + cellCoords.toString() + " but it had no dirt.");
        dirt--;
    }
}
