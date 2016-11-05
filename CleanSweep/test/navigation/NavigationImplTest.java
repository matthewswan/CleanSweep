/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import memory.ObservedCell;
import utility.Coords;
import utility.CarpetType;
import utility.ObstacleType;
import utility.Direction;
import utility.Path;
import utility.NoMoreDirectionsException;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class NavigationImplTest {
    
    static NavigationImpl nav;
    
    public NavigationImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        nav = new NavigationImpl();
        try {
            // We will set up a layout containing the bottom two rows of the sample layout.
            // However, doors have been added to (3,1)<->(4,1) and (5,0)<->(6,0), and a wall
            // has been added above row 1.  (9,1) has an unexplored north direction.
            nav.addCell(new ObservedCell(new Coords(0,0), CarpetType.LOWPILE, false, true, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.WALL, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(1,0), CarpetType.LOWPILE, false, false, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(2,0), CarpetType.LOWPILE, false, false, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(3,0), CarpetType.LOWPILE, false, false, ObstacleType.WALL, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(4,0), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.STAIRSDOWN, ObstacleType.WALL, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(5,0), CarpetType.BARE, false, false, ObstacleType.OPENDOOR, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(6,0), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.OPENDOOR, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(7,0), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(8,0), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(9,0), CarpetType.BARE, false, false, ObstacleType.WALL, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE));
            nav.addCell(new ObservedCell(new Coords(0,1), CarpetType.LOWPILE, false, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(1,1), CarpetType.LOWPILE, false, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(2,1), CarpetType.LOWPILE, false, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(3,1), CarpetType.LOWPILE, false, false, ObstacleType.OPENDOOR, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(4,1), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.OPENDOOR, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(5,1), CarpetType.BARE, false, false, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(6,1), CarpetType.BARE, false, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(7,1), CarpetType.HIGHPILE, true, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(8,1), CarpetType.HIGHPILE, true, false, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.WALL, ObstacleType.WALL));
            nav.addCell(new ObservedCell(new Coords(9,1), CarpetType.BARE, false, true, ObstacleType.WALL, ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE));
        }
        catch (DuplicateCellException e) {
            fail("Exception during setup.");
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testAddCell() throws Exception {
    }

    @Test
    public void testFindPath() throws Exception {
        Path path = nav.findPath(new Coords(0,0), new Coords(2,0));
        assertEquals(4.0, path.remainingCost(), 0.0001);
        try {
            assertEquals(Direction.EAST, path.moveAlong());
            assertEquals(Direction.EAST, path.moveAlong());
        }
        catch (NoMoreDirectionsException e) {
            fail("Ran out of directions early in search for two-step east.");
        }
        try {
            path.moveAlong();
            fail("Did not get exception when overrunning path.");
        }
        catch (NoMoreDirectionsException e) {
        }
        path = nav.findPath(new Coords(0,0), new Coords(9,1));
        assertEquals(16.5, path.remainingCost(), 0.0001);
        try {
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // Should be crossing first door into hallway
            assertEquals(Direction.EAST, path.nextDirection());
            assertEquals(1.5, path.nextCost(), 0.0001);
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // Should be crossing next door into master bedroom
            assertEquals(Direction.EAST, path.nextDirection());
            assertEquals(1.0, path.nextCost(), 0.0001);
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // One move north left to go, avoiding the highpile carpet
            assertEquals(Direction.NORTH, path.nextDirection());
            assertEquals(1.0, path.nextCost(), 0.0001);
            path.moveAlong();
            assertEquals(0.0, path.remainingCost(), 0.0001);   
        }
        catch (NoMoreDirectionsException e) {
            fail("Ran out of directions early in path to bedroom.");
        }
        try {
            path.moveAlong();
            fail("Did not get exception when overrunning path to bedroom.");
        }
        catch (NoMoreDirectionsException e) {
            
        }
    }

    @Test
    public void testFindNearestCharger() throws Exception {
        Path path;
        try {
            path = nav.findNearestCharger(new Coords(2,0));
            assertEquals(Direction.WEST, path.moveAlong());
            assertEquals(Direction.WEST, path.moveAlong());
            assertEquals(0.0, path.remainingCost(), 0.0001);
            try {
                path.moveAlong();
                fail("Path should not have had additional moves from 0,0 charger.");
            }
            catch (NoMoreDirectionsException e) {
                
            }
        }
        catch (NoPathException e) {
            fail("Could not find a path from 2,0 to a charger.");
        }
        catch (NoMoreDirectionsException e) {
            fail("Should have received a path with two WEST directions.");
        }
        try {
            path = nav.findNearestCharger(new Coords(4,1));
            assertEquals(7.0, path.remainingCost(), 0.0001);
            path.moveAlong();
            path.moveAlong();
            assertEquals(Direction.EAST, path.moveAlong());
            assertEquals(Direction.EAST, path.moveAlong());
            assertEquals(Direction.EAST, path.moveAlong());
            assertEquals(Direction.EAST, path.moveAlong());
            assertEquals(Direction.NORTH, path.moveAlong());
            assertEquals(0.0, path.remainingCost(), 0.0001);
            try {
                path.moveAlong();
                fail("Path should not have had additional moves from 9,1 charger.");
            }
            catch (NoMoreDirectionsException e) {
            }
        }
        catch (NoPathException e) {
            fail("Could not find a path from 4,1 to a charger.");
        }
        catch (NoMoreDirectionsException e) {
            fail("Should have received a path to 9,1 charger.");
        }
    }

    @Test
    public void testFindNearestDirt() throws Exception {
        Path path;
        try {
            path = nav.findNearestDirt(new Coords(5,0));
            assertEquals(Direction.EAST, path.moveAlong());
            path.moveAlong();
            path.moveAlong();
            assertEquals(0.0, path.remainingCost(), 0.0001);
            try {
                path.moveAlong();
                fail("Path should not have had additional moves from 7,1 dirt.");
            }
            catch (NoMoreDirectionsException e) {
                
            }
        }
        catch (NoPathException e) {
            fail("Could not find a path from 5,0 to a dirty cell.");
        }
        catch (NoMoreDirectionsException e) {
            fail("Should have received a path with two EAST directions and then a NORTH.");
        }
        try {
            path = nav.findNearestDirt(new Coords(9,0));
            assertEquals(3.0, path.remainingCost(), 0.0001);
            assertEquals(Direction.WEST, path.moveAlong());
            assertEquals(Direction.NORTH, path.moveAlong());
            assertEquals(0.0, path.remainingCost(), 0.0001);
            try {
                path.moveAlong();
                fail("Path should not have had additional moves from 8,1 dirt.");
            }
            catch (NoMoreDirectionsException e) {
            }
        }
        catch (NoPathException e) {
            fail("Could not find a path from 9,0 to a dirty cell.");
        }
        catch (NoMoreDirectionsException e) {
            fail("Should have received a path to 8,1 dirt.");
        }
    }

    @Test
    public void testFindNearestUnexplored() throws Exception {
        Path path = nav.findNearestUnexplored(new Coords(0,0));
        assertEquals(16.5, path.remainingCost(), 0.0001);
        try {
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // Should be crossing first door into hallway
            assertEquals(Direction.EAST, path.nextDirection());
            assertEquals(1.5, path.nextCost(), 0.0001);
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // Should be crossing next door into master bedroom
            assertEquals(Direction.EAST, path.nextDirection());
            assertEquals(1.0, path.nextCost(), 0.0001);
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            path.moveAlong();
            // One move north left to go, avoiding the highpile carpet
            assertEquals(Direction.NORTH, path.nextDirection());
            assertEquals(1.0, path.nextCost(), 0.0001);
            path.moveAlong();
            assertEquals(0.0, path.remainingCost(), 0.0001);   
        }
        catch (NoMoreDirectionsException e) {
            fail("Ran out of directions early in path to bedroom.");
        }
        try {
            path.moveAlong();
            fail("Did not get exception when overrunning path to bedroom.");
        }
        catch (NoMoreDirectionsException e) {
            
        }
    }
    
}
