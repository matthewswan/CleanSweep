/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import cleansweep.CleanSweep;
import cleansweep.CleanSweepImpl;
import utility.Coords;

/**
 *
 * @author MatthewSwan
 */
public class CleanSweepFactory {
    public static CleanSweep createCleanSweep() {
        CleanSweepImpl cleanSweep = new CleanSweepImpl();
        return (CleanSweep) cleanSweep;
    }
}
