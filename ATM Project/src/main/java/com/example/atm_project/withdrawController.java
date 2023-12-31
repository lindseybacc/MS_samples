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


public class withdrawController  {
    protected menu_options withdraw_acc = new menu_options();
    boolean isChecking;
    @FXML
    protected Label withdrawTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button withdrawButton;
    @FXML
    protected TextField currentBalance;
    @FXML
    private TextField withdrawAmount;
    @FXML
    protected Label withdrawDisplayLabel;
    private String regexCheck = "[0-9]*\\.?[0-9]*";
    private static DecimalFormat formatM = new DecimalFormat("###,###,###.00");

    @FXML
    protected void onWithdrawButtonClick() {
        double amount;
        String convertFromDouble;
        boolean isOk = false;

        if (withdrawAmount.getText().isEmpty()) {
            isOk = false;
            withdrawDisplayLabel.setText("You must enter an amount to withdraw.");
        }

        if (withdrawAmount.getText().matches(regexCheck)) {
            isOk = true;
        } else {
            isOk = false;
            withdrawDisplayLabel.setText("Invalid entry. Enter a number to withdraw.");
            withdrawAmount.clear();
        }

        if (isOk) {
            amount = Double.parseDouble(withdrawAmount.getText());
            if (isChecking) {
                if (amount < 0 || amount > user_accounts.getCheckingBalance() ) {
                    withdrawDisplayLabel.setText("Insufficient funds");
                } else {
                    withdraw_acc.withdrawChecking(amount);
                    withdrawDisplayLabel.setText("Withdraw successful");
                }
                currentBalance.setText("$ " + formatM.format(user_accounts.getCheckingBalance()));
                withdrawAmount.clear();
            }
            if (!isChecking) {
                if (amount < 0 || amount > user_accounts.getSavingBalance()) {
                    withdrawDisplayLabel.setText("Insufficient funds");
                } else {
                    withdraw_acc.withdrawSavings(amount);
                    withdrawDisplayLabel.setText("Withdraw successful");
                }
                currentBalance.setText(("$ " + formatM.format(user_accounts.getSavingBalance())));
                withdrawAmount.clear();
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
        control.checkingAcc = isChecking;

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
