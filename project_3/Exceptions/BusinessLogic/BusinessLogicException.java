package Exceptions.BusinessLogic;

import Exceptions.ExpenseTrackerException;

public class BusinessLogicException extends ExpenseTrackerException {
    public BusinessLogicException(String message) {
        super(message);
    }
}