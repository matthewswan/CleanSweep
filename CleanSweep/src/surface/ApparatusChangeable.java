package surface;

import utility.ApparatusType;
import utility.CarpetType;

/**
 * Created by Adam on 10/29/2016.
 */
public interface ApparatusChangeable {
    //Interface to be used with apparatus changer types
    public void setApparatusType(CarpetType carpetType);
    public ApparatusType getApparatusType();
    public String toString();
}
