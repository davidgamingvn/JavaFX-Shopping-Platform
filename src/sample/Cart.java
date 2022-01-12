package sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private final ArrayList<Item> cart;

    public Cart() {
        cart = new ArrayList<Item>();
    }

    public void addItem(Item item) {
        cart.add(item);
    }

    public void removeItem(Item item) {
        cart.remove(item);
    }

    public int getSize() {
        return cart.size();
    }

    public boolean duplicateItem(Item item) {
        boolean found = false;
        for (int i = 0; i < cart.size(); i++) {
            if (item.getItemName().equals(cart.get(i).getItemName())) {
                found = true;
                break;
            } else
                found = false;
        }
        return found;
    }

    public Item getItem(int index) {
        return cart.get(index);
    }

    public int findQuantityOfItem(Item item) {
        int totalQuantity = item.getQuantity();
        for (int i = 0; i < cart.size(); i++) {
            if (duplicateItem(item)) {
                totalQuantity += cart.get(i).getQuantity();
                removeItem(cart.get(i));
            }
        }
        return totalQuantity;
    }

    public int findTotalPrice() {
        int totalPrice = 0;
        for (Item items : cart) {
            totalPrice += items.getPrice() * items.getQuantity();
        }
        return totalPrice;
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public String print() {
        String result = "";
        for (Item items : cart) {
            result += items.toString() + "\n";
        }
        return result;
    }

    public String serialize() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        stream.write(print().getBytes());
        return stream.toString();
    }
}
