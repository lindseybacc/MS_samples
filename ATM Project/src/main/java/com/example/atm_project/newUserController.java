package com.example.atm_project;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;

import static com.example.atm_project.userDatabase.logger;

public class newUserController {
    private userDatabase atmUser = new userDatabase();
    @FXML
    private Button submitUser;
    @FXML
    private Button backButton;
    @FXML
    private Button clearButton;
    @FXML
    private TextField userID;
    @FXML
    private PasswordField userPIN;
    @FXML
    private TextField userFirstName;
    @FXML
    private TextField userLastName;
    @FXML
    private Label newUserLabel;
    @FXML
    protected String compareID;
    @FXML
    protected int comparePIN;
    @FXML
    protected void onClearFieldsButtonClick() {
        userID.clear();
        userFirstName.clear();
        userLastName.clear();
        userPIN.clear();
    }
    @FXML
    protected void createUser() {
        String username = userID.getText().trim();
        String lastName = userLastName.getText().trim();
        String firstName = userFirstName.getText().trim();
        Integer pin = Integer.parseInt(userPIN.getText());
        if (!username.isBlank() && !firstName.isBlank()
                && !lastName.isBlank() && pin != null) {
            try {
                User user = this.createUserObject(username, lastName, firstName, pin);
                if (!atmUser.userExists(username)) {
                    int userId = atmUser.saveUser(user);
                    if (userId > 0) {
                        this.alert("Save", "New User Added!", Alert.AlertType.INFORMATION);
                    } else {
                        this.alert("Error", "Failed!", Alert.AlertType.ERROR);
                    }
                } else {
                    int userID = atmUser.updateUser(user);
                    if (userID > 0) {
                        this.alert("Update", "User Exists, Successfully Updated!", Alert.AlertType.ERROR);
                    } else {
                        this.alert("Error", "Update failed!", Alert.AlertType.ERROR);
                    }
                }
            } catch (Exception exception) {
                logger.log(Level.SEVERE, exception.getMessage());
            }
        } else {
            this.alert("Error", "Please complete fields!", Alert.AlertType.ERROR);
        }

        userID.clear();
        userFirstName.clear();
        userLastName.clear();
        userPIN.clear();
    }

    public void alert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public User createUserObject(String username, String lastName, String firstName, int password) {
        int pinChange;
        pinChange = Integer.parseInt(userPIN.getText());
        User user = new User();
        user.setUsername(username);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setPin(pinChange);
        return user;
    }

    @FXML
    protected void goBackScene() throws IOException {
        Stage stage;
        stage = (Stage) backButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("atm_login.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
    }

}
