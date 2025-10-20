package Exceptions.BusinessLogic;

public class InsufficientFundsException extends BusinessLogicException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}