package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class accountDisplayController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private Label accountName;

    @FXML
    private Label username;

    @FXML
    private Label password;

    @FXML
    private Label firstname;

    @FXML
    private Label lastname;

    @FXML
    private Label email;

    @FXML
    private Label address;

    @FXML
    private TextField addressDisplay;

    @FXML
    private PasswordField passwordDisplay;

    @FXML
    private TextField firstnameDisplay;

    @FXML
    private TextField emailDisplay;

    @FXML
    private TextField usernameDisplay;

    @FXML
    private TextField lastnameDisplay;

    @FXML
    private ImageView accountIcon;

    @FXML
    private JFXButton backButton;

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainUI.fxml")));
        contentPane.getChildren().removeAll();
        contentPane.getChildren().setAll(fxml);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserDatabase connectUserDatabase = new UserDatabase();
        Connection connection = connectUserDatabase.getConnection();
        String findAccount = "SELECT * from accounts WHERE username = '" + loginController.username + "';";
        try {
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery(findAccount);
            System.out.println(loginController.username);
            while (result.next()) {
                accountName.setText(loginController.username);
                usernameDisplay.setText(loginController.username);
                passwordDisplay.setText(result.getString(3));
                firstnameDisplay.setText(result.getString(4));
                lastnameDisplay.setText(result.getString(5));
                emailDisplay.setText(result.getString(6));
                addressDisplay.setText(result.getString(7));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
