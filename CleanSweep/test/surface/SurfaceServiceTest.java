package surface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utility.CarpetType;
import utility.ApparatusType;

import static org.junit.Assert.*;

/**
 * Created by Adam on 10/30/2016.
 */
public class SurfaceServiceTest {
    private static SurfaceService surfaceService;

    @Before
    public void setUp() throws Exception {
        surfaceService=SurfaceService.getInstance();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void changeFloor() throws Exception {
        surfaceService.changeFloor(CarpetType.BARE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.BARE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.BAREAPPARATUS);

        surfaceService.changeFloor(CarpetType.LOWPILE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.LOWPILE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.LOWPILEAPPARATUS);

        surfaceService.changeFloor(CarpetType.HIGHPILE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.HIGHPILE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.HIGHPILEAPPARATUS);
    }

    @Test
    public void getCarpetType() throws Exception {
        surfaceService.changeFloor(CarpetType.BARE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.BARE);

        surfaceService.changeFloor(CarpetType.LOWPILE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.LOWPILE);

        surfaceService.changeFloor(CarpetType.HIGHPILE);
        assertEquals(surfaceService.getCarpetType(),CarpetType.HIGHPILE);
    }

    @Test
    public void getApparatusType() throws Exception {
        surfaceService.changeFloor(CarpetType.LOWPILE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.LOWPILEAPPARATUS);

        surfaceService.changeFloor(CarpetType.BARE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.BAREAPPARATUS);

        surfaceService.changeFloor(CarpetType.HIGHPILE);
        assertEquals(surfaceService.getApparatusType(),ApparatusType.HIGHPILEAPPARATUS);
    }

    @Test
    public void floorTypeString() throws Exception {
        surfaceService.changeFloor(CarpetType.LOWPILE);
        assertEquals("Low Pile Carpet Floor",surfaceService.carpetTypeString());
        surfaceService.changeFloor(CarpetType.BARE);
        assertEquals("Bare Floor",surfaceService.carpetTypeString());
        surfaceService.changeFloor(CarpetType.HIGHPILE);
        assertEquals("High Pile Carpet Floor",surfaceService.carpetTypeString());
    }

    @Test
    public void apparatusTypeString() throws Exception {
        surfaceService.changeFloor(CarpetType.LOWPILE);
        assertEquals("Low Pile Carpet Floor Apparatus",surfaceService.apparatusTypeString());
        surfaceService.changeFloor(CarpetType.BARE);
        assertEquals("Bare Floor Apparatus",surfaceService.apparatusTypeString());
        surfaceService.changeFloor(CarpetType.HIGHPILE);
        assertEquals("High Pile Carpet Floor Apparatus",surfaceService.apparatusTypeString());
    }

}