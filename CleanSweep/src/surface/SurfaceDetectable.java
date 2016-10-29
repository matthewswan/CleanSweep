package surface;

import utility.CarpetType;

/**
 * Created by Adam on 10/29/2016.
 */
public interface SurfaceDetectable {
    public void setCarpetType(CarpetType newCarpetType);
    public CarpetType getCarpetType();
    public String toString();
}
