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

    public ApparatusChangerImpl(){}

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

    public ApparatusType getApparatusType(){
        return apparatusType;
    }

    public String toString(){
        String apparatusTypeString;

        switch(apparatusType) {
            case LOWPILEAPPARATUS:
                apparatusTypeString="Bare Floor Apparatus";
                break;
            case HIGHPILEAPPARATUS:
                apparatusTypeString="Low Pile Carpet Floor Apparatus";
                break;
            case BAREAPPARATUS:
                apparatusTypeString="High Pile Carpet Floor Apparatus";
                break;
            default:
                apparatusTypeString="Issue retrieving apparatus type as a string";
                break;
        }

        return apparatusTypeString;
    }
}
