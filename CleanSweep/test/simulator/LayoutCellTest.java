/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class LayoutCellTest {
    
    static LayoutCell testCell;
    
    public LayoutCellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testCell = new LayoutCell(new Coords(2,4), CarpetType.BARE, 7, true, ObstacleType.NONE, ObstacleType.CLOSEDDOOR, ObstacleType.WALL, ObstacleType.OPENDOOR);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testToString() {
        assertEquals("o(   )o\n|      \n| B7C  \n|      \no(---)o\n",testCell.toString());
    }
    
    @Test
    public void testGetCoords() {
        assertEquals(2, testCell.getCoords().x);
        assertEquals(4, testCell.getCoords().y);
    }
    
    @Test
    public void testGetCarpet() {
        assertEquals(CarpetType.BARE, testCell.getCarpet());
    }
    
    @Test
    public void testGetDirtValue() {
        assertEquals(7, testCell.getDirtValue());
    }
    
    @Test
    public void testRemoveDirt() {
        try {
            testCell.removeDirt();
            assertEquals(6, testCell.getDirtValue());
            testCell.removeDirt();
            testCell.removeDirt();
            testCell.removeDirt();
            testCell.removeDirt();
            testCell.removeDirt();
            testCell.removeDirt();
            assertEquals(0, testCell.getDirtValue());
        }
        catch (NoDirtException e) {
            fail("Should have been able to remove dirt when >0 remaining.");
        }
        try {
            testCell.removeDirt();
            fail("Should not have been able to remove dirt when 0 remaining.");
        }
        catch (NoDirtException e) {
        }
    }
    
    @Test
    public void testGetObstacle() {
        assertEquals(ObstacleType.NONE, testCell.getObstacle(Direction.EAST));
        assertEquals(ObstacleType.CLOSEDDOOR, testCell.getObstacle(Direction.SOUTH));
        assertEquals(ObstacleType.WALL, testCell.getObstacle(Direction.WEST));
        assertEquals(ObstacleType.OPENDOOR, testCell.getObstacle(Direction.NORTH));
    }
    
    @Test
    public void testHasCharger() {
        assertTrue(testCell.hasCharger());
    }
}
