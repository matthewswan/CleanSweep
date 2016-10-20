/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import java.net.URISyntaxException;
import java.net.URL;
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
            URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
            String path = fileUrl.toURI().getPath();
            Simulator sim = SimulatorFactory.createSimulator(path, 0, 0);
            System.out.println(sim.toString());
        }
        catch (InvalidLayoutFileException | URISyntaxException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
}
