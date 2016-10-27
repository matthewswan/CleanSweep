/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author James Doyle
 */
public class InvalidMoveException extends Exception {

    /**
     * Creates a new instance of <code>InvalidMoveException</code> without
     * detail message.
     */
    public InvalidMoveException() {
    }

    /**
     * Constructs an instance of <code>InvalidMoveException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidMoveException(String msg) {
        super(msg);
    }
}
