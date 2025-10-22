package Exceptions.Validation;

import Exceptions.Base.ExpenseTrackerException;

public class ValidationException extends ExpenseTrackerException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String field, String message) {
        super(String.format("Validation failed for '%s': %s", field, message));
    }

    public ValidationException(String field, Object invalidValue, String message) {
        super(String.format(
            "Validation failed for '%s' (value: %s): %s",
            field, invalidValue, message
        ));
    }
}
