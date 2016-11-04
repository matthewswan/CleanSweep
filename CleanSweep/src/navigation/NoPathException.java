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
public class NoPathException extends Exception {

    /**
     * Creates a new instance of <code>NoPathException</code> without detail
     * message.
     */
    public NoPathException() {
    }

    /**
     * Constructs an instance of <code>NoPathException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoPathException(String msg) {
        super(msg);
    }
}
