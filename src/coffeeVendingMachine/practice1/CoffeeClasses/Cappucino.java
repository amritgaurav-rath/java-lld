package coffeeVendingMachine.practice1.CoffeeClasses;

import coffeeVendingMachine.practice1.CoffeeDecorators.*;
import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public class Cappucino extends Coffee {
    public Cappucino() {
        this.coffeeType = "Cappucino";
    }

    public int getPrice() {
        return 150;
    }

    protected void addCondiments() {
        System.err.println("Adding Milk and Foam");
    }

    public Map<Ingredients,Integer> getRecipe() {
        Map<Ingredients,Integer> recipe = new HashMap<>();

        recipe.put(Ingredients.Milk, 10);
        recipe.put(Ingredients.Sugar, 5);
        recipe.put(Ingredients.CoffeeBeans, 10);
        recipe.put(Ingredients.Water, 5);

        return recipe;
    }
}
