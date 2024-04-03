package web.service.booking.client.exceptions;

public class StringOnlyException extends Exception{
    public StringOnlyException() {
    }

    public StringOnlyException(String message) {
        super(message);
    }
}
