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
public interface CleanSweep {
    public void runSimulation();
    public Coords getInitialLocation();
    public Coords getCurrentLocation();
    public double getMaxChargeCapacity();
    public double getMaxDirtCapacity();
}
