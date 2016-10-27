/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class NullSensorException extends Exception {

    /**
     * Creates a new instance of <code>NullSensorException</code> without detail
     * message.
     */
    public NullSensorException() {
    }

    /**
     * Constructs an instance of <code>NullSensorException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NullSensorException(String msg) {
        super(msg);
    }
}
