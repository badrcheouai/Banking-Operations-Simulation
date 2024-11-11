import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class BankingSimulation extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Banking Simulation");

        Label label = new Label("Welcome to the Banking Simulation");
        Button savingsButton = new Button("Savings Account");
        Button checkingButton = new Button("Checking Account");
        Button businessButton = new Button("Business Account");
        Button exitButton = new Button("Exit");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, savingsButton, checkingButton, businessButton, exitButton);

        savingsButton.setOnAction(e -> runBankingOperations(primaryStage, new SavingsAccount("SA", 1000)));
        checkingButton.setOnAction(e -> runBankingOperations(primaryStage, new CheckingAccount("CA", 2000)));
        businessButton.setOnAction(e -> runBankingOperations(primaryStage, new BusinessAccount("BA", 5000)));
        exitButton.setOnAction(e -> primaryStage.close());

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void runBankingOperations(Stage primaryStage, Account currentAccount) {
        Label accountLabel = new Label("Account: " + currentAccount.accountNumber);
        Label balanceLabel = new Label("Balance: " + currentAccount.getBalance()+ " DH ");
        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button transactionLogButton = new Button("Transaction Log");
        Button backButton = new Button("Back");

        VBox layout = new VBox(10);
        layout.getChildren().addAll(accountLabel, balanceLabel, amountField, depositButton, withdrawButton, transactionLogButton, backButton);

        depositButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.deposit(amount);
                balanceLabel.setText("Balance: " + currentAccount.getBalance()+" DH ");
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid amount entered.");
            }
        });

        withdrawButton.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(amountField.getText());
                currentAccount.withdraw(amount);
                balanceLabel.setText("Balance: " + currentAccount.getBalance()+" DH ");
            } catch (NumberFormatException ex) {
                balanceLabel.setText("Invalid amount entered.");
            } catch (InsufficientFundsException ex) {
                balanceLabel.setText(ex.getMessage());
            }
        });

        transactionLogButton.setOnAction(e -> {
            StringBuilder log = new StringBuilder("Transaction Log:\n");
            for (String transaction : currentAccount.getTransactionLog()) {
                log.append(transaction).append("\n");
            }
            balanceLabel.setText(log.toString());
        });

        backButton.setOnAction(e -> start(primaryStage));

        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
