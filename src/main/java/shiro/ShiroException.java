package shiro;

/**
 * Represents a custom exception thrown by the Shiro application
 * when user input or storage operations are invalid.
 */
public class ShiroException extends Exception {

    /**
     * Creates a new ShiroException with the specified message.
     *
     * @param message Error message describing the problem.
     */
    public ShiroException(String message) {
        super(message);
    }
}
