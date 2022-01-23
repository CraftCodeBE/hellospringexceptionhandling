package be.contribute.helloworld.service;

/**
 * Exception to use in the CRUD library.
 */
public class MessageBusinessException extends RuntimeException {
    public static String ID_ALREADY_EXISTS = "The greatest trick the devil ever pulled, was taking this id.";
    public static String MESSAGE_NOT_FOUND = "There is no message in this bottle...";

    public MessageBusinessException(String message) {
        super(message);
    }
}
