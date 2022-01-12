package sample;

public class Item {
    private String itemName;
    private int quantity;

    private int price;
    public Item() {
        itemName = "";
        quantity = price = 0;
    }
    public Item(String itemName, int quantity, int price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString() {
        return itemName + ", Quantity: " + quantity + ", Price: " + price*quantity;
    }
}
