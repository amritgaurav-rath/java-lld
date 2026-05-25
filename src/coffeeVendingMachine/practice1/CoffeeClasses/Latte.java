package coffeeVendingMachine.practice1.CoffeeClasses;

import coffeeVendingMachine.practice1.CoffeeDecorators.*;
import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public class Latte extends Coffee {
    public Latte() {
        this.coffeeType = "Latte";
    }

    public int getPrice() {
        return 200;
    }

    protected void addCondiments() {
        System.err.println("Adding Milk");
    }

    public Map<Ingredients,Integer> getRecipe() {
        Map<Ingredients,Integer> recipe = new HashMap<>();

        recipe.put(Ingredients.CoffeeBeans, 10);
        recipe.put(Ingredients.Milk, 15);
        recipe.put(Ingredients.Water, 10);

        return recipe;
    }
}
