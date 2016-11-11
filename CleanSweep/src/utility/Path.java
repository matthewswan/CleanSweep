/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class Path {
    Queue<Direction> sequence;
    Queue<Double> costs;
    double remainingCost;
    Coords endCoords;
    
    public Path() {
        sequence = new LinkedList<>();
        costs = new LinkedList<>();
        remainingCost = 0.0;
    }
    
    public Path(Path pathIn) {
        sequence = new LinkedList<>(pathIn.sequence);
        costs = new LinkedList<>(pathIn.costs);
        remainingCost = pathIn.remainingCost();
    }

    public Coords getEndCoords(){return endCoords;}
    public void setEndCoords(Coords value){endCoords=value;}
    public Queue<Double> getCosts(){return new LinkedList<>(costs);}

    public void add(Direction d, double costIn) {
        sequence.add(d);
        costs.add(costIn);
        remainingCost += costIn;
    }
    
    public double nextCost() {
        if(costs.isEmpty()){
            return 0.0;
        }
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
    
    public double remainingCost() {
        return remainingCost;
    }

}
