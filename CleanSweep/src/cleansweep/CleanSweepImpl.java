/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

import java.net.URISyntaxException;
import java.net.URL;
import simulator.Simulator;
import simulator.SimulatorFactory;
import simulator.InvalidLayoutFileException;
import memory.Memory;
import memory.MemoryImpl;
import navigation.Navigation;
import navigation.NavigationImpl;
import memory.UnexpectedChangeException;
import navigation.NoPathException;
import simulator.NoDirtException;
import utility.CarpetType;
import utility.Coords;
import utility.NullSensorException;
import utility.Direction;
import utility.InvalidCoordinatesException;
import utility.InvalidMoveException;
import utility.NoMoreDirectionsException;
import utility.Path;
/**
 *
 * @author James Doyle
 */
public class CleanSweepImpl implements CleanSweep {
    private  final int DIRT_CAPACITY = 50;
    private  final double CHARGE_CAPACITY = 100.0;
    private  Coords initialLocation;
    private  Coords currentLocation;
    private  Memory memory;
    private  Navigation nav;
    private  Simulator sim;
    private  double chargeRemaining;
    private  boolean doneCleaning;
    private  Path nearestCharger;
    private  Path currentPath;
    private  int carriedDirt;
    
    CleanSweepImpl() {
        initialLocation = new Coords(0,0);
        currentLocation = initialLocation;
        chargeRemaining = CHARGE_CAPACITY;
        doneCleaning = false;
        nearestCharger = null;
        currentPath = null;
        carriedDirt = 0;
        
    }
     
    //Return Start Coordinates
    @Override
    public Coords getInitialLocation() {
        return new Coords(initialLocation.x, initialLocation.y);
    }
    
    @Override
    public Coords getCurrentLocation() {
        return new Coords(currentLocation.x, currentLocation.y);
    }
    
    @Override
    public double getMaxChargeCapacity() {
        return CHARGE_CAPACITY;
    }
    
    private  void move(Direction d) throws InvalidMoveException, UnexpectedChangeException {
        double moveCost = 0.5 * carpetCost(sim.checkSurfaceAtLocation()) + 0.5 * carpetCost(sim.checkSurfaceAdjacent(d));
        if (moveCost > chargeRemaining)
            throw new InvalidMoveException("Not enough charge remaining to move from " + currentLocation.toString());
        switch (d) {
            case EAST:
                currentLocation.x = currentLocation.x + 1;
                break;
            case SOUTH:
                currentLocation.y = currentLocation.y - 1;
                break;
            case WEST:
                currentLocation.x = currentLocation.x - 1;
                break;
            case NORTH:
                currentLocation.y = currentLocation.y + 1;
                break;
        }
        sim.move(d);
        chargeRemaining -= moveCost;
        memory.observeCell(currentLocation);
    }



    public  void runSimulation() {
        // TODO code application logic here
 
        int stepsAllowed = 50; // ###
        
        try {
            URL fileUrl = SimulatorFactory.class.getResource("samplefloorplan.txt");
            String path = fileUrl.toURI().getPath();
            sim = SimulatorFactory.createSimulator(path, 0, 0);
            nav = new NavigationImpl();
            memory = new MemoryImpl(sim, nav);
            System.out.println(sim.toString());
            memory.observeCell(currentLocation);
            System.out.println(memory.toString());
            
            while(!doneCleaning && stepsAllowed > 0) {
                stepsAllowed--;
                currentPath = null;
                if (sim.hasChargingStation())
                    rechargeAndEmpty();
                nearestCharger = findNearestCharger();
                while(sim.isDirty() && chargeRemaining - carpetCost(sim.checkSurfaceAtLocation()) > nearestCharger.remainingCost())
                    tryToClean();
                currentPath = exploreCurrentCell();
                if (currentPath == null || currentPath.nextDirection() == null)
                    currentPath = findNextPath();
                if (carriedDirt >= DIRT_CAPACITY)
                    currentPath = nearestCharger;
                if (nearestCharger.remainingCost() + 2.0 * currentPath.nextCost() > chargeRemaining)
                    currentPath = nearestCharger;
                move(currentPath.moveAlong());
                System.out.println("-- " + stepsAllowed + " steps remaining! --");
                System.out.println("CleanSweep at " + currentLocation.toString());
                System.out.println("Charge: " + chargeRemaining + " Dirt: " + carriedDirt);
                System.out.println(memory.toString());
            }
            System.out.println(sim.toString());
        }
        catch (InvalidLayoutFileException | URISyntaxException | NullSensorException | UnexpectedChangeException | InvalidCoordinatesException | UnableToReturnToChargerException | InvalidMoveException | NoMoreDirectionsException e)
        {
            System.err.println(e.getMessage());
        }
    }
    
