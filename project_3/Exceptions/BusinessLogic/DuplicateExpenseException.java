package Exceptions.BusinessLogic;

public class DuplicateExpenseException extends BusinessLogicException {
    public DuplicateExpenseException(String expenseId) {
        super(String.format("Expense with ID '%s' already exists.", expenseId),
              "DUPLICATE_EXPENSE");
    }
}