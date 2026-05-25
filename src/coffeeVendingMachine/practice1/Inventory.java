package coffeeVendingMachine.practice1;

import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public class Inventory {
    private Map<Ingredients,Integer> stock;
    private static Inventory INSTANCE = new Inventory();

    public Inventory() {
        this.stock = new HashMap<>();
    }

    public static Inventory getInstance() {
        return INSTANCE;
    }

    public void addStock(Map<Ingredients,Integer> reqStock) {
        for(Map.Entry<Ingredients,Integer> st:reqStock.entrySet()) {
            Ingredients ing = st.getKey();
            int val = st.getValue();
            this.stock.put(ing, this.stock.getOrDefault(ing, 0) + val);
        }
    }

    public void deductStock(Map<Ingredients,Integer> reqStock) {
        for(Map.Entry<Ingredients,Integer> st:reqStock.entrySet()) {
            Ingredients ing = st.getKey();
            int val = st.getValue();
            
            this.stock.put(ing, this.stock.getOrDefault(ing, 0) - val);
        }
    }

    public void showIngredients() {
        for(Map.Entry<Ingredients,Integer> mp: this.stock.entrySet()) {
            System.err.println("Ingredient: "+mp.getKey() +" Present Quantity: "+mp.getValue());
        }
    }

    public boolean hasIngredients(Map<Ingredients,Integer> reqStock) {
        for(Map.Entry<Ingredients,Integer> req:reqStock.entrySet()) {
            if(this.stock.getOrDefault(req.getKey(), 0) < req.getValue()) {
                return false;
            }
        }

        return true;
    }

    public void printInventory() {
        for(Map.Entry<Ingredients,Integer> st:this.stock.entrySet()) {
            System.err.println("Ingredient: "+st.getKey() + ", Qty: "+st.getValue());
        }
    }
}
