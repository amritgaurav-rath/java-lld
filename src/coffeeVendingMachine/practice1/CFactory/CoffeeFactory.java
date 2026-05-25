package coffeeVendingMachine.practice1.CFactory;

import coffeeVendingMachine.practice1.CoffeeClasses.*;
import coffeeVendingMachine.practice1.CoffeeDecorators.*;
import coffeeVendingMachine.practice1.enums.*;

public class CoffeeFactory {
    public static Coffee createCoffee(CoffeeType coffeeType) {
        switch (coffeeType) {
            case Latte:
                return new Latte();
            case Cappucino:
                return new Cappucino();
            case Black:
                return new Black();
            default:
                throw new IllegalArgumentException("Coffee Type unsupported");
        }
    }
}
