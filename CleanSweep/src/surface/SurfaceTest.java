package surface;

import utility.CarpetType;

/**
 * Created by Adam on 11/3/2016.
 */
public class SurfaceTest {
    public static void main(String[] args) throws Exception {
        SurfaceService surfaceService=SurfaceService.getInstance();

        surfaceService.changeFloor(CarpetType.BARE);
        System.out.println("Changing to a Bare Floor");
        System.out.println("Floor is: \r\n" + surfaceService.carpetTypeString());
        System.out.println("Using: \r\n"+surfaceService.apparatusTypeString());

        surfaceService.changeFloor(CarpetType.LOWPILE);
        System.out.println("Changing to a Low Pile Carpet Floor");
        System.out.println("Floor is: \r\n" + surfaceService.carpetTypeString());
        System.out.println("Using: \r\n"+surfaceService.apparatusTypeString());

        surfaceService.changeFloor(CarpetType.HIGHPILE);
        System.out.println("Changing to a High Pile Carpet Floor");
        System.out.println("Floor is: \r\n" + surfaceService.carpetTypeString());
        System.out.println("Using: \r\n"+surfaceService.apparatusTypeString());

    }
}
