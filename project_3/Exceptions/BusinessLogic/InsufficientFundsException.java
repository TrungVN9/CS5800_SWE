package Exceptions.BusinessLogic;

public class InsufficientFundsException extends BusinessLogicException {
    private final double currentBalance;
    private final double requestedAmount;

    public InsufficientFundsException(double currentBalance, double requestedAmount) {
        super(String.format("Insufficient funds! Current: %.2f, Requested: %.2f", currentBalance, requestedAmount),
              "INSUFFICIENT_FUNDS");
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }
}
