/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import cleansweep.CleanSweep;
import cleansweep.CleanSweepFactory;
/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class CleanSweepRun {
    
    public static void main(String[] args) {
        CleanSweep cleansweep = CleanSweepFactory.createCleanSweep();
        cleansweep.runSimulation();
    }
}
