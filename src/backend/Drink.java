package backend;

import customExceptions.ProductException;

public class Drink extends Product {

    private float weightPerPortion;

    public Drink(String name, float buyPrice, float sellPrice, float weightPerPortion) {

        super(name, buyPrice, sellPrice);
        this.weightPerPortion = weightPerPortion;

    }

    public float getWeightPerPortion() { return this.weightPerPortion; }
    
}