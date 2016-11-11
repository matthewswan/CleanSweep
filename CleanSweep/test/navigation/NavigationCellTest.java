package navigation;

import memory.ObservedCell;
import org.junit.Test;
import utility.CarpetType;
import utility.Coords;
import utility.ObstacleType;

import static org.junit.Assert.*;

/**
 * Created by Adam on 11/11/2016.
 */
public class NavigationCellTest {
    @Test
    public void getCost() throws Exception {
        NavigationCell bare = new NavigationCell(new ObservedCell(new Coords(0,0), CarpetType.BARE, true, false,
                ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE,ObstacleType.NONE));
        NavigationCell lowPile = new NavigationCell(new ObservedCell(new Coords(0,0), CarpetType.LOWPILE, true, false,
                ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE,ObstacleType.NONE));
        NavigationCell highPile = new NavigationCell(new ObservedCell(new Coords(0,0), CarpetType.HIGHPILE, true, false,
                ObstacleType.NONE, ObstacleType.NONE, ObstacleType.NONE,ObstacleType.NONE));

        assertEquals(1,bare.getCost());
        assertEquals(2,lowPile.getCost());
        assertEquals(3,highPile.getCost());
    }

}