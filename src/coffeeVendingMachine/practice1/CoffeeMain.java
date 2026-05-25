package coffeeVendingMachine.practice1;

import coffeeVendingMachine.practice1.enums.CoffeeType;
import coffeeVendingMachine.practice1.enums.Ingredients;
import coffeeVendingMachine.practice1.enums.Toppings;

import java.util.*;

public class CoffeeMain {
    public static void main(String[] args) {

        CoffeeVendingMachine machine = CoffeeVendingMachine.getInstance();
        Inventory inv = Inventory.getInstance();

        inv.addStock(Map.of(Ingredients.CoffeeBeans, 50, 
        Ingredients.Water, 500,
        Ingredients.Milk, 200,
        Ingredients.Sugar, 100,
        Ingredients.Caramel, 50));
        inv.printInventory();

        machine.selectCoffee(CoffeeType.Cappucino, List.of());
        machine.insertMoney(0);
        machine.insertMoney(5); 
        machine.dispenseCoffee();
        machine.cancel();
        inv.printInventory();

        machine.selectCoffee(CoffeeType.Latte, List.of(Toppings.ExtraSugar,Toppings.Caramel));
        machine.insertMoney(200);
        machine.insertMoney(50); 
        machine.dispenseCoffee();
        inv.printInventory();
    }
}
