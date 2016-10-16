/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.net.URISyntaxException;
import java.net.URL;
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
public class SimulatorImplTest {
    Simulator sim;
    
    public SimulatorImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try {
            URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
            String path = fileUrl.toURI().getPath();
            sim = SimulatorFactory.createSimulator(path, 0, 0);
        }
        catch (InvalidLayoutFileException | URISyntaxException e) {
            fail("Exception setting up the simulator: " + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testMove() throws Exception {
        try {
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.EAST);
            sim.move(Direction.SOUTH);
            sim.move(Direction.SOUTH);
            sim.move(Direction.SOUTH);
            sim.move(Direction.EAST);
            sim.move(Direction.WEST);
        }
        catch(InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        try {
            sim.move(Direction.SOUTH);
            fail("Moved through stairs obstacle at 4,0 SOUTH.");
        }
        catch (InvalidMoveException e) {
            assertEquals("Tried to cross obstacle: STAIRSDOWN", e.getMessage());
        }
        try {
            sim.move(Direction.WEST);
            fail("Moved through wall obstacle at 4,0 WEST.");
        }
        catch (InvalidMoveException e) {
            assertEquals("Tried to cross obstacle: WALL", e.getMessage());
        }
    }

    @Test
    public void testIsDirty() {
        assertFalse(sim.isDirty());
        try {
            sim.move(Direction.NORTH);
        }
        catch (InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        assertTrue(sim.isDirty());
    }

    @Test
    public void testRemoveDirt() throws Exception {
        try {
            sim.removeDirt();
            fail("Was able to remove dirt that shouldn't exist at 0,0.");
        }
        catch (NoDirtException e) {
            assertEquals("Tried to remove dirt from cell (0, 0) but it had no dirt.", e.getMessage());
        }
        try {
            sim.move(Direction.NORTH);
            sim.removeDirt();
            sim.removeDirt();
            sim.removeDirt();
        }
        catch (InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        catch (NoDirtException e) {
            fail("Couldn't remove dirt from 0, 1 three times.");
        }
        try {
            sim.removeDirt();
            fail("Was able to remove dirt four times from 0, 1.");
        }
        catch (NoDirtException e) {
            assertEquals("Tried to remove dirt from cell (0, 1) but it had no dirt.", e.getMessage());
        }
                
    }

    @Test
    public void testCheckSurfaceAtLocation() {
        assertEquals(CarpetType.LOWPILE, sim.checkSurfaceAtLocation());
        try {
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.EAST);
        }
        catch(InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        assertEquals(CarpetType.BARE, sim.checkSurfaceAtLocation());
        try {
            sim.move(Direction.EAST);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.SOUTH);
            sim.move(Direction.SOUTH);
        }
        catch(InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        assertEquals(CarpetType.HIGHPILE, sim.checkSurfaceAtLocation());
    }

    @Test
    public void testHasChargingStation() {
        assertTrue(sim.hasChargingStation());
        try {
            sim.move(Direction.EAST);
        }
        catch(InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        assertFalse(sim.hasChargingStation());
    }

    @Test
    public void testGetObstacle() {
        assertEquals(ObstacleType.NONE, sim.getObstacle(Direction.EAST));
        assertEquals(ObstacleType.WALL, sim.getObstacle(Direction.SOUTH));
        assertEquals(ObstacleType.WALL, sim.getObstacle(Direction.WEST));
        assertEquals(ObstacleType.NONE, sim.getObstacle(Direction.NORTH));
            try {
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.EAST);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.EAST);
            sim.move(Direction.SOUTH);
            sim.move(Direction.SOUTH);
            sim.move(Direction.SOUTH);
            sim.move(Direction.EAST);
            sim.move(Direction.WEST);
        }
        catch(InvalidMoveException e) {
            fail("Couldn't follow valid move sequence: " + e.getMessage());
        }
        assertEquals(ObstacleType.NONE, sim.getObstacle(Direction.EAST));
        assertEquals(ObstacleType.STAIRSDOWN, sim.getObstacle(Direction.SOUTH));
        assertEquals(ObstacleType.WALL, sim.getObstacle(Direction.WEST));
        assertEquals(ObstacleType.NONE, sim.getObstacle(Direction.NORTH));
    }

    @Test
    public void testCheckSurfaceAdjacent() {
        try {
            assertEquals(CarpetType.LOWPILE, sim.checkSurfaceAdjacent(Direction.EAST));
            assertEquals(CarpetType.LOWPILE, sim.checkSurfaceAdjacent(Direction.NORTH));
        }
        catch (InvalidMoveException e) {
            fail("Couldn't see adjacent surface despite lack of obstacle.");
        }
        try {
            sim.checkSurfaceAdjacent(Direction.SOUTH);
            fail("No exception when checking surface through south wall at 0, 0.");
        }
        catch (InvalidMoveException e) {
            assertEquals("Tried to look through obstacle: WALL", e.getMessage());
        }
        try {
            sim.checkSurfaceAdjacent(Direction.WEST);
            fail("No exception when checking surface through west wall at 0, 0.");
        }
        catch (InvalidMoveException e) {
            assertEquals("Tried to look through obstacle: WALL", e.getMessage());
        }
        try {
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.NORTH);
            sim.move(Direction.EAST);
        }
        catch (InvalidMoveException e) {
            fail("Unable to follow valid move sequence.");
        }
        try {
            assertEquals(CarpetType.BARE, sim.checkSurfaceAdjacent(Direction.NORTH));
        }
        catch (InvalidMoveException e) {
            fail("Unable to see through open door at 1,4 NORTH");
        }
    }

    @Test
    public void testFindChargingStations() {
    }

    @Test
    public void testToString() {
        assertEquals("CleanSweep at location (0, 0).\n"
                   + "o-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----o\n"
                   + "|                          ||                          ||            |\n"
                   + "| L1     L1     L1     L1  || B3     B2     B3     B2  || B1     B2  |\n"
                   + "|                          ||                          ||            |\n"
                   + "o     oo     oo     oo     oo-----oo-----oo(   )oo(   )oo     oo     o\n"
                   + "o     oo     oo     oo     oo-----oo-----oo(   )oo(   )oo     oo     o\n"
                   + "|                          ||            ||            ^^            |\n"
                   + "| L1     L1     L1     L1  || B4     B3  || B2     B1     B1     B2  |\n"
                   + "|                          ||            ||            vv            |\n"
                   + "o     oo     oo     oo     oo(   )oo(   )oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo(   )oo(   )oo     oo     oo     oo     o\n"
                   + "|                          ^^            ^^            ||            |\n"
                   + "| L1     L1     L1     L1     B1     B2     B2     B1  || B2     B2  |\n"
                   + "|                          vv            vv            ||            |\n"
                   + "o(   )oo(   )oo-----oo-----oo     oo     oo     oo     oo-----oo-----o\n"
                   + "o(   )oo(   )oo-----oo-----oo     oo     oo     oo     oo-----oo-----o\n"
                   + "|            ||            ^^            ||                          |\n"
                   + "| B0     B1  || B1     B1     B1     B1  || B1     B2     B2     B3  |\n"
                   + "|            ||            vv            ||                          |\n"
                   + "o-----oo-----oo-----oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "o-----oo-----oo-----oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| B1     B1     B1     B1  || B2     B1  || B2     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o-----oo(   )oo(   )oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "o-----oo(   )oo(   )oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L2     L2     L2     L2  || B1     B1  || B1     H3     H3     B2  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ^^            ||                          |\n"
                   + "| L1     L2     L2     L2     B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          vv            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L1     L2     L2     L2  || B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L3     L2     L2     L2  || B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L0C    L3     L3     L3  || B0     B1  || B1     B2     B2     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o-----oo-----oo-----oo-----oovvvvvoo-----oo-----oo-----oo-----oo-----o\n", sim.toString());
        try {
            sim.move(Direction.NORTH);
            sim.removeDirt();
        }
        catch (InvalidMoveException e) {
            fail("Unable to follow valid move sequence.");
        }
        catch (NoDirtException e) {
            fail("No dirt at location 0,1.");
        }
        assertEquals("CleanSweep at location (0, 1).\n"
                   + "o-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----o\n"
                   + "|                          ||                          ||            |\n"
                   + "| L1     L1     L1     L1  || B3     B2     B3     B2  || B1     B2  |\n"
                   + "|                          ||                          ||            |\n"
                   + "o     oo     oo     oo     oo-----oo-----oo(   )oo(   )oo     oo     o\n"
                   + "o     oo     oo     oo     oo-----oo-----oo(   )oo(   )oo     oo     o\n"
                   + "|                          ||            ||            ^^            |\n"
                   + "| L1     L1     L1     L1  || B4     B3  || B2     B1     B1     B2  |\n"
                   + "|                          ||            ||            vv            |\n"
                   + "o     oo     oo     oo     oo(   )oo(   )oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo(   )oo(   )oo     oo     oo     oo     o\n"
                   + "|                          ^^            ^^            ||            |\n"
                   + "| L1     L1     L1     L1     B1     B2     B2     B1  || B2     B2  |\n"
                   + "|                          vv            vv            ||            |\n"
                   + "o(   )oo(   )oo-----oo-----oo     oo     oo     oo     oo-----oo-----o\n"
                   + "o(   )oo(   )oo-----oo-----oo     oo     oo     oo     oo-----oo-----o\n"
                   + "|            ||            ^^            ||                          |\n"
                   + "| B0     B1  || B1     B1     B1     B1  || B1     B2     B2     B3  |\n"
                   + "|            ||            vv            ||                          |\n"
                   + "o-----oo-----oo-----oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "o-----oo-----oo-----oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| B1     B1     B1     B1  || B2     B1  || B2     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o-----oo(   )oo(   )oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "o-----oo(   )oo(   )oo-----oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L2     L2     L2     L2  || B1     B1  || B1     H3     H3     B2  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ^^            ||                          |\n"
                   + "| L1     L2     L2     L2     B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          vv            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L1     L2     L2     L2  || B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L2     L2     L2     L2  || B1     B1  || B1     H3     H3     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "o     oo     oo     oo     oo     oo     oo     oo     oo     oo     o\n"
                   + "|                          ||            ||                          |\n"
                   + "| L0C    L3     L3     L3  || B0     B1  || B1     B2     B2     B1  |\n"
                   + "|                          ||            ||                          |\n"
                   + "o-----oo-----oo-----oo-----oovvvvvoo-----oo-----oo-----oo-----oo-----o\n", sim.toString());
    }
    
}
