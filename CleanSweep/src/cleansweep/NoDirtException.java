/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cleansweep;

/**
 *
 * @author MatthewSwan
 */
public class NoDirtException extends Exception {
    /**
     * Creates a new instance of <code>NoDirtException</code> without detail
     * message.
     */
    public NoDirtException() {
    }

    /**
     * Constructs an instance of <code>NoDirtException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public NoDirtException(String msg) {
        super(msg);
    }
}
