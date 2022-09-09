package src;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Snack {

    private String categoryName;
    private double itemPrice;
    private int itemQuantity;
    private List<LocalDate> purchases = new ArrayList<>();

    private boolean served;

    public boolean isServed() {
        return this.served;
    }

    public void setServed(boolean served) {
        this.served = served;
    }


    public Snack(String categoryName, double itemPrice, int itemQuantity) {
        this.categoryName = categoryName;
        this.itemPrice = itemPrice;
        this.itemQuantity = itemQuantity;
        this.served = true;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public List<LocalDate> getPurchases() {
        return this.purchases;
    }

    public void addPurchase(LocalDate purchaseDate) {
        this.purchases.add(purchaseDate);
    }
}
