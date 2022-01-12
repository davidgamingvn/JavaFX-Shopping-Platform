package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;


public class loginController {
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton;

    @FXML
    private Text orText;

    @FXML
    private Button signupButton;

    @FXML
    private ImageView asuLogo;

    public static String username;

    @FXML
    private Label notif;
    public void loginAction(ActionEvent event) throws SQLException {
        if (usernameField.getText().isBlank() || passwordField.getText().isBlank()) {
            notif.setText("Please enter your username and password");
        }
        findAccount();
    }

    public void signupAction(ActionEvent event) throws Exception {
        signup();
    }

    public void findAccount() throws SQLException {
        UserDatabase connectUserDatabase = new UserDatabase();
        Connection connection = connectUserDatabase.getConnection();
        connection.setAutoCommit(false);
        String findAccount = "SELECT * from accounts WHERE username = '" + usernameField.getText()
                + "' AND password = '" + passwordField.getText()+"';";
        try {
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery(findAccount);
            while (result.next()) {
                connection.setAutoCommit(false);
                switch (result.getString(1)) {
                    case "" -> {
                        username = result.getString(2);
                        System.out.println("Account " + usernameField.getText() + " found");
                        notif.setText("Account " + usernameField.getText() + " found");
                        mainCart();
                    }
                    case "MNG" -> {
                        username = result.getString(2);
                        System.out.println("Manager account " + usernameField.getText() + " found");
                        notif.setText("Manager account " + usernameField.getText() + " found");
                        managerUI();
                    }
                    case "EMP" -> {
                        username = result.getString(2);
                        System.out.println("Employee account " + usernameField.getText() + " found");
                        notif.setText("Employee account " + usernameField.getText() + " found");
                        employeeUI();
                    }
                    default -> {
                        notif.setText("Login failed. Please check your username and password");
                        System.out.println("No account found");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.print(e.getClass().getName() + ": " + e.getMessage());
        }
    }
    public void signup() {
        try{
            Stage signupStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup.fxml")));
            signupStage.setTitle("CSE205 Final Project");
            signupStage.setScene(new Scene(root, 400, 550));
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void mainCart() {
        try{
            Stage signupStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("mainUI.fxml")));
            signupStage.setTitle("CSE205 Final Project");
            signupStage.setScene(new Scene(root, 824, 761));
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void employeeUI() {
        try{
            Stage signupStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("employeeUI.fxml")));
            signupStage.setTitle("CSE205 Final Project");
            signupStage.setScene(new Scene(root, 824, 761));
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void managerUI() {
        try{
            Stage signupStage = new Stage();
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("managerUI.fxml")));
            signupStage.setTitle("CSE205 Final Project");
            signupStage.setScene(new Scene(root, 824, 761));
            signupStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
