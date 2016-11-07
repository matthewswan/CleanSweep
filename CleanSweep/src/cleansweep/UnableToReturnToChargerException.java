/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class UnableToReturnToChargerException extends Exception {

    /**
     * Creates a new instance of <code>UnableToReturnToChargerException</code>
     * without detail message.
     */
    public UnableToReturnToChargerException() {
    }

    /**
     * Constructs an instance of <code>UnableToReturnToChargerException</code>
     * with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UnableToReturnToChargerException(String msg) {
        super(msg);
    }
}
