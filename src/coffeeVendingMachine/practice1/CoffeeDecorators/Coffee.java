package coffeeVendingMachine.practice1.CoffeeDecorators;

import coffeeVendingMachine.practice1.enums.*;
import java.util.*;

public abstract class Coffee {
    protected String coffeeType = "Unknown Coffee";

    protected String getCoffeeType(){
        return this.coffeeType;
    }

    public void prepare() {
        System.err.println("Started preparing "+this.getCoffeeType()+ "...");
        grindBeans();
        brew();
        addCondiments();
        pourIntoCup();
    }

    private void grindBeans() {
        System.err.println("Grinding Beans");
    }

    private void brew() {
        System.err.println("Wait a sec, brewing...");
    }

    protected abstract void addCondiments();

    private void pourIntoCup() {
        System.err.println("Pouring...");
    }

    public abstract int getPrice();
    public abstract Map<Ingredients,Integer> getRecipe();
}
