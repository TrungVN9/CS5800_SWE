package Exceptions.Base;

public class ExpenseTrackerException extends RuntimeException {
    private final String errorCode;

    public ExpenseTrackerException(String message) {
        super(message);
        this.errorCode = "GENERIC_ERROR";
    }

    public ExpenseTrackerException(String message, String cause) {
        super(message);
        this.errorCode = cause;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
