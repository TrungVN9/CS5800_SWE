package Exceptions.Authentication;

public class UnauthorizedAccessException extends AuthenticationException {
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}