    public  void tryToClean() throws UnexpectedChangeException {
        CarpetType carpet = sim.checkSurfaceAtLocation();
        double cost = carpetCost(carpet);
        
        if (sim.isDirty() && chargeRemaining - cost >= nearestCharger.remainingCost() && carriedDirt + 1 <= DIRT_CAPACITY) {
            try {
                sim.removeDirt();
                chargeRemaining -= cost;
                carriedDirt++;
                memory.observeCell(currentLocation);
            }
            catch (NoDirtException e) {
                // log it here.
            }
        }
    }
    
    private  Path exploreCurrentCell() throws InvalidCoordinatesException {
        Direction exploreDirection = null;
        double movementCost = Double.MAX_VALUE;
        for(Direction d : Direction.values())
            if (memory.unexploredNeighbor(currentLocation, d)) {
                double cost;
                try {
                    cost = 0.5 * carpetCost(sim.checkSurfaceAtLocation()) + 0.5 * carpetCost(sim.checkSurfaceAdjacent(d));
                }
                catch (InvalidMoveException e) {
                    cost = Double.MAX_VALUE;
                }
                if (cost < movementCost) {
                    exploreDirection = d;
                    movementCost = cost;
                }
            }
        if (exploreDirection == null)
            return null;
        Path p = new Path();
        p.add(exploreDirection, movementCost);
        return p;
    }
    
    private  Path findNextPath() throws UnableToReturnToChargerException, InvalidCoordinatesException {
        Path pathToFollow = null;
        Path nearestDirt = null;
        Path nearestUnexplored = null;
        try {
            nearestDirt = nav.findNearestDirt(currentLocation);
        }
        catch (NoPathException e) { }
        try {
            nearestUnexplored = nav.findNearestUnexplored(currentLocation);
        }
        catch (NoPathException e) { }
        if (nearestDirt == null && nearestUnexplored == null)
            doneCleaning = true;
        else {
            if (nearestDirt == null)
                pathToFollow = nearestUnexplored;
            else if (nearestUnexplored == null)
                pathToFollow = nearestDirt;
            else {
                pathToFollow = nearestDirt;
                if (nearestUnexplored.remainingCost() < nearestDirt.remainingCost())
                    pathToFollow = nearestUnexplored;
            }
        }
        return pathToFollow;
    }
    
    private  Path findNearestCharger() throws UnableToReturnToChargerException, InvalidCoordinatesException {
        Path pathToCharger = null;
        try {
            pathToCharger = nav.findNearestCharger(currentLocation);
            if(pathToCharger.remainingCost() > chargeRemaining)
                throw new UnableToReturnToChargerException("Returning to nearest charger would cost more than the remaining charge.");
        }
        
        catch (NoPathException e) {
            throw new UnableToReturnToChargerException("No known path to charger: " + e.getMessage());
        }
        return pathToCharger;
    }
    
    private  double carpetCost(CarpetType c) {
        switch (c) {
            case BARE:
                return 1.0;
            case LOWPILE:
                return 2.0;
            case HIGHPILE:
                return 3.0;
        }
        return 0.0;
    }
    
    private  void rechargeAndEmpty() {
        chargeRemaining = CHARGE_CAPACITY;
        carriedDirt = 0;
    }
}