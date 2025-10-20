package Exceptions.Authentication;

import Exceptions.Base.ExpenseTrackerException;

public class AuthenticationException extends ExpenseTrackerException {
    public AuthenticationException(String message) {
        super(message, "AUTHENTICATION_ERROR");
    }
}
