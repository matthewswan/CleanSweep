/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memory;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class UnexpectedChangeException extends Exception {

    /**
     * Creates a new instance of <code>UnexpectedChangeException</code> without
     * detail message.
     */
    public UnexpectedChangeException() {
    }

    /**
     * Constructs an instance of <code>UnexpectedChangeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnexpectedChangeException(String msg) {
        super(msg);
    }
}
