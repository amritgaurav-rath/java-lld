package coffeeVendingMachine.practice1.CoffeeDecorators;

import java.util.*;
import coffeeVendingMachine.practice1.enums.*;

public class CaramelDecorator extends CoffeeDecorator {
    private int extraCost = 20;
    Map<Ingredients,Integer> additions = Map.of(Ingredients.Sugar,2, Ingredients.Caramel, 5);

    public CaramelDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getCoffeeType() {
        return decoratedCoffee.getCoffeeType() + ", Extra Caramel";
    }

    @Override
    public int getPrice() {
        return this.decoratedCoffee.getPrice() + extraCost;
    }

    @Override
    public Map<Ingredients,Integer> getRecipe() {
        Map<Ingredients,Integer> newRecipe = new HashMap<>(this.decoratedCoffee.getRecipe());
        
        for(Map.Entry<Ingredients,Integer> recipe: additions.entrySet()) {
            newRecipe.put(recipe.getKey(), newRecipe.getOrDefault(recipe.getKey(), 0) + recipe.getValue());
        }

        return newRecipe;
    }

    public void prepare() {
        super.prepare();
        System.err.println("Putting Caramel on top...");
    }
}
