package cleansweep;

import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.assertEquals;

/**
 * Created by alexluka
 */
public class CleanSweepTests {

    CleanSweep roomba = new CleanSweep();

    @Test
    public void testRememberStartPosition() {
        Point2D.Double testCoords = new Point2D.Double(0,0);
        Point2D.Double testCoords2 = new Point2D.Double(0,1);
        Point2D.Double startCoords = roomba.getStartCoords();
        assertEquals(testCoords,startCoords);
    }

    @Test
    public void testMoveUp() {
        Point2D.Double testCoords = new Point2D.Double(0,0);
        Point2D.Double testCoords2 = new Point2D.Double(0,1);
        assertEquals(testCoords2,roomba.moveUp(testCoords));
    }

    @Test
    public void testMoveDown() {
        Point2D.Double testCoords = new Point2D.Double(0,0);
        Point2D.Double testCoords2 = new Point2D.Double(0,-1);
        assertEquals(testCoords2,roomba.moveDown(testCoords));
    }

    @Test
    public void testMoveRight() {
        Point2D.Double testCoords = new Point2D.Double(0,0);
        Point2D.Double testCoords2 = new Point2D.Double(1,0);
        assertEquals(testCoords2,roomba.moveRight(testCoords));
    }

    @Test
    public void testMoveLeft() {
        Point2D.Double testCoords = new Point2D.Double(0,0);
        Point2D.Double testCoords2 = new Point2D.Double(-1,0);
        assertEquals(testCoords2,roomba.moveLeft(testCoords));
    }


}