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
public class InvalidSimulationException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidSimulationException</code> without
     * detail message.
     */
    public InvalidSimulationException() {
    }

    /**
     * Constructs an instance of <code>InvalidSimulationException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidSimulationException(String msg) {
        super(msg);
    }
}
