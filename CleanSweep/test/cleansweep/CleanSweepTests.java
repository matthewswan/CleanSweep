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
        int testInt = 0;
        testInt = cleanSweepImpl.getMaxCapacity();
        assertEquals(50, testInt);
    }
    
    @Test
    
    public void testMoveUp() {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(0,1);
        assertEquals(testCoords2, roomba.moveUp(testCoords));
    }
    
    @Test
    public void testMoveDown() {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(0,-1);
        assertEquals(testCoords2, roomba.moveDown(testCoords));
    }
    
    @Test
    public void testMoveRight() {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(1,0);
        assertEquals(testCoords2, roomba.moveRight(testCoords));
    }
    
    @Test
    public void testMoveLeft() {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(-1,0);
        assertEquals(testCoords2, roomba.moveLeft(testCoords));
    }
}