package Exceptions.Authentication;

import Exceptions.Base.ExpenseTrackerException;

public class UnauthorizedAccessException extends ExpenseTrackerException {
    public UnauthorizedAccessException(String message) {
        super(message, "UNAUTHORIZED_ERROR");
    }
}
