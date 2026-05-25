package coffeeVendingMachine.practice1.CoffeeClasses;

import coffeeVendingMachine.practice1.CoffeeDecorators.*;
import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public class Black extends Coffee {
    public Black() {
        this.coffeeType = "Black";
    }

    public int getPrice() {
        return 100;
    }

    protected void addCondiments() {
        System.err.println("Adding Hot Water");
    }

    public Map<Ingredients,Integer> getRecipe() {
        Map<Ingredients,Integer> recipe = new HashMap<>();

        recipe.put(Ingredients.CoffeeBeans, 15);
        recipe.put(Ingredients.Water, 15);

        return recipe;
    }
}
