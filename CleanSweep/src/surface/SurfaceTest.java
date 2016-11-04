package surface;

import utility.CarpetType;

/**
 * Created by Adam on 11/3/2016.
 */
public class SurfaceTest {
    public static void main(String[] args) throws Exception {
        SurfaceService surfaceService=SurfaceService.getInstance();

        surfaceService.changeFloor(CarpetType.BARE);
        System.out.println("Changing to Bare Floor");
        System.out.println("Floor is: " + surfaceService.carpetTypeString());
        System.out.println("Using "+surfaceService.apparatusTypeString());

        surfaceService.changeFloor(CarpetType.LOWPILE);
        System.out.println("Changing to Bare Floor");
        System.out.println("Floor is: " + surfaceService.carpetTypeString());
        System.out.println("Using "+surfaceService.apparatusTypeString());

        surfaceService.changeFloor(CarpetType.HIGHPILE);
        System.out.println("Changing to Bare Floor");
        System.out.println("Floor is: " + surfaceService.carpetTypeString());
        System.out.println("Using "+surfaceService.apparatusTypeString());

    }
}
