package sample;

import javafx.beans.property.SimpleStringProperty;

public class pendingOrder {
    SimpleStringProperty username;
    SimpleStringProperty order_id;
    SimpleStringProperty status;

    public pendingOrder(String username, String order_id, String status) {
        this.username = new SimpleStringProperty(username);
        this.order_id = new SimpleStringProperty(order_id);
        this.status = new SimpleStringProperty(status);
    }

    public String getUsername() {
        return username.get();
    }
    public String getOrder_id() {
        return order_id.get();
    }
    public String getStatus() {
        return status.get();
    }
    public void setUsername(String username) {
        this.username.set(username);
    }

    public void setOrder_id(String order_id) {
        this.order_id.set(order_id);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

}