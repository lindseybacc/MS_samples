package com.example.atm_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class transferController {

    protected menu_options transfer_acc = new menu_options();
    boolean transfer_from_checking;
    @FXML
    protected Label transferTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button transferButton;
    @FXML
    protected Label accountOneLabel;
    @FXML
    protected Label accountTwoLabel;
    @FXML
    protected TextField accountOneBalance;
    @FXML
    protected TextField accountTwoBalance;
    @FXML
    private TextField transferAmount;
    @FXML
    protected Label transferDisplayLabel;
    private String regexCheck = "[0-9]*\\.?[0-9]*";
    private static DecimalFormat formatM = new DecimalFormat("###,###,###.00");


    @FXML
    protected void onTransferButtonClick() {
        double amount;

        boolean isOk;

        if (transferAmount.getText().isEmpty()) {
            isOk = false;
            transferDisplayLabel.setText("You must enter an amount to transfer.");
        }

        if (transferAmount.getText().matches(regexCheck)) {
            isOk = true;
        } else {
            isOk = false;
            transferDisplayLabel.setText("Invalid entry. Enter a number to transfer.");
            transferAmount.clear();
        }

        if (isOk) {
            amount = Double.parseDouble(transferAmount.getText());
            if (transfer_from_checking) {
                if (amount < 0 || amount > user_accounts.getCheckingBalance() ) {
                    transferDisplayLabel.setText("Insufficient funds");
                    transferAmount.clear();
                } else {
                    transfer_acc.withdrawChecking(amount);
                    transfer_acc.depositSavings(amount);
                    transferDisplayLabel.setText("Transfer successful");
                }
                accountOneBalance.setText("$ " + formatM.format(transfer_acc.getCheckingBalance()));
                accountTwoBalance.setText("$ " + formatM.format(transfer_acc.getSavingBalance()));

                transferAmount.clear();

            }
            if (!transfer_from_checking) {
                if (amount < 0 || amount > user_accounts.getSavingBalance()) {
                    transferDisplayLabel.setText("Insufficient funds");
                    transferAmount.clear();
                } else {
                    transfer_acc.withdrawSavings(amount);
                    transfer_acc.depositChecking(amount);
                    transferDisplayLabel.setText("Transfer successful");
                }
                accountOneBalance.setText("$ " + formatM.format(transfer_acc.getSavingBalance()));
                accountTwoBalance.setText("$ " + formatM.format(transfer_acc.getCheckingBalance()));

                transferAmount.clear();
            }
        }
    }
    @FXML
    protected void goBackScene() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_menu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        atmMenuController control = loader.getController();
        control.checkingAcc = transfer_from_checking;

    }

    @FXML
    protected void exitProgram() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        HelloController cont = loader.getController();
        cont.loginLabel.setText("Thank you for using the ATM");
    }
}
