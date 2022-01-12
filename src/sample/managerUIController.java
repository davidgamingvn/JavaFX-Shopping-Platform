package sample;

import com.jfoenix.controls.JFXButton;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class managerUIController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private JFXButton createCustomerButton;

    @FXML
    private JFXButton createEmployeeButton;

    @FXML
    private TableView<pendingOrder> pendingOrderView;

    @FXML
    private TableColumn<pendingOrder, String> usernameCol;

    @FXML
    private TableColumn<pendingOrder, String> order_idCol;

    @FXML
    private TableColumn<pendingOrder, String> statusCol;

    @FXML
    private TableColumn<pendingOrder, String> editCol;

    @FXML
    private AnchorPane createPane;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;

    @FXML
    private Label closePane;

    @FXML
    private Label signup;

    @FXML
    private Label notif;

    @FXML
    private JFXButton refreshButton;

    @FXML
    void closeCreatePane(MouseEvent event) {
        createPane.setVisible(false);
    }

    public void createEmployeeAction(ActionEvent event) throws Exception {
        createPane.setVisible(true);
        signup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserDatabase userDatabase = new UserDatabase();
                Connection connectDatabase = userDatabase.getConnection();
                String insertCommand = "INSERT INTO accounts(user_type, username, password, first_name, last_name, email, address) VALUES ('";
                String valuesCommand = "EMP','" + usernameField.getText() + "','" +
                        passwordField.getText() + "','" +
                        firstNameField.getText() + "','" +
                        lastNameField.getText() + "','" +
                        emailField.getText() + "','" +
                        addressField.getText() + "');";
                try {
                    Statement smt = connectDatabase.createStatement();
                    ResultSet result = smt.executeQuery("SELECT * from accounts;");
                    while (result.next()) {
                        if (!result.getString("username").equals(usernameField.getText())) {
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
        });
    }

    public void createCustomerAction(ActionEvent event) throws Exception {
        createPane.setVisible(true);
        signup.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                UserDatabase userDatabase = new UserDatabase();
                Connection connectDatabase = userDatabase.getConnection();
                String insertCommand = "INSERT INTO accounts(user_type, username, password, first_name, last_name, email, address) VALUES ('";
                String valuesCommand = "','" + usernameField.getText() + "','" +
                        passwordField.getText() + "','" +
                        firstNameField.getText() + "','" +
                        lastNameField.getText() + "','" +
                        emailField.getText() + "','" +
                        addressField.getText() + "');";
                try {
                    Statement smt = connectDatabase.createStatement();
                    ResultSet result = smt.executeQuery("SELECT * from accounts;");
                    while (result.next()) {
                        if (!result.getString("username").equals(usernameField.getText())) {
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
        });
    }

    @FXML
    void refreshAction(ActionEvent event) {
        refresh();
    }

    ObservableList<pendingOrder> pendingOrders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createPane.setVisible(false);

        usernameCol.setCellValueFactory(new PropertyValueFactory<pendingOrder, String>("username"));
        order_idCol.setCellValueFactory(new PropertyValueFactory<pendingOrder, String>("order_id"));
        statusCol.setCellValueFactory(new PropertyValueFactory<pendingOrder, String>("status"));

        pendingOrderView.setEditable(true);
        statusCol.setCellFactory(TextFieldTableCell.forTableColumn());
        statusCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<pendingOrder, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<pendingOrder, String> event) {
                event.getTableView().getItems().get(event.getTablePosition().getRow()).setStatus(event.getNewValue());
                try {
                    pendingOrder order = pendingOrderView.getSelectionModel().getSelectedItem();
                    UserDatabase userDatabase = new UserDatabase();
                    Connection connectDatabase = userDatabase.getConnection();
                    Statement smt = connectDatabase.createStatement();
                    smt.executeUpdate("UPDATE account_order SET status='" + order.getStatus()+"' WHERE order_id = '" + order.getOrder_id()+"';");
                    refresh();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        pendingOrderView.setItems(pendingOrders);
        refresh();
    }

    private void refresh() {
        pendingOrders.clear();
        UserDatabase connectDatabase = new UserDatabase();
        Connection connection = connectDatabase.getConnection();
        try {
            Statement smt = connection.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM account_order;");
            while (result.next()) {
                pendingOrders.add(new pendingOrder(result.getString(1), result.getString(2), result.getString(3)));
            }
            pendingOrderView.setItems(pendingOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
