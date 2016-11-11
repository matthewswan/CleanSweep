/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import logger.LogService;
import memory.Memory;
import memory.MemoryImpl;
import navigation.NavigationImpl;
import org.junit.Test;
import simulator.NoDirtException;
import simulator.Simulator;
import simulator.SimulatorFactory;
import utility.*;

import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author MatthewSwan
 */
public class CleanSweepImplTest {
    
    public CleanSweepImplTest(){}
    
    @Test
    public void testRememberInitialPosition() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        Coords testLocation = new Coords(0,0);
        Coords initialLocation = new Coords(1,3);
        initialLocation = cleanSweepImpl.getInitialLocation();
        assertEquals(testLocation,initialLocation);
    }

    @Test
    public void testRememberMaxChargeCapacity() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        double testDouble = 0.0;
        testDouble = cleanSweepImpl.getMaxChargeCapacity();
        assertEquals(100, testDouble, 0.0001);
    }

    @Test
    public void testRememberMaxDirtCapacity() {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        double testDouble = 0.0;
        testDouble = cleanSweepImpl.getMaxDirtCapacity();
        assertEquals(50, testDouble, 0.0001);
    }

    @Test
    public void testCurrentPosition(){
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        Coords currentCoords = cleanSweepImpl.getCurrentLocation();
        Coords coordsToTest = cleanSweepImpl.getInitialLocation();

        assertEquals(currentCoords, coordsToTest);
    }

    @Test
    public void testAllReachableHaveBeenCleaned(){
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        cleanSweepImpl.runSimulation();
        Memory memory = cleanSweepImpl.getMemory();
        Simulator sim = cleanSweepImpl.getSimulator();

        assertTrue(!sim.isDirty());

        String simString = sim.toString().replace("L0","L ").replace("B0","B ").replace("H0","H ");

        assertEquals(simString,"CleanSweep at location (9, 1).\n"+memory.toString());

        assertTrue(!memory.toString().contains("D"));
    }

    @Test
    public void testPowerConsumptionCheckAndDirtCapacity(){
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();

        assertEquals(cleanSweepImpl.getMaxChargeCapacity(),cleanSweepImpl.getChargeRemaining(),0.0);
        assertEquals(0.0,cleanSweepImpl.getCarriedDirt(),0);

        cleanSweepImpl.runSimulation();

        assertEquals(50.5,cleanSweepImpl.getChargeRemaining(),0.0);
        assertEquals(5,cleanSweepImpl.getCarriedDirt(),0);
    }

    @Test
    public void testIfRedirectsOnObstacle() throws Exception {
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
        String path = fileUrl.toURI().getPath();
        Simulator simulator = SimulatorFactory.createSimulator(path, 0, 0);
        MemoryImpl memory =new MemoryImpl(simulator, new NavigationImpl());
        Coords coord = new Coords(0,0);
        memory.observeCell(coord);
        Path testReturnPath = cleanSweepImpl.exploreCurrentCellTest(coord, memory, simulator);

        assertTrue(testReturnPath.nextDirection() == Direction.EAST);
        assertTrue(testReturnPath.nextDirection() != Direction.SOUTH);
        assertEquals("WALL",simulator.getObstacle(Direction.SOUTH).toString());

        coord = new Coords(0,5);
        memory.observeCell(coord);
        simulator = SimulatorFactory.createSimulator(path, 0, 5);
        testReturnPath = cleanSweepImpl.exploreCurrentCellTest(coord, memory, simulator);

        assertTrue(testReturnPath.nextDirection() == Direction.EAST);
        assertTrue(testReturnPath.nextDirection() != Direction.SOUTH);
        assertEquals("WALL",simulator.getObstacle(Direction.SOUTH).toString());

        coord = new Coords(9,5);
        memory.observeCell(coord);
        simulator = SimulatorFactory.createSimulator(path, 9, 5);
        testReturnPath = cleanSweepImpl.exploreCurrentCellTest(coord, memory, simulator);


        assertTrue(testReturnPath.nextDirection() == Direction.NORTH);
        assertTrue(testReturnPath.nextDirection() != Direction.EAST);
        assertEquals("WALL",simulator.getObstacle(Direction.EAST).toString());
    }

    @Test
    public void testCleaning() throws Exception{
        CleanSweepImpl cleanSweepImpl = new CleanSweepImpl();
        URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
        String path = fileUrl.toURI().getPath();
        Simulator simulator = SimulatorFactory.createSimulator(path, 8, 2);
        MemoryImpl memory =new MemoryImpl(simulator, new NavigationImpl());
        Coords coord = new Coords(8,2);
        memory.observeCell(coord);

        assertTrue(simulator.isDirty());

        while(simulator.isDirty()){
            cleanSweepImpl.tryToCleanTest(simulator,memory,coord);
        }

        assertTrue(!simulator.isDirty());
    }

    @Test
    public void testChargingTillFull() throws Exception{
        CleanSweepImpl cleanSweep = new CleanSweepImpl();
        cleanSweep.runSimulation();

        assertTrue(cleanSweep.getMaxChargeCapacity()>cleanSweep.getChargeRemaining());

        Path chargePath = cleanSweep.getNearestCharger();

        while(chargePath.getCosts().size()!=0) {
            cleanSweep.move(chargePath.moveAlong());
        }
        cleanSweep.rechargeAndEmpty();

        assertEquals(cleanSweep.getMaxChargeCapacity(),cleanSweep.getChargeRemaining(),0.0);

        assertEquals(cleanSweep.getNearestChargerCoords(), cleanSweep.getSimulator().getCurrentLocation());
    }

}
