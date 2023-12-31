package com.example.atm_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;

public class check_balance_controller {

    protected menu_options current_user = new menu_options();
    boolean isChecking;
    @FXML
    protected Label balanceShow;
    @FXML
    protected Label accountTitle;

    @FXML
    protected Label accountLabel;
    @FXML
    private Button backButton;
    @FXML
    private Button exitButton;

    @FXML
    protected void goBackScene() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_menu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        atmMenuController cont = loader.getController();
        cont.checkingAcc = isChecking;
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
