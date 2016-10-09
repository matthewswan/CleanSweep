/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
 *
 * @author James Doyle
 */
public class Coords {
    public int x;
    public int y;
    
    public Coords(int xIn, int yIn) {
        x = xIn;
        y = yIn;
    }
    
    public void addOffset(Coords offset) {
        x += offset.x;
        y += offset.y;
    }
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
