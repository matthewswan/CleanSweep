/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

import java.net.URL;
import java.util.Map;
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
public class LayoutImplTest {
    Layout layout;
    
    public LayoutImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        URL fileUrl = getClass().getResource("samplefloorplan.txt");
        String path = fileUrl.toURI().getPath();
        try {
            layout = new LayoutImpl(path);
        }
        catch (InvalidLayoutFileException e) {
            fail("Unable to load layout file: " + e.getMessage());
        }
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetCarpet()  {
        try {
            assertEquals(CarpetType.BARE, layout.getCarpet(new Coords(4,2)));
            assertEquals(CarpetType.LOWPILE, layout.getCarpet(new Coords(3,4)));
            assertEquals(CarpetType.HIGHPILE, layout.getCarpet(new Coords(7,1)));
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        try {
            layout.getCarpet(new Coords(10,2));
            fail("Should have received an exception - bad coordinates (10,2).");
        }
        catch (InvalidCoordinatesException e) {
        }
    }

    @Test
    public void testHasDirt() {
        try {
            assertTrue(layout.hasDirt(new Coords(0,3)));
            assertFalse(layout.hasDirt(new Coords(0,6)));
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        try {
            layout.hasDirt(new Coords(10,2));
            fail("Should have received an exception - bad coordinates (10,2).");
        }
        catch (InvalidCoordinatesException e) {
        }
    }

    @Test
    public void testRemoveDirt() {
        try {
            layout.removeDirt(new Coords(7,5));
            layout.removeDirt(new Coords(7,5));
            layout.removeDirt(new Coords(7,5));
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        catch (NoDirtException e) {
            fail("Should have still had dirt at 7,5: " + e.getMessage());
        }
        try {
            layout.removeDirt(new Coords(7,5));
            fail("Shoud not have been able to remove dirt at 7,5");
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        catch (NoDirtException e) {
        }
        try {
            layout.removeDirt(new Coords(10,2));
            fail("Should have received an exception - bad coordinates (10,2).");
        }
        catch (InvalidCoordinatesException e) {
        }
        catch (NoDirtException e) {
            fail("Should not have made it to a NoDirtException with bad coordinates." + e.getMessage());
        }
    }

    @Test
    public void testGetBorders() {
        try {
            Map<Direction, ObstacleType> cellBorders = layout.getBorders(new Coords(4,0));
            assertEquals(ObstacleType.NONE, cellBorders.get(Direction.EAST));
            assertEquals(ObstacleType.STAIRSDOWN, cellBorders.get(Direction.SOUTH));
            assertEquals(ObstacleType.WALL, cellBorders.get(Direction.WEST));
            assertEquals(ObstacleType.NONE, cellBorders.get(Direction.NORTH));
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        try {
            layout.getBorders(new Coords(10,2));
            fail("Should have received an exception - bad coordinates (10,2).");
        }
        catch (InvalidCoordinatesException e) {
        }
    }

    @Test
    public void testHasCharger() throws Exception {
        try {
            assertTrue(layout.hasCharger(new Coords(0,0)));
            assertFalse(layout.hasCharger(new Coords(0,1)));
        }
        catch (InvalidCoordinatesException e) {
            fail("Should have been able to find cell at valid coords: " + e.getMessage());
        }
        try {
            layout.hasCharger(new Coords(10,2));
            fail("Should have received an exception - bad coordinates (10,2).");
        }
        catch (InvalidCoordinatesException e) {
        }
    }

    @Test
    public void testToString() {
        assertEquals("o-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----oo-----o\n"
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
                   + "o-----oo-----oo-----oo-----oovvvvvoo-----oo-----oo-----oo-----oo-----o\n", layout.toString());
    }
    
}
