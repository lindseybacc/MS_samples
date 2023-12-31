package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;


public class atmMenuController {

    protected menu_options current_user = new menu_options();
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;
    @FXML
    private Button checkBalanceButton;
    @FXML
    private Button depositButton;
    @FXML
    private Button withdrawButton;
    @FXML
    private Button transferButton;

    boolean checkingAcc;

    private static DecimalFormat formatM = new DecimalFormat("###,###,###.00");

    @FXML
    protected void checkBalance(ActionEvent event) throws Exception {

        Stage stage;
        stage = (Stage) checkBalanceButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("check_balance.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        check_balance_controller cont = loader.getController();
        if (checkingAcc) {
            cont.isChecking = checkingAcc;
            cont.accountTitle.setText("CHECKING ACCOUNT BALANCE");
            cont.accountLabel.setText("Checking Account balance:");
            cont.balanceShow.setText("$ " + formatM.format(current_user.getCheckingBalance()));
        }
        if (!checkingAcc) {
            cont.isChecking = checkingAcc;
            cont.accountTitle.setText("SAVINGS ACCOUNT BALANCE");
            cont.accountLabel.setText("Savings Account balance: ");
            cont.balanceShow.setText("$ " + formatM.format(current_user.getSavingBalance()));
        }

    }

    @FXML
    protected void depositFunds(ActionEvent event) throws Exception {
        String convertNum;

        Stage stage;
        stage = (Stage) depositButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("deposit.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        depositController cont = loader.getController();

        if (checkingAcc) {
            cont.depositTitle.setText("Checking Account Deposit");
            cont.currentBalance.setText("$ " + formatM.format(current_user.getCheckingBalance()));
            cont.deposit_checking = checkingAcc;
            cont.deposit_acc = current_user;
        }
        if (!checkingAcc) {
            cont.depositTitle.setText("Savings Account Deposit");
            cont.deposit_checking = checkingAcc;
            cont.currentBalance.setText("$ " + formatM.format(current_user.getSavingBalance()));
            cont.deposit_acc = current_user;
        }

    }

    @FXML
    protected void withdrawFunds(ActionEvent event) throws Exception {
        String convertNum;

        Stage stage;
        stage = (Stage) withdrawButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("withdraw.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        withdrawController cont = loader.getController();

        if (checkingAcc) {
            cont.withdrawTitle.setText("Checking Account Withdrawal");
            cont.currentBalance.setText("$ " + formatM.format(current_user.getCheckingBalance()));
            cont.isChecking = checkingAcc;
            cont.withdraw_acc = current_user;
        }
        if (!checkingAcc){
            cont.withdrawTitle.setText("Savings Account Withdrawal");
            cont.isChecking = checkingAcc;
            cont.currentBalance.setText("$ " + formatM.format(current_user.getSavingBalance()));
            cont.withdraw_acc = current_user;
        }

    }

    @FXML
    protected void transferFunds(ActionEvent event) throws Exception {

        Stage stage;
        stage = (Stage) transferButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("transfer.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        transferController cont = loader.getController();

        if (checkingAcc) {
            cont.transferTitle.setText("Checking Account Transfer to Savings Account");
            cont.accountOneLabel.setText("Checking Balance");
            cont.accountTwoLabel.setText("Savings Balance");

            cont.accountOneBalance.setText("$ " + formatM.format(current_user.getCheckingBalance()));
            cont.accountTwoBalance.setText("$ " + formatM.format(current_user.getSavingBalance()));

            cont.transfer_from_checking = checkingAcc;
            cont.transfer_acc = current_user;
        }
        if (!checkingAcc) {
            cont.transferTitle.setText("Savings Account Transfer to Checking Account");
            cont.accountOneLabel.setText("Savings Balance: ");
            cont.accountTwoLabel.setText("Checking Balance: ");

            cont.accountOneBalance.setText("$ " + formatM.format(current_user.getSavingBalance()));
            cont.accountTwoBalance.setText("$ " + formatM.format(current_user.getCheckingBalance()));

            cont.transfer_from_checking = checkingAcc;
            cont.transfer_acc = current_user;
        }

    }
    @FXML
    protected void goBackScene() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("select_account.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

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