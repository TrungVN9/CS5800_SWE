package Exceptions.Authentication;

import Exceptions.Base.ExpenseTrackerException;

public class InvalidCredentialsException extends ExpenseTrackerException{
    public InvalidCredentialsException(String message) {
        super(message, "INVALID_CREDENTIALS_ERROR");
    }
}
