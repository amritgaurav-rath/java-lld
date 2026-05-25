package coffeeVendingMachine.practice1.CoffeeDecorators;

import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public abstract class CoffeeDecorator extends Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public int getPrice() {
        return this.decoratedCoffee.getPrice();
    }

    @Override
    public Map<Ingredients,Integer> getRecipe() {
        return this.decoratedCoffee.getRecipe();
    }

    @Override
    protected void addCondiments() {
        this.decoratedCoffee.addCondiments();
    }

    @Override
    public void prepare() {
        this.decoratedCoffee.prepare();
    }
}
