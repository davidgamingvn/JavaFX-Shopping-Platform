package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.sql.*;

public class signupController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameSignupField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private TextField emailSignupField;

    @FXML
    private TextField addressSignupField;

    @FXML
    private Label notif;

    @FXML
    private Button signupButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField specialCode;

    public void signupButtonAction(ActionEvent event) throws Exception {
        if (usernameSignupField.getText().isBlank()) notif.setText("Please enter your username");
        else if (passwordField.getText().isBlank()) notif.setText("Please enter your password");
        else if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            notif.setText("Password does not match");
        } else if (lastNameField.getText().isBlank() || firstNameField.getText().isBlank())
            notif.setText("Please enter your first and last names");
        else if (emailSignupField.getText().isBlank()) notif.setText("Please enter your email");
        else {
            signupUser();
        }
    }

    public void backButtonAction(ActionEvent event) throws Exception {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void signupUser() throws SQLException {
        UserDatabase userDatabase = new UserDatabase();
        Connection connectDatabase = userDatabase.getConnection();
        String insertCommand = "INSERT INTO accounts(user_type, username, password, first_name, last_name, email, address) VALUES ('";
        String valuesCommand = specialCode.getText() + "','" + usernameSignupField.getText() + "','" +
                passwordField.getText() + "','" +
                firstNameField.getText() + "','" +
                lastNameField.getText() + "','" +
                emailSignupField.getText() + "','" +
                addressSignupField.getText() + "');";
        try {
            Statement smt = connectDatabase.createStatement();
            ResultSet result = smt.executeQuery("SELECT * from accounts;");
            while (result.next()) {
                if (!result.getString("username").equals(usernameSignupField.getText())) {
                    smt = connectDatabase.createStatement();
                    smt.executeUpdate(insertCommand + valuesCommand);
                    notif.setText("Sign up successfully");
                } else {
                    notif.setText("Username already existed");
                }
                break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}