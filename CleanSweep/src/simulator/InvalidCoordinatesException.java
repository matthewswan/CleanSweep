/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class InvalidCoordinatesException extends Exception {

    /**
     * Creates a new instance of <code>InvalidCoordinatesException</code>
     * without detail message.
     */
    public InvalidCoordinatesException() {
    }

    /**
     * Constructs an instance of <code>InvalidCoordinatesException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidCoordinatesException(String msg) {
        super(msg);
    }
}
