package Exceptions.BusinessLogic;

public class BudgetExceededException extends BusinessLogicException {
    public BudgetExceededException(String message) {
        super(message);
    }
}