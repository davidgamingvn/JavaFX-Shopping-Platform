package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class cartUIController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private TextArea userCart;

    @FXML
    private Label totalLabel;

    @FXML
    private TextField totalField;

    @FXML
    private JFXButton checkoutButton;

    @FXML
    private JFXButton backButton;

    @FXML
    private StackPane confirmStackPane;

    @FXML
    private JFXDialogLayout confirmLayout;

    @FXML
    private JFXDialog checkoutDialog;

    @FXML
    private JFXDialog confirmDialog;

    @FXML
    private Label notif;

    private final Cart currentCart = mainUIController.currentCart;

    @FXML
    public void backButtonAction(ActionEvent event) throws Exception {
        confirmLayout = new JFXDialogLayout();
        confirmLayout.setBody(new Label("Do you want to save your order"));
        JFXButton noButton = new JFXButton("No");
        JFXButton yesButton = new JFXButton("Yes");
        confirmDialog = new JFXDialog(confirmStackPane, confirmLayout, JFXDialog.DialogTransition.CENTER);
        confirmDialog.show();
        noButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage stage = (Stage) backButton.getScene().getWindow();
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        yesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    addToDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                confirmDialog.close();
                notif.setText("Order saved");
            }
        });
        confirmLayout.setActions(noButton, yesButton);
    }

    @FXML
    public void checkoutButtonAction(ActionEvent event) throws SQLException, IOException {
        JFXDialogLayout checkoutLayout = new JFXDialogLayout();
        checkoutLayout.setHeading(new Label("THANK YOU"));
        checkoutLayout.setBody(new Label("YOUR ORDER IS BEING PROCESSED"));
        checkoutDialog = new JFXDialog(confirmStackPane, checkoutLayout, JFXDialog.DialogTransition.CENTER);
        checkoutDialog.show();

        UserDatabase connectUserDatabase = new UserDatabase();
        Connection connection = connectUserDatabase.getConnection();
        connection.setAutoCommit(false);
        String updateOrder = "UPDATE account_order SET status = 'PURCHASED' WHERE username = '" +
                loginController.username + "' AND order_id = '"+currentCart.serialize()+"';";
        String addOrder = "INSERT INTO account_order(username, order_id, status) VALUES ('" + loginController.username + "', '" +
                currentCart.serialize() + "', 'PURCHASED');";
        String findAccount = "SELECT * FROM account_order WHERE username = '" + loginController.username + "';";
        try {
            Statement smt = connection.createStatement();
            smt.executeUpdate(addOrder);
            smt = connection.createStatement();
            ResultSet result = smt.executeQuery(findAccount);
            while (result.next()) {
                if (result.getString("order_id") != null && result.getString("username").equals(loginController.username)) {
                    smt = connection.createStatement();
                    smt.executeUpdate(updateOrder);
                } else {
                    continue;
                }
                connection.commit();
            }
            smt = connection.createStatement();
            result = smt.executeQuery("SELECT * FROM account_order;");
            while (result.next()) {
                System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addToDatabase() throws Exception {
        UserDatabase connectUserDatabase = new UserDatabase();
        Connection connection = connectUserDatabase.getConnection();
        connection.setAutoCommit(false);
        String findAccount = "SELECT * FROM account_order;";
        String addOrder = "INSERT INTO account_order(username, order_id, status) VALUES ('" + loginController.username + "', '" +
                currentCart.serialize() + "', 'PENDING');";
        String updateOrder = "UPDATE account_order SET order_id = '" + currentCart.serialize() + "' WHERE username = '" + loginController.username + "';";
        try {
            Statement smt = connection.createStatement();
            //smt= connection.createStatement();
            ResultSet result = smt.executeQuery(findAccount);
            while (result.next()) {
                if (result.getString("order_id").equals(currentCart.serialize()) && result.getString("username").equals(loginController.username)) {
                    smt = connection.createStatement();
                    smt.executeUpdate(updateOrder);
                } else {
                    smt.executeUpdate(addOrder);
                }
                connection.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Cart currentCart = mainUIController.currentCart;
        userCart.setText(currentCart.print());
        totalField.setText("$" + currentCart.findTotalPrice());
    }
}
