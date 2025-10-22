package Exceptions.BusinessLogic;

import Exceptions.Base.ExpenseTrackerException;

public class BusinessLogicException extends ExpenseTrackerException {
    private final String errorCode;

    public BusinessLogicException(String message) {
        super(message);
        this.errorCode = "BUSINESS_LOGIC_ERROR";
    }

    public BusinessLogicException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
