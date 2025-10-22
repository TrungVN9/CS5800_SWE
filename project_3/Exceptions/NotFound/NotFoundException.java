package Exceptions.NotFound;

import Exceptions.Base.ExpenseTrackerException;

public class NotFoundException extends ExpenseTrackerException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String entityName, Object identifier) {
        super(String.format("%s with identifier '%s' was not found.", entityName, identifier));
    }
}
