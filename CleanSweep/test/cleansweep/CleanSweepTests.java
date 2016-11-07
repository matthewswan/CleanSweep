package cleansweep;

import cleansweep.CleanSweepImpl;
import org.junit.Test;
import java.awt.geom.Point2D;
import static org.junit.Assert.assertEquals;
import utility.Coords;

/**
 * Created by alexluka
 */
public class CleanSweepTests {

    CleanSweepImpl roomba = new CleanSweepImpl();

    @Test
    public void testRememberStartPosition() {
        Coords testLocation = new Coords(0,0);
        Coords initialLocation = new Coords(1,3);
        initialLocation = roomba.getInitialLocation();
        assertEquals(testLocation,initialLocation);
    }

    @Test
    public void testRememberMaxDirtCapacity() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        double testDouble = 0.0;
        testDouble = cleanSweepImpl.getMaxChargeCapacity();
        assertEquals(50, testDouble, 0.0001);
    }

}
