/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import navigation.NavigationImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import simulator.Simulator;
import simulator.SimulatorFactory;
import utility.Coords;
import utility.Direction;
import utility.InvalidMoveException;
import utility.Sensor;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class MemoryImplTest {
    
    Memory memory;
    Simulator sensor;
    
    public MemoryImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
        String path = fileUrl.toURI().getPath();
        sensor = SimulatorFactory.createSimulator(path, 0, 0);
        memory = new MemoryImpl(sensor, new NavigationImpl());
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testObserveCell() {
        try {
            memory.observeCell(new Coords(0,0));
            sensor.move(Direction.EAST);
            memory.observeCell(new Coords(1,0));
            sensor.move(Direction.EAST);
            memory.observeCell(new Coords(2,0));
            sensor.move(Direction.WEST);
            sensor.move(Direction.WEST);
            sensor.move(Direction.NORTH);
            memory.observeCell(new Coords(0,1));
            sensor.move(Direction.NORTH);
            memory.observeCell(new Coords(0,2));
        }
        catch (UnexpectedChangeException e) {
            fail("Should not have had an exception reading from the selected cells: " + e.getMessage());
        } catch (InvalidMoveException e) {
            fail("Should have been able to move according to test pattern: " + e.getMessage());
        }
        System.err.println(memory.toString());
        assertEquals("o     o              \n"
                   + "|                    \n"
                   + "| LD                 \n"
                   + "|                    \n"
                   + "o     o              \n"
                   + "o     o              \n"
                   + "|                    \n"
                   + "| LD                 \n"
                   + "|                    \n"
                   + "o     o              \n"
                   + "o     oo     oo     o\n"
                   + "|                    \n"
                   + "| L C    LD     LD   \n"
                   + "|                    \n"
                   + "o-----oo-----oo-----o\n", memory.toString());
        
    }

    @Test
    public void testToString() {
        testObserveCell();
    }
    
}
