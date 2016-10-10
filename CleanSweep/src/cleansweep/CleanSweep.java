/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import simulator.SimulatorFactory;
import simulator.Simulator;
import simulator.InvalidLayoutFileException;

/**
 *
 * @author James Doyle
 */
public class CleanSweep {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            Simulator sim = SimulatorFactory.createSimulator("C:/temp/samplefloorplan.txt", 0, 0);
            System.out.println(sim.toString());
        }
        catch (InvalidLayoutFileException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
}
