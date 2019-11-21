package backend;

import customExceptions.GUIAlertException;
import customExceptions.ProductException;

import java.io.Serializable;

public abstract class Product implements Serializable, Interface {
    
    private String name;
    private float buyPrice;
    private float sellPrice;
    float storedAmount;

    public Product(String name, float buyPrice, float sellPrice) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.storedAmount = 0;
    }
    
    public float getBuyPrice() {
        return this.buyPrice;
    }

    public float getSellPrice() {
        return this.sellPrice;
    }

    public String getName() {
        return this.name;
    }

    public float getStoredAmount() { return this.storedAmount; }

    public void takeFromStorage(float amount) throws GUIAlertException {
        if(this.storedAmount < amount) {
            throw new ProductException("There aren't enough products to take");
        }
        this.storedAmount -= amount;
    }

    public void addToStorage(float amount) {
        this.storedAmount += amount;
    }

}