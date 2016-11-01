package cleansweep;

import cleansweep.CleanSweepImpl;
import org.junit.Test;
import java.awt.geom.Point2D;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

    public void testMoveUp() throws InvalidCoordinatesException {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(0,1);
        assertEquals(testCoords2, roomba.moveUp(testCoords));
    }

    @Test
    public void testMoveDown() throws InvalidCoordinatesException {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(0,-1);
        assertEquals(testCoords2, roomba.moveDown(testCoords));
    }

    @Test
    public void testMoveRight() throws InvalidCoordinatesException {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(1,0);
        assertEquals(testCoords2, roomba.moveRight(testCoords));
    }

    @Test
    public void testMoveLeft() throws InvalidCoordinatesException {
        Coords testCoords = new Coords(0,0);
        Coords testCoords2 = new Coords(-1,0);
        assertEquals(testCoords2, roomba.moveLeft(testCoords));
    }
    
    @Test
    public void testHasDirt() throws InvalidCoordinatesException {
        Cell testCell = new Cell(new Coords(1,1), 1);
        assertEquals(true, roomba.hasDirt(testCell));
        assertEquals(true, roomba.hasDirt(testCell));
    }
    
    @Test
    public void testDirtExpectation() throws InvalidCoordinatesException {
        Cell testCell = new Cell(new Coords(0,0), 0);
        assertEquals(true, roomba.hasDirt(testCell));
        assertEquals(false, roomba.hasDirt(testCell));
    }
    
    @Test
    public void testDirtRemoval() throws InvalidCoordinatesException, NoDirtException {
        Cell testCell = new Cell(new Coords(0,0), 1);
        roomba.removeDirt(testCell);
        assertEquals(0, testCell.getDirtValue());
        assertEquals(1, roomba.getDirtCount());
    }
}
