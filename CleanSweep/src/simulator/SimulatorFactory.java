/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class SimulatorFactory {
    public static Simulator createSimulator(String layoutFile, int initialX, int initialY) throws InvalidLayoutFileException {
        SimulatorImpl simulator = new SimulatorImpl(layoutFile, new Coords(initialX, initialY));
        return simulator;
    }
}
