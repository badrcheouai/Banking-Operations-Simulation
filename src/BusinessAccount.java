class BusinessAccount extends Account {
    private static final double TRANSACTION_FEE = 5.0;

    public BusinessAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        double totalAmount = amount + TRANSACTION_FEE;
        if (totalAmount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal including transaction fee.");
        }
        balance -= totalAmount;
        recordTransaction("Withdrew: " + amount + " (Fee: " + TRANSACTION_FEE + ")");
    }
}
