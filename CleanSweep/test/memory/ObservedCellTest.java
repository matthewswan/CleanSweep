/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import org.junit.After;
import org.junit.AfterClass;
import utility.Direction;
import utility.ObstacleType;
import utility.CarpetType;
import utility.Coords;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class ObservedCellTest {
    
    static ObservedCell testCell;
    
    public ObservedCellTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testCell = new ObservedCell(new Coords(2,4), CarpetType.BARE, true, true, ObstacleType.NONE, ObstacleType.CLOSEDDOOR, ObstacleType.WALL, ObstacleType.OPENDOOR);
    }
    
    @After
    public void tearDown() {
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
    public void testHasDirt() {
        assertTrue(testCell.hasDirt());
    }

    @Test
    public void testClearDirt() {
        testCell.clearDirt();
        assertFalse(testCell.hasDirt());
    }

    @Test
    public void testGetObstacle() {
        assertEquals(ObstacleType.NONE, testCell.getObstacle(Direction.EAST));
        assertEquals(ObstacleType.CLOSEDDOOR, testCell.getObstacle(Direction.SOUTH));
        assertEquals(ObstacleType.WALL, testCell.getObstacle(Direction.WEST));
        assertEquals(ObstacleType.OPENDOOR, testCell.getObstacle(Direction.NORTH));
    }

    @Test
    public void testSetObstacle() {
        try {
            testCell.setObstacle(Direction.EAST, ObstacleType.CLOSEDDOOR);
            fail("Should not have been able to add a door to the cell.");
        }
        catch (UnexpectedChangeException e) {
            assertEquals(ObstacleType.NONE, testCell.getObstacle(Direction.EAST));
        }
        try {
            testCell.setObstacle(Direction.SOUTH, ObstacleType.OPENDOOR);
            testCell.setObstacle(Direction.NORTH, ObstacleType.CLOSEDDOOR);
            assertEquals(ObstacleType.OPENDOOR, testCell.getObstacle(Direction.SOUTH));
            assertEquals(ObstacleType.CLOSEDDOOR, testCell.getObstacle(Direction.NORTH));
        }
        catch (UnexpectedChangeException e) {
            fail("Should have been able to replace a closed door with an open door or vice versa.");
        }
        
    }

    @Test
    public void testHasCharger() {
        assertTrue(testCell.hasCharger());
    }

    @Test
    public void testToString() {
        assertEquals("o(   )o\n|      \n| BDC  \n|      \no(---)o\n", testCell.toString());
    }
    
}
