package com.example.atm_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    protected menu_options User = new menu_options();
    @FXML
    protected Label loginLabel;
    @FXML
    private Label welcomeText;

    @FXML
    private Button exitButton;

    @FXML
    private TextField userID;
    @FXML
    private PasswordField userPIN;

    @FXML
    private Button submit;
    @FXML
    private Button clearFields;

    @FXML
    private Button selectChecking;
    @FXML
    private Button selectSavings;

    @FXML
    private Button newUser;

    @FXML
    private Label displayLabel;

    boolean isChecking = false;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to the ATM");
    }

    @FXML
    protected void onClearFieldsButtonClick() {
        userID.clear();
        userPIN.clear();
    }

    @FXML
    protected void newUserClick(ActionEvent event) throws Exception {
        Stage stage;

        stage = (Stage) newUser.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("newUser.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void enterATM(ActionEvent event) throws Exception {
        Stage stage;
        boolean okEntry = true;

        if (userID.getText().isBlank() || userPIN.getText().isEmpty()) {
            okEntry = false;
        }

        if (okEntry) {
            User.setAccNum();
            stage = (Stage) submit.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("select_account.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        }
    }


    @FXML
    protected void enterCheckings(ActionEvent event) throws Exception {
        Stage stage;
        isChecking = true;

        stage = (Stage) selectChecking.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_menu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        atmMenuController cont = loader.getController();
        cont.checkingAcc = isChecking;
        cont.current_user = User;
    }

    @FXML
    protected void enterSavings(ActionEvent event) throws Exception {
        Stage stage;
        isChecking = false;

        stage = (Stage) selectSavings.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_menu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        atmMenuController cont = loader.getController();
        cont.checkingAcc = isChecking;
        cont.current_user = User;

    }


    @FXML
    protected void exitProgram() throws IOException {
        Stage stage;
        stage = (Stage) exitButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        HelloController cont = loader.getController();
        cont.loginLabel.setText("Thank you for using the ATM");
    }


}