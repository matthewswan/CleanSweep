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
        //Only ever need one instance
        return SurfaceManager;
    }

    //Create a log writer
    private SurfaceService()
    {
        //Initialize both a surface detector and apparatus changer objects
        surfaceDetector=new SurfaceDetectorImpl(CarpetType.BARE);
        apparatusChanger=new ApparatusChangerImpl(CarpetType.BARE);
    }

    public void changeFloor(CarpetType carpetType){
        //Set floor and apparatus
        surfaceDetector.setCarpetType(carpetType);
        apparatusChanger.setApparatusType(carpetType);
    }

    public CarpetType getCarpetType(){
        //Return carpet type in enum
        return surfaceDetector.getCarpetType();
    }

    public ApparatusType getApparatusType(){
        //Return apparatus type in enum
        return apparatusChanger.getApparatusType();
    }

    public String carpetTypeString(){
        //Return string representation of current surface
        return surfaceDetector.toString();
    }

    public String apparatusTypeString(){
        //Return string representation of current apparatus
        return apparatusChanger.toString();
    }
}
