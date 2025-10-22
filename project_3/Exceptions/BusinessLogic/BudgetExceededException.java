package Exceptions.BusinessLogic;

public class BudgetExceededException extends BusinessLogicException {
    private final double budgetLimit;
    private final double attemptedAmount;

    public BudgetExceededException(double budgetLimit, double attemptedAmount) {
        super(String.format("Budget exceeded! Limit: %.2f, Attempted: %.2f", budgetLimit, attemptedAmount),
              "BUDGET_EXCEEDED");
        this.budgetLimit = budgetLimit;
        this.attemptedAmount = attemptedAmount;
    }

    public double getBudgetLimit() {
        return budgetLimit;
    }

    public double getAttemptedAmount() {
        return attemptedAmount;
    }
}
