import java.util.ArrayList;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


abstract class Account implements BankingOperations, TransactionLog {
    protected String accountNumber;
    protected double balance;
    protected ArrayList<String> transactionLog = new ArrayList<>();

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            recordTransaction("Deposited: " + amount);
        }
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient funds for withdrawal.");
        }
        balance -= amount;
        recordTransaction("Withdrew: " + amount);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void recordTransaction(String transaction) {
        transactionLog.add(transaction);
    }

    public ArrayList<String> getTransactionLog() {
        return transactionLog;
    }
}

