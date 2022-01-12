package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class mainUIController implements Initializable {

    @FXML
    private AnchorPane contentPane;

    @FXML
    private ImageView image1;

    @FXML
    private Label item1;

    @FXML
    private TextField quantity1;

    @FXML
    private JFXButton addMac;

    @FXML
    private ImageView icon1;

    @FXML
    private Label price1;

    @FXML
    private JFXButton removeMac;

    @FXML
    private JFXButton cartButton;

    @FXML
    private ImageView cartIcon;

    @FXML
    private JFXButton accountDisplay;

    @FXML
    private ImageView accountIcon;

    @FXML
    private StackPane cartStackPane;

    @FXML
    private JFXDialog cartDialog;

    @FXML
    private ImageView image2;

    @FXML
    private Label item2;

    @FXML
    private TextField quantity2;

    @FXML
    private JFXButton addIphone;

    @FXML
    private ImageView icon11;

    @FXML
    private Label price2;

    @FXML
    private JFXButton removeMac1;

    @FXML
    private ImageView image3;

    @FXML
    private Label item3;

    @FXML
    private TextField quantity3;

    @FXML
    private JFXButton addAirpods;

    @FXML
    private ImageView icon111;

    @FXML
    private Label price3;

    @FXML
    private JFXButton removeAirpods;

    @FXML
    private ImageView image4;

    @FXML
    private Label item4;

    @FXML
    private TextField quantity4;

    @FXML
    private JFXButton addSurface;

    @FXML
    private ImageView icon12;

    @FXML
    private Label price4;

    @FXML
    private JFXButton removeSurface;

    @FXML
    private ImageView image5;

    @FXML
    private Label item5;

    @FXML
    private TextField quantity5;

    @FXML
    private JFXButton addSony;

    @FXML
    private ImageView icon112;

    @FXML
    private Label price5;

    @FXML
    private JFXButton removeSony;

    @FXML
    private ImageView image6;

    @FXML
    private Label item6;

    @FXML
    private TextField quantity6;

    @FXML
    private JFXButton addGalaxy;

    @FXML
    private ImageView icon1111;

    @FXML
    private Label price6;

    @FXML
    private JFXButton removeGalaxy;

    public static Cart currentCart = new Cart();

    @FXML
    public void addMacButtonAction(ActionEvent event) {
        if (quantity1.getText().isBlank()) quantity1.setText("1");
        Item currentItem = new Item(item1.getText(),Integer.parseInt(quantity1.getText()),Integer.parseInt(price1.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
        System.out.println(currentCart.print());
    }

    @FXML
    public void removeMacButtonAction(ActionEvent event) {
        if (quantity1.getText().isBlank()) quantity1.setText("1");
        Item currentItem = new Item(item1.getText(),Integer.parseInt(quantity1.getText()),Integer.parseInt(price1.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
        System.out.println(currentCart.print());
    }

    @FXML
    void addIphoneButtonAction(ActionEvent event) {
        if (quantity2.getText().isBlank()) quantity2.setText("1");
        Item currentItem = new Item(item2.getText(),Integer.parseInt(quantity2.getText()),Integer.parseInt(price2.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
    }

    @FXML
    void addAirpodsButtonAction(ActionEvent event) {
        if (quantity3.getText().isBlank()) quantity3.setText("1");
        Item currentItem = new Item(item3.getText(),Integer.parseInt(quantity3.getText()),Integer.parseInt(price3.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
    }

    @FXML
    void addSurfaceButtonAction(ActionEvent event) {
        if (quantity4.getText().isBlank()) quantity4.setText("1");
        Item currentItem = new Item(item4.getText(),Integer.parseInt(quantity4.getText()),Integer.parseInt(price4.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
    }

    @FXML
    void addSonyButtonAction(ActionEvent event) {
        if (quantity5.getText().isBlank()) quantity5.setText("1");
        Item currentItem = new Item(item5.getText(),Integer.parseInt(quantity5.getText()),Integer.parseInt(price5.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
    }

    @FXML
    void addGalaxyButtonAction(ActionEvent event) {
        if (quantity6.getText().isBlank()) quantity6.setText("1");
        Item currentItem = new Item(item6.getText(),Integer.parseInt(quantity6.getText()),Integer.parseInt(price6.getText().substring(1)));
        if (currentCart.duplicateItem(currentItem) || currentCart.isEmpty()) {
            currentItem.setQuantity(currentCart.findQuantityOfItem(currentItem));
        }
        currentCart.addItem(currentItem);
    }

    @FXML
    void removeIphoneButtonAction(ActionEvent event) {
        if (quantity2.getText().isBlank()) quantity2.setText("1");
        Item currentItem = new Item(item2.getText(),Integer.parseInt(quantity2.getText()),Integer.parseInt(price2.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
    }

    @FXML
    void removeAirpodsButtonAction(ActionEvent event) {
        if (quantity3.getText().isBlank()) quantity3.setText("1");
        Item currentItem = new Item(item3.getText(),Integer.parseInt(quantity3.getText()),Integer.parseInt(price3.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
    }

    @FXML
    void removeSurfaceButtonAction(ActionEvent event) {
        if (quantity4.getText().isBlank()) quantity4.setText("1");
        Item currentItem = new Item(item4.getText(),Integer.parseInt(quantity4.getText()),Integer.parseInt(price4.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
    }

    @FXML
    void removeSonyButtonAction(ActionEvent event) {
        if (quantity5.getText().isBlank()) quantity5.setText("1");
        Item currentItem = new Item(item5.getText(),Integer.parseInt(quantity5.getText()),Integer.parseInt(price5.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
    }

    @FXML
    void removeGalaxyButtonAction(ActionEvent event) {
        if (quantity6.getText().isBlank()) quantity6.setText("1");
        Item currentItem = new Item(item6.getText(),Integer.parseInt(quantity6.getText()),Integer.parseInt(price6.getText().substring(1)));
        for (int i=0;i<currentCart.getSize();i++) {
            if (currentCart.getItem(i).getItemName().equals(currentItem.getItemName())){
                currentCart.removeItem(currentCart.getItem(i));
            }
        }
    }

    public void accountDisplay(ActionEvent event) throws IOException, SQLException {
        Parent fxml = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("accountDisplay.fxml")));
        contentPane.getChildren().setAll(fxml);
    }

    public void cartDisplay(ActionEvent event) throws Exception {
        cartStackPane.setVisible(true);
        JFXDialogLayout cartDialogLayout = new JFXDialogLayout();
        cartDialogLayout.setHeading(new Label("Your shopping cart:"));
        cartDialogLayout.setBody(new Label(currentCart.print()));
        cartDialog = new JFXDialog(cartStackPane, cartDialogLayout, JFXDialog.DialogTransition.CENTER);
        cartDialog.show();

        JFXButton closeButton = new JFXButton("Close");
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                cartStackPane.setVisible(false);
                cartDialog.close();
            }
        });
        JFXButton checkoutButton = new JFXButton("Checkout");
        checkoutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    Stage checkoutStage = new Stage();
                    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("cartUI.fxml")));
                    checkoutStage.setTitle("CSE205 Final Project");
                    checkoutStage.setScene(new Scene(root, 600, 500));
                    checkoutStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        cartDialogLayout.setActions(closeButton,checkoutButton);
        System.out.println(currentCart.findTotalPrice());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cartStackPane.setVisible(false);
    }
}
