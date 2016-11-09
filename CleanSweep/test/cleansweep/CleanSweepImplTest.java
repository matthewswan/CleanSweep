/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import utility.Coords;

/**
 *
 * @author MatthewSwan
 */
public class CleanSweepImplTest {
    
    public CleanSweepImplTest(){}
    
    @Test
    public void testRememberInitialPosition() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        Coords testLocation = new Coords(0,0);
        Coords initialLocation = new Coords(1,3);
        initialLocation = cleanSweepImpl.getInitialLocation();
        assertEquals(testLocation,initialLocation);
    }

    @Test
    public void testRememberMaxChargeCapacity() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        double testDouble = 0.0;
        testDouble = cleanSweepImpl.getMaxChargeCapacity();
        assertEquals(100, testDouble, 0.0001);
    }

    @Test
    public void testRemmberMaxDirtCapacity() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        double testDouble = 0.0;
        testDouble = cleanSweepImpl.getMaxDirtCapacity();
        assertEquals(50, testDouble, 0.0001);
    }
}
