package kthknugarna.iv1201project.model.exceptions;

/**
 *
 * @author Jonas
 * @author Benjamin
 * @author Anton
 * 
 * Thrown when input verification fails
 * 
 * @see VerificationHandler
 */
public class VerificationException extends Exception {

    /**
     * Creates a new instance of <code>VerificationException</code> without
     * detail message.
     */
    public VerificationException() {
    }

    /**
     * Constructs an instance of <code>VerificationException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public VerificationException(String msg) {
        super(msg);
    }
}
