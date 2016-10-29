package surface;


import utility.ApparatusType;
import utility.CarpetType;

/**
 * Created by Adam on 10/29/2016.
 */
public class SurfaceService {
    //Need singleton manager
    private volatile static SurfaceService SurfaceManager;
    //Surface and Carpet classes to change between carpets
    private static SurfaceDetectable surfaceDetector;
    private static ApparatusChangeable apparatusChanger;
    //One instance of log service
    public static SurfaceService getInstance() throws Exception
    {
        synchronized(SurfaceService.class)
        {
            if (SurfaceManager == null)
            {
                SurfaceManager = new SurfaceService();
            }
        }

        return SurfaceManager;
    }

    //Create a log writer
    private SurfaceService()
    {
        surfaceDetector=new SurfaceDetectorImpl(CarpetType.BARE);
        apparatusChanger=new ApparatusChangerImpl(CarpetType.BARE);
    }

    public void changeFloor(CarpetType carpetType){
        surfaceDetector.setCarpetType(carpetType);
        apparatusChanger.setApparatusType(carpetType);
    }

    public CarpetType getCarpetType(){
        return surfaceDetector.getCarpetType();
    }

    public ApparatusType getApparatusType(){
        return apparatusChanger.getApparatusType();
    }

    public String floorTypeString(){
        return surfaceDetector.toString();
    }

    public String apparatusTypeString(){
        return apparatusChanger.toString();
    }
}
