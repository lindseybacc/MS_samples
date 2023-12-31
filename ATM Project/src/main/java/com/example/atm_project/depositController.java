package com.example.atm_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;


public class depositController  {
    protected menu_options deposit_acc = new menu_options();

    boolean deposit_checking;
    @FXML
    protected Label depositTitle;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button depositButton;
    @FXML
    protected TextField currentBalance;
    @FXML
    private TextField depositAmount;
    @FXML
    protected Label depositDisplayLabel;

    private String regexCheck = "[0-9]*\\.?[0-9]*";

    private static DecimalFormat formatM = new DecimalFormat("###,###,###.00");

    @FXML
    protected void onDepositButtonClick() {
        String convertFromDouble;
        boolean isOk = false;

        if (depositAmount.getText().isEmpty()) {
            isOk = false;
            depositDisplayLabel.setText("You must enter an amount to deposit.");
        }

        if (depositAmount.getText().matches(regexCheck)) {
            isOk = true;
        } else {
            isOk = false;
            depositDisplayLabel.setText("Invalid entry. Enter a number to deposit.");
            depositAmount.clear();
        }

        if (isOk) {
            if (deposit_checking) {
                deposit_acc.depositChecking(Double.parseDouble(depositAmount.getText()));
                depositDisplayLabel.setText("Deposit Success");
                currentBalance.setText("$ " + formatM.format(user_accounts.getCheckingBalance()));
                depositAmount.clear();
            }
            if (!deposit_checking) {
                deposit_acc.depositSavings(Double.parseDouble(depositAmount.getText()));
                depositDisplayLabel.setText("Deposit Success");
                currentBalance.setText("$ " + formatM.format(user_accounts.getSavingBalance()));
                depositAmount.clear();
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
        control.checkingAcc = deposit_checking;


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
