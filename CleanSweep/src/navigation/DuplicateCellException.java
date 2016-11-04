/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package navigation;

/**
 *
 * @author James Doyle <jdoyle12@mail.depaul.edu>
 */
public class DuplicateCellException extends Exception {

    /**
     * Creates a new instance of <code>DuplicateCellException</code> without
     * detail message.
     */
    public DuplicateCellException() {
    }

    /**
     * Constructs an instance of <code>DuplicateCellException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateCellException(String msg) {
        super(msg);
    }
}
