package surface;

import utility.ApparatusType;
import utility.CarpetType;

import static utility.ApparatusType.BAREAPPARATUS;
import static utility.ApparatusType.LOWPILEAPPARATUS;
import static utility.ApparatusType.HIGHPILEAPPARATUS;

/**
 * Created by Adam on 10/29/2016.
 */
public class ApparatusChangerImpl implements ApparatusChangeable {
    ApparatusType apparatusType;

    //Default constructor can be called and paired with setApparatusType to initialize
    public ApparatusChangerImpl(){}

    //Constructor taking in carpettype to find apparatustype that is appropriate
    public ApparatusChangerImpl(CarpetType carpetType){
        switch(carpetType) {
            case LOWPILE:
                apparatusType = LOWPILEAPPARATUS;
                break;
            case HIGHPILE:
                apparatusType = HIGHPILEAPPARATUS;
                break;
            default:
                apparatusType = BAREAPPARATUS;
                break;
        }
    }

    //Apparatus set property
    public void setApparatusType(CarpetType carpetType){
        switch(carpetType) {
            case LOWPILE:
                apparatusType = LOWPILEAPPARATUS;
                break;
            case HIGHPILE:
                apparatusType = HIGHPILEAPPARATUS;
                break;
            default:
                apparatusType = BAREAPPARATUS;
                break;
        }
    }

    //Apparatus get property
    public ApparatusType getApparatusType(){
        return apparatusType;
    }

    //Returns string representation of apparatus type
    public String toString(){
        String apparatusTypeString;

        switch(apparatusType) {
            case LOWPILEAPPARATUS:
                apparatusTypeString="Low Pile Carpet Floor Apparatus";
                break;
            case HIGHPILEAPPARATUS:
                apparatusTypeString="High Pile Carpet Floor Apparatus";
                break;
            case BAREAPPARATUS:
                apparatusTypeString="Bare Floor Apparatus";
                break;
            default:
                apparatusTypeString="Issue retrieving apparatus type as a string";
                break;
        }

        return apparatusTypeString;
    }
}
