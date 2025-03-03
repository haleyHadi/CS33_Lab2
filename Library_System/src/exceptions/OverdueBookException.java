package exceptions;

public class OverdueBookException extends Exception {
    public OverdueBookException(String message) {
        super(message);
    }
}