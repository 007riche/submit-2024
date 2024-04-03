package web.service.booking.client.exceptions;

public class InvalidCardNumberException extends Exception {
    public InvalidCardNumberException() {
    }

    public InvalidCardNumberException(String message) {
        super(message);
    }
}
