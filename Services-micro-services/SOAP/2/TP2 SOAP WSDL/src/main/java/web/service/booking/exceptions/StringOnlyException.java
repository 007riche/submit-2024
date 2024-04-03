package web.service.booking.exceptions;

public class StringOnlyException extends Exception{
    public StringOnlyException() {
    }

    public StringOnlyException(String message) {
        super(message);
    }
}
