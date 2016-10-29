package surface;

import utility.ApparatusType;
import utility.CarpetType;

/**
 * Created by Adam on 10/29/2016.
 */
public interface ApparatusChangeable {
    public void setApparatusType(CarpetType carpetType);
    public ApparatusType getApparatusType();
    public String toString();
}
