package surface;

import utility.CarpetType;

import static utility.CarpetType.LOWPILE;

/**
 * Created by Adam on 10/29/2016.
 */
public class SurfaceDetectorImpl implements SurfaceDetectable {
    //Need enum to set when changes occur
    CarpetType carpetType;

    //Default constructor
    //Can be used first and then set carpetType later
    public SurfaceDetectorImpl(){};

    //Normal constructor passing in carpet type
    public SurfaceDetectorImpl(CarpetType newCarpetType){
        carpetType = newCarpetType;
    }

    //CarpetType set property
    public void setCarpetType(CarpetType newCarpetType){
        carpetType = newCarpetType;
    }

    //CarpetType get property
    public CarpetType getCarpetType(){
        return carpetType;
    }

    //Returns String representation of CarpetType currently on
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
