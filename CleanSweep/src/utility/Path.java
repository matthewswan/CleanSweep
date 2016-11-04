/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class Path {
    Queue<Direction> sequence;
    Queue<Integer> costs;
    int remainingCost;
    
    public Path() {
        sequence = new LinkedList<>();
        costs = new LinkedList<>();
        remainingCost = 0;
    }
    
    public Path(Path pathIn) {
        sequence = new LinkedList<>(pathIn.sequence);
        costs = new LinkedList<>(pathIn.costs);
        remainingCost = pathIn.remainingCost();
    }
    
    public void add(Direction d, int costIn) {
        sequence.add(d);
        costs.add(costIn);
        remainingCost += costIn;
    }
    
    public int nextCost() {
        return costs.peek();
    }
    
    public Direction nextDirection() {
        return sequence.peek();
    }
    
    public Direction moveAlong() throws NoMoreDirectionsException {
        try {
            remainingCost -= costs.remove();
            return sequence.remove();
        }
        catch (NoSuchElementException e) {
            throw new NoMoreDirectionsException();
        }
    }
    
    public int remainingCost() {
        return remainingCost;
    }
}
