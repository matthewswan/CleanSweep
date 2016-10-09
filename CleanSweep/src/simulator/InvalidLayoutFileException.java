package simulator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class InvalidLayoutFileException extends Exception {

    /**
     * Creates a new instance of <code>InvalidLayoutFileException</code> without
     * detail message.
     */
    public InvalidLayoutFileException() {
    }

    /**
     * Constructs an instance of <code>InvalidLayoutFileException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidLayoutFileException(String msg) {
        super(msg);
    }
}
