package backend;
import customExceptions.BalanceException;
import customExceptions.GUIAlertException;
import customExceptions.MenuException;
import customExceptions.ProductException;

import java.util.ArrayList;

public class Cafe {

    private String name;
    private float money;
    private ArrayList<Product> menu;

    public Cafe(String name) {
        this.name = name;
        this.money = 0;
        this.menu = new ArrayList<Product>();
    }

    public float getMoney() {
        return this.money;
    }

    public void addProduct(Product product) throws MenuException {
        if(this.menu.size() >= 10) {
            throw new MenuException("Remove products from the menu before adding any more");
        } else {
            for (Product p : this.menu) {
                if (p.getName().equals(product.getName())) {
                    throw new MenuException("Product with this name already exists in the menu");
                }
            }
            menu.add(product);
        }
    }
    
    public void removeProduct(Product product) throws MenuException {
        if(menu.contains(product)) {
            menu.remove(product);
        } else {
            throw new MenuException("The specified product has not been found in the menu");
        }
    }

    public void buyProduct(String name, float amount) throws GUIAlertException {
        for(Product p : this.menu) {
            if(p.getName().equals(name)) {
                float cost = amount*p.getBuyPrice();
                if(cost > this.money) {
                    throw new BalanceException("There isn't enough money in the cash register to buy " + amount + "x " + p.getName());
                }
                p.addToStorage(amount);
                this.money -= cost;
                return;
            }
        }
        throw new ProductException("Selected product isn't in the menu");
    }

    public int getProductIndex(String productName) throws GUIAlertException {
        for(int i=0; i < this.menu.size(); i++) {
            if(this.menu.get(i).getName().equals(productName)) {
                return i;
            }
        }
        throw new ProductException("Product with this name is not on the menu");
    }

    public void sellProduct(String name, float amount) throws GUIAlertException {
        for(Product p : this.menu) {
            if(p.getName().equals(name)) {
                float cost = amount*p.getSellPrice();
                p.takeFromStorage(amount);
                this.money += cost;
                return;
            }
        }
        throw new ProductException("Selected product isn't in the menu");
    }

    public void changeBalance(float cash) throws BalanceException {
        if((this.money + cash) < 0) {
            throw new BalanceException("There is not enough money in the cash register of the Cafe.\n" + "Current balance: " + this.money + "\nRequired balance change: " + cash);
        } else {
            this.money += cash;
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Product> getMenu() {
        return menu;
    }
}