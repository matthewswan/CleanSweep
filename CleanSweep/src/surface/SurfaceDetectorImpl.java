package surface;

import utility.CarpetType;

import static utility.CarpetType.LOWPILE;

/**
 * Created by Adam on 10/29/2016.
 */
public class SurfaceDetectorImpl implements SurfaceDetectable {
    CarpetType carpetType;

    public SurfaceDetectorImpl(){};

    public SurfaceDetectorImpl(CarpetType newCarpetType){
        carpetType = newCarpetType;
    }

    public void setCarpetType(CarpetType newCarpetType){
        carpetType = newCarpetType;
    }

    public CarpetType getCarpetType(){
        return carpetType;
    }

    public String toString(){
        String carpetTypeString;

        switch(carpetType) {
            case BARE:
                carpetTypeString="Bare Floor";
                break;
            case LOWPILE:
                carpetTypeString="Low Pile Carpet Floor";
                break;
            case HIGHPILE:
                carpetTypeString="High Pile Carpet Floor";
                break;
            default:
                carpetTypeString="Issue retrieving carpet type as a string";
                break;
        }

        return carpetTypeString;
    }

}
